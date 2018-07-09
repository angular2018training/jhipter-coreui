import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';
import {
OrderSubServicesTypeComponent,
    OrderSubServicesTypeDetailComponent,
    OrderSubServicesTypeUpdateComponent,
    OrderSubServicesTypeDeletePopupComponent,
    OrderSubServicesTypeDeleteDialogComponent,
    orderSubServicesTypeRoute,
    orderSubServicesTypePopupRoute,
    OrderSubServicesTypePopupService
} from './';

const ENTITY_STATES = [
    ...orderSubServicesTypeRoute,
    ...orderSubServicesTypePopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    OrderSubServicesTypeComponent,
    OrderSubServicesTypeDetailComponent,
    OrderSubServicesTypeUpdateComponent,
    OrderSubServicesTypeDeleteDialogComponent,
    OrderSubServicesTypeDeletePopupComponent,
],
    entryComponents: [
    OrderSubServicesTypeComponent,
    OrderSubServicesTypeUpdateComponent,
    OrderSubServicesTypeDeleteDialogComponent,
    OrderSubServicesTypeDeletePopupComponent,
],
providers: [
    OrderSubServicesTypePopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixOrderSubServicesTypeModule {}
