import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../model/user';
import {Observable} from 'rxjs';
import {HttpWrapper} from '../common/http/http-wrapper';
import {Page} from '../model/page';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) {
  }

  get(user?: User): Observable<User> {
    return this.httpClient.get<User>('/U103/0/', HttpWrapper.params(user));
  }

  getPage(user?: User, pageNumber?: number, sizeNumber?: number): Observable<Page<User>> {
    return this.httpClient.get<Page<User>>('', HttpWrapper.params(user, {pageNumber, sizeNumber}));
  }

  save(user: User): Observable<User> {
    return this.httpClient.post<User>('', user);
  }

}
