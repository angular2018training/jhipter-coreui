import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';
import {
CustomerComponent,
    CustomerDetailComponent,
    CustomerUpdateComponent,
    CustomerDeletePopupComponent,
    CustomerDeleteDialogComponent,
    customerRoute,
    customerPopupRoute,
    CustomerPopupService
} from './';

const ENTITY_STATES = [
    ...customerRoute,
    ...customerPopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    CustomerComponent,
    CustomerDetailComponent,
    CustomerUpdateComponent,
    CustomerDeleteDialogComponent,
    CustomerDeletePopupComponent,
],
    entryComponents: [
    CustomerComponent,
    CustomerUpdateComponent,
    CustomerDeleteDialogComponent,
    CustomerDeletePopupComponent,
],
providers: [
    CustomerPopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixCustomerModule {}
