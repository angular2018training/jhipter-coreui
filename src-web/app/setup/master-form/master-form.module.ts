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
import {NextlogixDetailFormShareModule} from "../detail-form-share/detail-form-share.module";

const ENTITY_STATES = [
    ...masterFormRoute,
    ...masterFormPopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
      NextlogixDetailFormModule,
      NextlogixDetailFormShareModule,
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
