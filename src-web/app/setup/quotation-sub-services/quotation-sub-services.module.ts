import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { NextlogixSharedModule } from '../../shared';
import {
QuotationSubServicesComponent,
    QuotationSubServicesDetailComponent,
    QuotationSubServicesUpdateComponent,
    QuotationSubServicesDeletePopupComponent,
    QuotationSubServicesDeleteDialogComponent,
    quotationSubServicesRoute,
    quotationSubServicesPopupRoute,
    QuotationSubServicesPopupService
} from './';

const ENTITY_STATES = [
    ...quotationSubServicesRoute,
    ...quotationSubServicesPopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    QuotationSubServicesComponent,
    QuotationSubServicesDetailComponent,
    QuotationSubServicesUpdateComponent,
    QuotationSubServicesDeleteDialogComponent,
    QuotationSubServicesDeletePopupComponent,
],
    entryComponents: [
    QuotationSubServicesComponent,
    QuotationSubServicesUpdateComponent,
    QuotationSubServicesDeleteDialogComponent,
    QuotationSubServicesDeletePopupComponent,
],
providers: [
    QuotationSubServicesPopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixQuotationSubServicesModule {}
