export interface Menu {
  id?: number;
  icon: string;
  name?: string;
  url?: string;
  children?: Menu[];
  parentId?: number;
}
