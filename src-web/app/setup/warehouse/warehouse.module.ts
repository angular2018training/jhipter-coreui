import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';
import {
WarehouseComponent,
    WarehouseDetailComponent,
    WarehouseUpdateComponent,
    WarehouseDeletePopupComponent,
    WarehouseDeleteDialogComponent,
    warehouseRoute,
    warehousePopupRoute,
    WarehousePopupService
} from './';

const ENTITY_STATES = [
    ...warehouseRoute,
    ...warehousePopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    WarehouseComponent,
    WarehouseDetailComponent,
    WarehouseUpdateComponent,
    WarehouseDeleteDialogComponent,
    WarehouseDeletePopupComponent,
],
    entryComponents: [
    WarehouseComponent,
    WarehouseUpdateComponent,
    WarehouseDeleteDialogComponent,
    WarehouseDeletePopupComponent,
],
providers: [
    WarehousePopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixWarehouseModule {}
