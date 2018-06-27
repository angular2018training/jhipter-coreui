import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import {  ToasterConfig }  from 'angular2-toaster/angular2-toaster';

@Component({
  // tslint:disable-next-line
  selector: 'body',
  template: '<toaster-container [toasterconfig]="toasterconfig"></toaster-container> <router-outlet></router-outlet>'
})
export class AppComponent implements OnInit {
  public toasterconfig : ToasterConfig =
    new ToasterConfig({
      tapToDismiss: true,
      positionClass:'toast-bottom-right',
      timeout: 5000
    });
  constructor(private router: Router) { }

  ngOnInit() {
    this.router.events.subscribe((evt) => {
      if (!(evt instanceof NavigationEnd)) {
        return;
      }
      window.scrollTo(0, 0)
    });
  }
}
