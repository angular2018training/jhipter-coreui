import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { NextlogixSharedModule } from '../../shared';
import {
QuotationCodComponent,
    QuotationCodDetailComponent,
    QuotationCodUpdateComponent,
    QuotationCodDeletePopupComponent,
    QuotationCodDeleteDialogComponent,
    quotationCodRoute,
    quotationCodPopupRoute,
    QuotationCodPopupService
} from './';

const ENTITY_STATES = [
    ...quotationCodRoute,
    ...quotationCodPopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    QuotationCodComponent,
    QuotationCodDetailComponent,
    QuotationCodUpdateComponent,
    QuotationCodDeleteDialogComponent,
    QuotationCodDeletePopupComponent,
],
    entryComponents: [
    QuotationCodComponent,
    QuotationCodUpdateComponent,
    QuotationCodDeleteDialogComponent,
    QuotationCodDeletePopupComponent,
],
providers: [
    QuotationCodPopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixQuotationCodModule {}
