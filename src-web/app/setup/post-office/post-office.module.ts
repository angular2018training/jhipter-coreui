import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';
import {
PostOfficeComponent,
    PostOfficeDetailComponent,
    PostOfficeUpdateComponent,
    PostOfficeDeletePopupComponent,
    PostOfficeDeleteDialogComponent,
    postOfficeRoute,
    postOfficePopupRoute,
    PostOfficePopupService
} from './';

const ENTITY_STATES = [
    ...postOfficeRoute,
    ...postOfficePopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    PostOfficeComponent,
    PostOfficeDetailComponent,
    PostOfficeUpdateComponent,
    PostOfficeDeleteDialogComponent,
    PostOfficeDeletePopupComponent,
],
    entryComponents: [
    PostOfficeComponent,
    PostOfficeUpdateComponent,
    PostOfficeDeleteDialogComponent,
    PostOfficeDeletePopupComponent,
],
providers: [
    PostOfficePopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixPostOfficeModule {}
