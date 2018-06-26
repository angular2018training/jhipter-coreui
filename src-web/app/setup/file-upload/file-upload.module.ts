import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';
import {
FileUploadComponent,
    FileUploadDetailComponent,
    FileUploadUpdateComponent,
    FileUploadDeletePopupComponent,
    FileUploadDeleteDialogComponent,
    fileUploadRoute,
    fileUploadPopupRoute,
    FileUploadPopupService
} from './';

const ENTITY_STATES = [
    ...fileUploadRoute,
    ...fileUploadPopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    FileUploadComponent,
    FileUploadDetailComponent,
    FileUploadUpdateComponent,
    FileUploadDeleteDialogComponent,
    FileUploadDeletePopupComponent,
],
    entryComponents: [
    FileUploadComponent,
    FileUploadUpdateComponent,
    FileUploadDeleteDialogComponent,
    FileUploadDeletePopupComponent,
],
providers: [
    FileUploadPopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixFileUploadModule {}
