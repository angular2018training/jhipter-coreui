import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';
import {
CustomerLegalComponent,
    CustomerLegalDetailComponent,
    CustomerLegalUpdateComponent,
    CustomerLegalDeletePopupComponent,
    CustomerLegalDeleteDialogComponent,
    customerLegalRoute,
    customerLegalPopupRoute,
    CustomerLegalPopupService
} from './';

const ENTITY_STATES = [
    ...customerLegalRoute,
    ...customerLegalPopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    CustomerLegalComponent,
    CustomerLegalDetailComponent,
    CustomerLegalUpdateComponent,
    CustomerLegalDeleteDialogComponent,
    CustomerLegalDeletePopupComponent,
],
    entryComponents: [
    CustomerLegalComponent,
    CustomerLegalUpdateComponent,
    CustomerLegalDeleteDialogComponent,
    CustomerLegalDeletePopupComponent,
],
providers: [
    CustomerLegalPopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixCustomerLegalModule {}
