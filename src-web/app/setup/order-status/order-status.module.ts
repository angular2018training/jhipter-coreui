import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';
import {
OrderStatusComponent,
    OrderStatusDetailComponent,
    OrderStatusUpdateComponent,
    OrderStatusDeletePopupComponent,
    OrderStatusDeleteDialogComponent,
    orderStatusRoute,
    orderStatusPopupRoute,
    OrderStatusPopupService
} from './';

const ENTITY_STATES = [
    ...orderStatusRoute,
    ...orderStatusPopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    OrderStatusComponent,
    OrderStatusDetailComponent,
    OrderStatusUpdateComponent,
    OrderStatusDeleteDialogComponent,
    OrderStatusDeletePopupComponent,
],
    entryComponents: [
    OrderStatusComponent,
    OrderStatusUpdateComponent,
    OrderStatusDeleteDialogComponent,
    OrderStatusDeletePopupComponent,
],
providers: [
    OrderStatusPopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixOrderStatusModule {}
