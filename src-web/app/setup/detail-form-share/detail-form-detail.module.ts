import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import {NextlogixSharedModule} from "../../shared/shared.module";
import {DetailFormDetailUpdateComponent} from "./detail-form-detail-update.component";
import {DetailFormDetailPopupService} from "./detail-form-detail-popup.service";
import {DetailFormDetailListComponent} from "./detail-form-detail-list.component";
import {DetailFormDetailDeleteDialogComponent} from "./detail-form-detail-delete-dialog.component";



@NgModule({
    imports: [
        NextlogixSharedModule
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
exports:[
    DetailFormDetailUpdateComponent,
  DetailFormDetailListComponent,
  DetailFormDetailDeleteDialogComponent
],
providers: [
  DetailFormDetailPopupService
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixDetailFormDetailModule {}
