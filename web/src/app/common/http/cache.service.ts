import {Injectable} from '@angular/core';
import {Cache} from './cache';
import {HttpRequest, HttpResponse} from '@angular/common/http';
import {HTTP_CACHE_TIMEOUT_MS} from '../config/config';
import {CacheEntry} from './cache-entry';

@Injectable({
  providedIn: 'root'
})
export class CacheService implements Cache {

  private cache = new Map<string, CacheEntry>();

  constructor() {
  }

  get(req: HttpRequest<any>): HttpResponse<any> | null {
    // 判断当前请求是否已被缓存，若未缓存则返回null
    const entry = this.cache.get(req.urlWithParams);
    if (!entry) {
      return null;
    }
    // 若缓存命中，则判断缓存是否过期，若已过期则返回null。否则返回请求对应的响应对象
    const isExpired = Date.now() > entry.expire;
    if (isExpired || this.cache.size > 30) {
      this.deleteExpiredCache();
    }

    return isExpired ? null : entry.response;
  }

  put(req: HttpRequest<any>, resp: HttpResponse<any>): void {
    // 创建CacheEntry对象
    const entry: CacheEntry = {
      url: req.urlWithParams,
      expire: Date.now() + HTTP_CACHE_TIMEOUT_MS,
      response: resp
    };
    // 以请求url作为键，CacheEntry对象为值，保存到cacheMap中。并执行
    this.cache.set(req.urlWithParams, entry);
  }

  private deleteExpiredCache() {
    this.cache.forEach(entry => {
      if (Date.now() > entry.expire) {
        this.cache.delete(entry.url);
      }
    });
  }
}
