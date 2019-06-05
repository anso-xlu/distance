/**
 *  服务端结果集： http.
 */
export interface Result<T> {
  data: T;
  code: number;
  msg: string;
}
