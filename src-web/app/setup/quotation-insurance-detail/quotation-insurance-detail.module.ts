import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';
import {
    QuotationInsuranceDetailUpdateComponent,
    QuotationInsuranceDetailPopupService,
    QuotationInsuranceDetailListComponent,
    QuotationInsuranceDetailDeleteDialogComponent
} from './';



@NgModule({
    imports: [
        NextlogixSharedModule,
        RouterModule,

],
declarations: [
    QuotationInsuranceDetailUpdateComponent,
    QuotationInsuranceDetailListComponent,
    QuotationInsuranceDetailDeleteDialogComponent
],
entryComponents: [
    QuotationInsuranceDetailUpdateComponent,
    QuotationInsuranceDetailListComponent,
    QuotationInsuranceDetailDeleteDialogComponent
],
exports: [
    QuotationInsuranceDetailUpdateComponent,
    QuotationInsuranceDetailListComponent,
    QuotationInsuranceDetailDeleteDialogComponent
],
providers: [
    QuotationInsuranceDetailPopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixQuotationInsuranceDetailModule {}
