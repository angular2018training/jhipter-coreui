import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';
import {
QuotationTypeComponent,
    QuotationTypeDetailComponent,
    QuotationTypeUpdateComponent,
    QuotationTypeDeletePopupComponent,
    QuotationTypeDeleteDialogComponent,
    quotationTypeRoute,
    quotationTypePopupRoute,
    QuotationTypePopupService
} from './';

const ENTITY_STATES = [
    ...quotationTypeRoute,
    ...quotationTypePopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    QuotationTypeComponent,
    QuotationTypeDetailComponent,
    QuotationTypeUpdateComponent,
    QuotationTypeDeleteDialogComponent,
    QuotationTypeDeletePopupComponent,
],
    entryComponents: [
    QuotationTypeComponent,
    QuotationTypeUpdateComponent,
    QuotationTypeDeleteDialogComponent,
    QuotationTypeDeletePopupComponent,
],
providers: [
    QuotationTypePopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixQuotationTypeModule {}
