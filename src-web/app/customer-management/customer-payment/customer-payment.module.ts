import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';
import {
CustomerPaymentComponent,
    CustomerPaymentDetailComponent,
    CustomerPaymentUpdateComponent,
    CustomerPaymentDeletePopupComponent,
    CustomerPaymentDeleteDialogComponent,
    customerPaymentRoute,
    customerPaymentPopupRoute,
    CustomerPaymentPopupService
} from './';

const ENTITY_STATES = [
    ...customerPaymentRoute,
    ...customerPaymentPopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    CustomerPaymentComponent,
    CustomerPaymentDetailComponent,
    CustomerPaymentUpdateComponent,
    CustomerPaymentDeleteDialogComponent,
    CustomerPaymentDeletePopupComponent,
],
    entryComponents: [
    CustomerPaymentComponent,
    CustomerPaymentUpdateComponent,
    CustomerPaymentDeleteDialogComponent,
    CustomerPaymentDeletePopupComponent,
],
providers: [
    CustomerPaymentPopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixCustomerPaymentModule {}
