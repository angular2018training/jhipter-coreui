import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';
import {
CustomerTypeComponent,
    CustomerTypeDetailComponent,
    CustomerTypeUpdateComponent,
    CustomerTypeDeletePopupComponent,
    CustomerTypeDeleteDialogComponent,
    customerTypeRoute,
    customerTypePopupRoute,
    CustomerTypePopupService
} from './';

const ENTITY_STATES = [
    ...customerTypeRoute,
    ...customerTypePopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    CustomerTypeComponent,
    CustomerTypeDetailComponent,
    CustomerTypeUpdateComponent,
    CustomerTypeDeleteDialogComponent,
    CustomerTypeDeletePopupComponent,
],
    entryComponents: [
    CustomerTypeComponent,
    CustomerTypeUpdateComponent,
    CustomerTypeDeleteDialogComponent,
    CustomerTypeDeletePopupComponent,
],
providers: [
    CustomerTypePopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixCustomerTypeModule {}
