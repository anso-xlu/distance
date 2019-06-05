import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {catchError, map} from 'rxjs/operators';
import {HttpWrapper} from './http-wrapper';
import {CacheService} from './cache.service';
import {Result} from './result';

const ERROR_MESSAGE = {
  0: '未知异常',
  403: '用户得到授权，但是访问是被禁止的。',
  404: '发出的请求针对的是不存在的记录，服务器没有进行操作。',
  406: '请求的格式不可得。',
  410: '请求的资源被永久删除，且不会再得到的。',
  422: '当创建一个对象时，发生一个验证错误。',
  500: '服务器发生错误，请检查服务器。',
  502: '网关错误。',
  503: '服务不可用，服务器暂时过载或维护。',
  504: '网关超时。',
};

/**
 * 1. interceptor 统一处理,处理后返回 Result<T>.data, 否则完整返回Result<T>
 * 2. GET 设置缓存
 * 3. 处理 URL and HttpHeader
 */
@Injectable({
  providedIn: 'root'
})
export class HttpInterceptorService implements HttpInterceptor {
  constructor(private cacheService: CacheService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // 获取httpWrapper
    const wrapper: HttpWrapper = HttpWrapper.parse(req);

    const newReq = req.clone({
      url: wrapper.getUrl(),
      params: wrapper.getParams(),
      body: wrapper.getBody(),
      setHeaders: {
        'Content-Type': 'application/json;charset=UTF-8',
        Authorization: ''
      },
    });

    // 获取缓存数据
    const cache: HttpResponse<any> = this.cacheService.get(req);
    if (wrapper.isCacheable() && cache) {
      return of(cache);
    } else {
      return next.handle(newReq).pipe(
        map((event: any) => {
          // 统一处理结果集
          if (wrapper.isProcess(event)) {
            if (HttpWrapper.isError(event.status)) {
              if (ERROR_MESSAGE[event.status]) {
                console.log(ERROR_MESSAGE[event.status]);
              } else if (wrapper.getMsg(event)) {
                console.log(wrapper.getMsg(event));
              } else {
                console.log(ERROR_MESSAGE[0]);
              }
            } else {
              // 处理后返回 Result<T>.data
              const response: HttpResponse<Result<any>> = event.clone({
                body: event.body.data
              });

              if (wrapper.isCacheable()) {// 缓存
                this.cacheService.put(req, response);
              }
              return response;
            }
          } else {
            return event;
          }
        }),
        catchError(err => {
          console.log(ERROR_MESSAGE[err.status]);
          return of([]);
        })
      );
    }
  }
}

