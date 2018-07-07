import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';
import {
    QuotationPickupDetailUpdateComponent,
    QuotationPickupDetailPopupService,
    QuotationPickupDetailListComponent,
    QuotationPickupDetailDeleteDialogComponent
} from './';



@NgModule({
    imports: [
        NextlogixSharedModule,
        RouterModule,

],
declarations: [
    QuotationPickupDetailUpdateComponent,
    QuotationPickupDetailListComponent,
    QuotationPickupDetailDeleteDialogComponent
],
entryComponents: [
    QuotationPickupDetailUpdateComponent,
    QuotationPickupDetailListComponent,
    QuotationPickupDetailDeleteDialogComponent
],
exports: [
    QuotationPickupDetailUpdateComponent,
    QuotationPickupDetailListComponent,
    QuotationPickupDetailDeleteDialogComponent
],
providers: [
    QuotationPickupDetailPopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixQuotationPickupDetailModule {}
