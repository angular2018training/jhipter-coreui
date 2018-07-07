import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';
import {
    QuotationReturnDetailUpdateComponent,
    QuotationReturnDetailPopupService,
    QuotationReturnDetailListComponent,
    QuotationReturnDetailDeleteDialogComponent
} from './';



@NgModule({
    imports: [
        NextlogixSharedModule,
        RouterModule,

],
declarations: [
    QuotationReturnDetailUpdateComponent,
    QuotationReturnDetailListComponent,
    QuotationReturnDetailDeleteDialogComponent
],
entryComponents: [
    QuotationReturnDetailUpdateComponent,
    QuotationReturnDetailListComponent,
    QuotationReturnDetailDeleteDialogComponent
],
exports: [
    QuotationReturnDetailUpdateComponent,
    QuotationReturnDetailListComponent,
    QuotationReturnDetailDeleteDialogComponent
],
providers: [
    QuotationReturnDetailPopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixQuotationReturnDetailModule {}
