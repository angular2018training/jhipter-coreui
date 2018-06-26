import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';
import {
CustomerSourceComponent,
    CustomerSourceDetailComponent,
    CustomerSourceUpdateComponent,
    CustomerSourceDeletePopupComponent,
    CustomerSourceDeleteDialogComponent,
    customerSourceRoute,
    customerSourcePopupRoute,
    CustomerSourcePopupService
} from './';

const ENTITY_STATES = [
    ...customerSourceRoute,
    ...customerSourcePopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    CustomerSourceComponent,
    CustomerSourceDetailComponent,
    CustomerSourceUpdateComponent,
    CustomerSourceDeleteDialogComponent,
    CustomerSourceDeletePopupComponent,
],
    entryComponents: [
    CustomerSourceComponent,
    CustomerSourceUpdateComponent,
    CustomerSourceDeleteDialogComponent,
    CustomerSourceDeletePopupComponent,
],
providers: [
    CustomerSourcePopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixCustomerSourceModule {}
