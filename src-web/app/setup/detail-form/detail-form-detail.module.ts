import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';
import {
DetailFormComponent,
    DetailFormDetailUpdateComponent,
    DetailFormDetailPopupService,
    DetailFormDetailListComponent,
    DetailFormDetailDeleteDialogComponent
} from './';



@NgModule({
    imports: [
        NextlogixSharedModule,
],
declarations: [
    DetailFormDetailUpdateComponent,
    DetailFormDetailListComponent,
    DetailFormDetailDeleteDialogComponent
],
entryComponents: [
    DetailFormDetailUpdateComponent,
    DetailFormDetailListComponent,
    DetailFormDetailDeleteDialogComponent
],
exports: [
    DetailFormDetailUpdateComponent,
    DetailFormDetailListComponent,
    DetailFormDetailDeleteDialogComponent
],
providers: [
    DetailFormDetailPopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixDetailFormDetailModule {}
