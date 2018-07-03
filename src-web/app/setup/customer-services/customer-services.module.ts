import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import {AlertService} from '../../shared/alert/alert-service';
import { NextlogixSharedModule } from '../../shared';
import {
CustomerServicesComponent,
    CustomerServicesDetailComponent,
    CustomerServicesUpdateComponent,
    CustomerServicesDeletePopupComponent,
    CustomerServicesDeleteDialogComponent,
    customerServicesRoute,
    customerServicesPopupRoute,
    CustomerServicesPopupService
} from './';

const ENTITY_STATES = [
    ...customerServicesRoute,
    ...customerServicesPopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    CustomerServicesComponent,
    CustomerServicesDetailComponent,
    CustomerServicesUpdateComponent,
    CustomerServicesDeleteDialogComponent,
    CustomerServicesDeletePopupComponent,
],
    entryComponents: [
    CustomerServicesComponent,
    CustomerServicesUpdateComponent,
    CustomerServicesDeleteDialogComponent,
    CustomerServicesDeletePopupComponent,
],
providers: [
    CustomerServicesPopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixCustomerServicesModule {}
