import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { NextlogixSharedModule } from '../../shared';
import {
QuotationReturnComponent,
    QuotationReturnDetailComponent,
    QuotationReturnUpdateComponent,
    QuotationReturnDeletePopupComponent,
    QuotationReturnDeleteDialogComponent,
    quotationReturnRoute,
    quotationReturnPopupRoute,
    QuotationReturnPopupService
} from './';

const ENTITY_STATES = [
    ...quotationReturnRoute,
    ...quotationReturnPopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    QuotationReturnComponent,
    QuotationReturnDetailComponent,
    QuotationReturnUpdateComponent,
    QuotationReturnDeleteDialogComponent,
    QuotationReturnDeletePopupComponent,
],
    entryComponents: [
    QuotationReturnComponent,
    QuotationReturnUpdateComponent,
    QuotationReturnDeleteDialogComponent,
    QuotationReturnDeletePopupComponent,
],
providers: [
    QuotationReturnPopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixQuotationReturnModule {}
