import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';
import {
    UserPostOfficeDetailUpdateComponent,
    UserPostOfficeDetailPopupService,
    UserPostOfficeDetailListComponent,
    UserPostOfficeDetailDeleteDialogComponent
} from './';
import {NgSelectModule} from "@ng-select/ng-select";



@NgModule({
    imports: [
        NextlogixSharedModule,
        RouterModule,
      NgSelectModule
],
declarations: [
    UserPostOfficeDetailUpdateComponent,
    UserPostOfficeDetailListComponent,
    UserPostOfficeDetailDeleteDialogComponent
],
entryComponents: [
    UserPostOfficeDetailUpdateComponent,
    UserPostOfficeDetailListComponent,
    UserPostOfficeDetailDeleteDialogComponent
],
exports: [
    UserPostOfficeDetailUpdateComponent,
    UserPostOfficeDetailListComponent,
    UserPostOfficeDetailDeleteDialogComponent
],
providers: [
    UserPostOfficeDetailPopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixUserPostOfficeDetailModule {}
