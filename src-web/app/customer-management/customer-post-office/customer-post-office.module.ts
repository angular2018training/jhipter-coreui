import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';
import {
CustomerPostOfficeComponent,
    CustomerPostOfficeDetailComponent,
    CustomerPostOfficeUpdateComponent,
    CustomerPostOfficeDeletePopupComponent,
    CustomerPostOfficeDeleteDialogComponent,
    customerPostOfficeRoute,
    customerPostOfficePopupRoute,
    CustomerPostOfficePopupService
} from './';

const ENTITY_STATES = [
    ...customerPostOfficeRoute,
    ...customerPostOfficePopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    CustomerPostOfficeComponent,
    CustomerPostOfficeDetailComponent,
    CustomerPostOfficeUpdateComponent,
    CustomerPostOfficeDeleteDialogComponent,
    CustomerPostOfficeDeletePopupComponent,
],
    entryComponents: [
    CustomerPostOfficeComponent,
    CustomerPostOfficeUpdateComponent,
    CustomerPostOfficeDeleteDialogComponent,
    CustomerPostOfficeDeletePopupComponent,
],
providers: [
    CustomerPostOfficePopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixCustomerPostOfficeModule {}
