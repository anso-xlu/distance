import {Injectable} from '@angular/core';
import {Menu} from '../model/menu';
import {HttpClient} from '@angular/common/http';
import {HttpWrapper} from '../common/http/http-wrapper';
import {Page} from '../model/page';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  constructor(private http: HttpClient) {
  }

  get(menu?: Menu): Observable<Menu> {
    return this.http.get<Menu>('/U103/0/', HttpWrapper.params(menu));
  }

  getMenu(): Observable<Menu[]> {
    return this.http.get<Menu[]>('/menu/getMenu');
  }

  save(menu: Menu): Observable<Menu> {
    return this.http.post<Menu>('/menu', menu);
  }

  delete(id: number): Observable<Menu> {
    return this.http.post<Menu>('/menu', {id});
  }
}
