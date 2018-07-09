import { Component } from '@angular/core';

@Component({
  templateUrl: 'collapses.component.html'
})
export class CollapsesComponent {

  isCollapsed: boolean = false;
  constructor() { }


  collapsed(event: any): void {
    // console.log(event);
  }

  expanded(event: any): void {
    // console.log(event);
  }

}
