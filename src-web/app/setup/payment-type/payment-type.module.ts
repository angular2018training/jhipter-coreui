import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';
import {
PaymentTypeComponent,
    PaymentTypeDetailComponent,
    PaymentTypeUpdateComponent,
    PaymentTypeDeletePopupComponent,
    PaymentTypeDeleteDialogComponent,
    paymentTypeRoute,
    paymentTypePopupRoute,
    PaymentTypePopupService
} from './';

const ENTITY_STATES = [
    ...paymentTypeRoute,
    ...paymentTypePopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    PaymentTypeComponent,
    PaymentTypeDetailComponent,
    PaymentTypeUpdateComponent,
    PaymentTypeDeleteDialogComponent,
    PaymentTypeDeletePopupComponent,
],
    entryComponents: [
    PaymentTypeComponent,
    PaymentTypeUpdateComponent,
    PaymentTypeDeleteDialogComponent,
    PaymentTypeDeletePopupComponent,
],
providers: [
    PaymentTypePopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixPaymentTypeModule {}
