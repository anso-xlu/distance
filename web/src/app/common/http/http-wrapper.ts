import {HttpParams, HttpRequest, HttpResponse} from '@angular/common/http';
import {serviceUrl} from '../config/config';
import {Result} from './result';

/**
 * 1. interceptor 获取 url/body/params
 * 2. GET params wrapper
 * 3. process：interceptor　是否统一处理结果集
 * ４．cacheable　interceptor　是否缓存: 只有 GET 才可以
 */
export class HttpWrapper {
  private readonly args: any; // body or GET.url参数
  private readonly process; // 是否统一处理结果集
  private readonly cacheable; // 是否缓存:
  private readonly req: HttpRequest<any>; // 方便使用加的, parse() used

  private constructor(args: any, process: boolean, cacheable: boolean, req?: HttpRequest<any>) {
    this.args = args;
    this.process = process;
    this.cacheable = cacheable;
    this.req = req;
  }

  // interceptor 转换
  static parse(req: HttpRequest<any>): HttpWrapper {
    const isGetMethod = req.method.toUpperCase() === 'GET';

    if (isGetMethod) {
      const params: HttpParams = req.params;
      if (params.get('isHttpParams') === 'true') {
        return new HttpWrapper(
          params.get('ages'),
          Boolean(params.get('process')),
          Boolean(params.get('cacheable')),
          req);
      }
    } else if (req.body instanceof HttpWrapper) {
      return req.body;
    }
    return new HttpWrapper(null, true, true, req);
  }

  /**
   *  GET 参数处理
   *  params：{key: value != object}
   *  options {
   *      pageNumber: 分页查询页码
   *      sizeNumber: 分页查询条数
   *  }
   */
  static params(params: any, options?: {
    pageNumber?: number, sizeNumber?: number,
    process?: boolean, cacheable?: boolean
  }): { params: HttpParams } | null {
    if (params || options) {
      if (!params) {
        params = {};
      }
      if (!options) {
        options = {};
      }

      params.pageNumber = options.pageNumber;
      params.sizeNumber = options.sizeNumber;

      if (options.cacheable || options.process) {
        return {
          params: new HttpParams({
            fromObject: {
              isHttpParams: 'true',
              params: JSON.stringify(params),
              process: `${Boolean(options.process)}`,
              cacheable: `${Boolean(options.cacheable)}`
            }
          })
        };
      } else {
        return {params: new HttpParams({fromObject: params})};
      }
    }
  }

  /**
   *  NOT GET
   */
  static body(body: any, process?: boolean): HttpWrapper | null {
    if (body || process) {
      return new HttpWrapper(JSON.stringify(body), process, false);
    }
  }

  static isError(status: number) {
    return status >= 400;
  }


  /**
   *  interceptor，GET 获取实际参数
   */
  getParams(): HttpParams {
    return this.args && this.isGetMethod() ? new HttpParams({fromObject: this.args}) : this.req.params;
  }

  /**
   *  interceptor，NOT GET 获取实际参数
   */
  getBody(): any {
    return this.args && !this.isGetMethod() ? this.args : this.req.body;
  }

  /**
   * interceptor，处理 URL 错误的双//
   */
  getUrl(): string {
    const url = `${serviceUrl}/${this.req.url}`;
    return url.replace(/([^:])\/\//g, '$1/');
  }

  isProcess(even: any): boolean {
    return this.process && (even instanceof HttpResponse);
  }

  getMsg(resp: HttpResponse<Result<any>>): string | null {
    if (resp.headers.get('Content-Type').startsWith('application/json;')) {
      return resp.body.msg;
    }
  }

  /**
   * interceptor used
   */
  isCacheable(): boolean {
    return this.isGetMethod() ? this.cacheable : false;
  }

  isGetMethod(): boolean {
    return this.req.method.toUpperCase() === 'GET';
  }

}
