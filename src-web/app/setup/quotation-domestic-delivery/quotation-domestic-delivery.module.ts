import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { NextlogixSharedModule } from '../../shared';
import {
QuotationDomesticDeliveryComponent,
    QuotationDomesticDeliveryDetailComponent,
    QuotationDomesticDeliveryUpdateComponent,
    QuotationDomesticDeliveryDeletePopupComponent,
    QuotationDomesticDeliveryDeleteDialogComponent,
    quotationDomesticDeliveryRoute,
    quotationDomesticDeliveryPopupRoute,
    QuotationDomesticDeliveryPopupService
} from './';

const ENTITY_STATES = [
    ...quotationDomesticDeliveryRoute,
    ...quotationDomesticDeliveryPopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    QuotationDomesticDeliveryComponent,
    QuotationDomesticDeliveryDetailComponent,
    QuotationDomesticDeliveryUpdateComponent,
    QuotationDomesticDeliveryDeleteDialogComponent,
    QuotationDomesticDeliveryDeletePopupComponent,
],
    entryComponents: [
    QuotationDomesticDeliveryComponent,
    QuotationDomesticDeliveryUpdateComponent,
    QuotationDomesticDeliveryDeleteDialogComponent,
    QuotationDomesticDeliveryDeletePopupComponent,
],
providers: [
    QuotationDomesticDeliveryPopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixQuotationDomesticDeliveryModule {}
