export interface Page<T> {
  data: T[];
  hasNext: boolean;
  isFirst: boolean;
  isLast: boolean;
  pageNumber: number;
  sizeNumber: number;
  pageTotal: number;
  sizeTotal: number;
}
