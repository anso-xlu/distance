import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-error404',
  template: `
    <p>
      error404 works!
    </p>`,
  styles: [`
    p {
      color: darkred;
    }
  `]
})
export class Page404Component implements OnInit {

  constructor() {
  }

  ngOnInit() {
  }

}
