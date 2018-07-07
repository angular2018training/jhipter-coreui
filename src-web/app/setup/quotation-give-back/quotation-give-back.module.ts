import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { NextlogixSharedModule } from '../../shared';
import {
QuotationGiveBackComponent,
    QuotationGiveBackDetailComponent,
    QuotationGiveBackUpdateComponent,
    QuotationGiveBackDeletePopupComponent,
    QuotationGiveBackDeleteDialogComponent,
    quotationGiveBackRoute,
    quotationGiveBackPopupRoute,
    QuotationGiveBackPopupService
} from './';

const ENTITY_STATES = [
    ...quotationGiveBackRoute,
    ...quotationGiveBackPopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    QuotationGiveBackComponent,
    QuotationGiveBackDetailComponent,
    QuotationGiveBackUpdateComponent,
    QuotationGiveBackDeleteDialogComponent,
    QuotationGiveBackDeletePopupComponent,
],
    entryComponents: [
    QuotationGiveBackComponent,
    QuotationGiveBackUpdateComponent,
    QuotationGiveBackDeleteDialogComponent,
    QuotationGiveBackDeletePopupComponent,
],
providers: [
    QuotationGiveBackPopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixQuotationGiveBackModule {}
