import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';
import {
    QuotationSubServicesDetailUpdateComponent,
    QuotationSubServicesDetailPopupService,
    QuotationSubServicesDetailListComponent,
    QuotationSubServicesDetailDeleteDialogComponent
} from './';



@NgModule({
    imports: [
        NextlogixSharedModule,
        RouterModule,

],
declarations: [
    QuotationSubServicesDetailUpdateComponent,
    QuotationSubServicesDetailListComponent,
    QuotationSubServicesDetailDeleteDialogComponent
],
entryComponents: [
    QuotationSubServicesDetailUpdateComponent,
    QuotationSubServicesDetailListComponent,
    QuotationSubServicesDetailDeleteDialogComponent
],
exports: [
    QuotationSubServicesDetailUpdateComponent,
    QuotationSubServicesDetailListComponent,
    QuotationSubServicesDetailDeleteDialogComponent
],
providers: [
    QuotationSubServicesDetailPopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixQuotationSubServicesDetailModule {}
