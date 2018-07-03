import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { NextlogixSharedModule } from '../../shared';
import {
QuotationItemComponent,
    QuotationItemDetailComponent,
    QuotationItemUpdateComponent,
    QuotationItemDeletePopupComponent,
    QuotationItemDeleteDialogComponent,
    quotationItemRoute,
    quotationItemPopupRoute,
    QuotationItemPopupService
} from './';

const ENTITY_STATES = [
    ...quotationItemRoute,
    ...quotationItemPopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    QuotationItemComponent,
    QuotationItemDetailComponent,
    QuotationItemUpdateComponent,
    QuotationItemDeleteDialogComponent,
    QuotationItemDeletePopupComponent,
],
    entryComponents: [
    QuotationItemComponent,
    QuotationItemUpdateComponent,
    QuotationItemDeleteDialogComponent,
    QuotationItemDeletePopupComponent,
],
providers: [
    QuotationItemPopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixQuotationItemModule {}
