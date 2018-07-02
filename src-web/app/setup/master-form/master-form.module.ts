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
import {NextlogixDetailFormDetailModule} from "../detail-form-detail/detail-form-detail.module";

const ENTITY_STATES = [
    ...masterFormRoute,
    ...masterFormPopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
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

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixMasterFormModule {}
