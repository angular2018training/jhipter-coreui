import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';
import {
    QuotationGiveBackDetailUpdateComponent,
    QuotationGiveBackDetailPopupService,
    QuotationGiveBackDetailListComponent,
    QuotationGiveBackDetailDeleteDialogComponent
} from './';



@NgModule({
    imports: [
        NextlogixSharedModule,
        RouterModule,

],
declarations: [
    QuotationGiveBackDetailUpdateComponent,
    QuotationGiveBackDetailListComponent,
    QuotationGiveBackDetailDeleteDialogComponent
],
entryComponents: [
    QuotationGiveBackDetailUpdateComponent,
    QuotationGiveBackDetailListComponent,
    QuotationGiveBackDetailDeleteDialogComponent
],
exports: [
    QuotationGiveBackDetailUpdateComponent,
    QuotationGiveBackDetailListComponent,
    QuotationGiveBackDetailDeleteDialogComponent
],
providers: [
    QuotationGiveBackDetailPopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixQuotationGiveBackDetailModule {}
