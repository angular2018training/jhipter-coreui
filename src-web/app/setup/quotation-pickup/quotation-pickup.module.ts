import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { NextlogixSharedModule } from '../../shared';
import {
QuotationPickupComponent,
    QuotationPickupDetailComponent,
    QuotationPickupUpdateComponent,
    QuotationPickupDeletePopupComponent,
    QuotationPickupDeleteDialogComponent,
    quotationPickupRoute,
    quotationPickupPopupRoute,
    QuotationPickupPopupService
} from './';

const ENTITY_STATES = [
    ...quotationPickupRoute,
    ...quotationPickupPopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    QuotationPickupComponent,
    QuotationPickupDetailComponent,
    QuotationPickupUpdateComponent,
    QuotationPickupDeleteDialogComponent,
    QuotationPickupDeletePopupComponent,
],
    entryComponents: [
    QuotationPickupComponent,
    QuotationPickupUpdateComponent,
    QuotationPickupDeleteDialogComponent,
    QuotationPickupDeletePopupComponent,
],
providers: [
    QuotationPickupPopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixQuotationPickupModule {}
