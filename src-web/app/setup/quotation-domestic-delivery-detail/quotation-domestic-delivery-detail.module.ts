import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';
import {
    QuotationDomesticDeliveryDetailUpdateComponent,
    QuotationDomesticDeliveryDetailPopupService,
    QuotationDomesticDeliveryDetailListComponent,
    QuotationDomesticDeliveryDetailDeleteDialogComponent
} from './';



@NgModule({
    imports: [
        NextlogixSharedModule,
        RouterModule,

],
declarations: [
    QuotationDomesticDeliveryDetailUpdateComponent,
    QuotationDomesticDeliveryDetailListComponent,
    QuotationDomesticDeliveryDetailDeleteDialogComponent
],
entryComponents: [
    QuotationDomesticDeliveryDetailUpdateComponent,
    QuotationDomesticDeliveryDetailListComponent,
    QuotationDomesticDeliveryDetailDeleteDialogComponent
],
exports: [
    QuotationDomesticDeliveryDetailUpdateComponent,
    QuotationDomesticDeliveryDetailListComponent,
    QuotationDomesticDeliveryDetailDeleteDialogComponent
],
providers: [
    QuotationDomesticDeliveryDetailPopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixQuotationDomesticDeliveryDetailModule {}
