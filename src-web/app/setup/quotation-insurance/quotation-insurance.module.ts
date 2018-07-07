import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { NextlogixSharedModule } from '../../shared';
import {
QuotationInsuranceComponent,
    QuotationInsuranceDetailComponent,
    QuotationInsuranceUpdateComponent,
    QuotationInsuranceDeletePopupComponent,
    QuotationInsuranceDeleteDialogComponent,
    quotationInsuranceRoute,
    quotationInsurancePopupRoute,
    QuotationInsurancePopupService
} from './';

const ENTITY_STATES = [
    ...quotationInsuranceRoute,
    ...quotationInsurancePopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    QuotationInsuranceComponent,
    QuotationInsuranceDetailComponent,
    QuotationInsuranceUpdateComponent,
    QuotationInsuranceDeleteDialogComponent,
    QuotationInsuranceDeletePopupComponent,
],
    entryComponents: [
    QuotationInsuranceComponent,
    QuotationInsuranceUpdateComponent,
    QuotationInsuranceDeleteDialogComponent,
    QuotationInsuranceDeletePopupComponent,
],
providers: [
    QuotationInsurancePopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixQuotationInsuranceModule {}
