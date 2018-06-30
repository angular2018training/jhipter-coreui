import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';

import {DetailFormShareUpdateComponent} from "./detail-form-share-update.component";
import {DetailFormSharePopupService} from "./detail-form-share-popup.service";



@NgModule({
    imports: [
        NextlogixSharedModule
],
declarations: [
    DetailFormShareUpdateComponent
],
    entryComponents: [
      DetailFormShareUpdateComponent
],
  exports:[
    DetailFormShareUpdateComponent
  ],
providers: [
  DetailFormSharePopupService
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixDetailFormShareModule {}
