import {Component, OnInit} from '@angular/core';
import {MenuService} from '../../service/menu.service';
import {Menu} from '../../model/menu';
import {NestedTreeControl} from '@angular/cdk/tree';
import {MatTreeNestedDataSource} from '@angular/material';
import {SelectionModel} from '@angular/cdk/collections';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {
  treeControl: NestedTreeControl<Menu>;
  treeSource: MatTreeNestedDataSource<Menu>;

  /** The selection for checklist */
  checklistSelection = new SelectionModel<Menu>(true);

  constructor(private menuService: MenuService) {
    this.treeControl = new NestedTreeControl<Menu>((menu: Menu) => menu.children);
    this.treeSource = new MatTreeNestedDataSource();
    menuService.getMenu().subscribe((menus: Menu[]) => this.treeSource.data = menus);
  }

  ngOnInit() {
  }

  hasChild(_: number, menu: Menu): boolean {
    return Boolean(menu.children);
  }
}
