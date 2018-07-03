import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { NextlogixSharedModule } from '../../shared';
import {
QuotationComponent,
    QuotationDetailComponent,
    QuotationUpdateComponent,
    QuotationDeletePopupComponent,
    QuotationDeleteDialogComponent,
    quotationRoute,
    quotationPopupRoute,
    QuotationPopupService
} from './';

const ENTITY_STATES = [
    ...quotationRoute,
    ...quotationPopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    QuotationComponent,
    QuotationDetailComponent,
    QuotationUpdateComponent,
    QuotationDeleteDialogComponent,
    QuotationDeletePopupComponent,
],
    entryComponents: [
    QuotationComponent,
    QuotationUpdateComponent,
    QuotationDeleteDialogComponent,
    QuotationDeletePopupComponent,
],
providers: [
    QuotationPopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixQuotationModule {}
