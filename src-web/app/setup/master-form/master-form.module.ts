import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';
import {
MasterFormComponent,
    MasterFormDetailComponent,
    MasterFormUpdateComponent,
    MasterFormDeletePopupComponent,
    MasterFormDeleteDialogComponent,
    masterFormRoute,
    masterFormPopupRoute,
    MasterFormPopupService
} from './';
import {NextlogixDetailFormModule} from "../detail-form/detail-form.module";
import {NextlogixDetailFormDetailModule} from "../detail-form-share/detail-form-detail.module";

const ENTITY_STATES = [
    ...masterFormRoute,
    ...masterFormPopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
      NextlogixDetailFormModule,
      NextlogixDetailFormDetailModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    MasterFormComponent,
    MasterFormDetailComponent,
    MasterFormUpdateComponent,
    MasterFormDeleteDialogComponent,
    MasterFormDeletePopupComponent,
],
    entryComponents: [
    MasterFormComponent,
    MasterFormUpdateComponent,
    MasterFormDeleteDialogComponent,
    MasterFormDeletePopupComponent,
],
providers: [
    MasterFormPopupService,
],
exports :[NextlogixDetailFormModule],
schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixMasterFormModule {}
