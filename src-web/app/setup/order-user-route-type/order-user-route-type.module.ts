import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';
import {
OrderUserRouteTypeComponent,
    OrderUserRouteTypeDetailComponent,
    OrderUserRouteTypeUpdateComponent,
    OrderUserRouteTypeDeletePopupComponent,
    OrderUserRouteTypeDeleteDialogComponent,
    orderUserRouteTypeRoute,
    orderUserRouteTypePopupRoute,
    OrderUserRouteTypePopupService
} from './';

const ENTITY_STATES = [
    ...orderUserRouteTypeRoute,
    ...orderUserRouteTypePopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    OrderUserRouteTypeComponent,
    OrderUserRouteTypeDetailComponent,
    OrderUserRouteTypeUpdateComponent,
    OrderUserRouteTypeDeleteDialogComponent,
    OrderUserRouteTypeDeletePopupComponent,
],
    entryComponents: [
    OrderUserRouteTypeComponent,
    OrderUserRouteTypeUpdateComponent,
    OrderUserRouteTypeDeleteDialogComponent,
    OrderUserRouteTypeDeletePopupComponent,
],
providers: [
    OrderUserRouteTypePopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixOrderUserRouteTypeModule {}
