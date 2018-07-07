import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';
import {
    QuotationCodDetailUpdateComponent,
    QuotationCodDetailPopupService,
    QuotationCodDetailListComponent,
    QuotationCodDetailDeleteDialogComponent
} from './';



@NgModule({
    imports: [
        NextlogixSharedModule,
        RouterModule,

],
declarations: [
    QuotationCodDetailUpdateComponent,
    QuotationCodDetailListComponent,
    QuotationCodDetailDeleteDialogComponent
],
entryComponents: [
    QuotationCodDetailUpdateComponent,
    QuotationCodDetailListComponent,
    QuotationCodDetailDeleteDialogComponent
],
exports: [
    QuotationCodDetailUpdateComponent,
    QuotationCodDetailListComponent,
    QuotationCodDetailDeleteDialogComponent
],
providers: [
    QuotationCodDetailPopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixQuotationCodDetailModule {}
