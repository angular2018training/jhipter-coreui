import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import {AlertService} from '../../shared/alert/alert-service';
import { NextlogixSharedModule } from '../../shared';
import {
OrderServicesTypeComponent,
    OrderServicesTypeDetailComponent,
    OrderServicesTypeUpdateComponent,
    OrderServicesTypeDeletePopupComponent,
    OrderServicesTypeDeleteDialogComponent,
    orderServicesTypeRoute,
    orderServicesTypePopupRoute,
    OrderServicesTypePopupService
} from './';

const ENTITY_STATES = [
    ...orderServicesTypeRoute,
    ...orderServicesTypePopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    OrderServicesTypeComponent,
    OrderServicesTypeDetailComponent,
    OrderServicesTypeUpdateComponent,
    OrderServicesTypeDeleteDialogComponent,
    OrderServicesTypeDeletePopupComponent,
],
    entryComponents: [
    OrderServicesTypeComponent,
    OrderServicesTypeUpdateComponent,
    OrderServicesTypeDeleteDialogComponent,
    OrderServicesTypeDeletePopupComponent,
],
providers: [
    OrderServicesTypePopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixOrderServicesTypeModule {}
