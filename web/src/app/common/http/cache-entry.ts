import {HttpResponse} from '@angular/common/http';

export interface CacheEntry {
  url: string;
  expire: number;
  response: HttpResponse<any>;
}
