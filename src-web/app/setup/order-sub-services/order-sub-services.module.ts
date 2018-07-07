import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { NextlogixSharedModule } from '../../shared';
import {
OrderSubServicesComponent,
    OrderSubServicesDetailComponent,
    OrderSubServicesUpdateComponent,
    OrderSubServicesDeletePopupComponent,
    OrderSubServicesDeleteDialogComponent,
    orderSubServicesRoute,
    orderSubServicesPopupRoute,
    OrderSubServicesPopupService
} from './';

const ENTITY_STATES = [
    ...orderSubServicesRoute,
    ...orderSubServicesPopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    OrderSubServicesComponent,
    OrderSubServicesDetailComponent,
    OrderSubServicesUpdateComponent,
    OrderSubServicesDeleteDialogComponent,
    OrderSubServicesDeletePopupComponent,
],
    entryComponents: [
    OrderSubServicesComponent,
    OrderSubServicesUpdateComponent,
    OrderSubServicesDeleteDialogComponent,
    OrderSubServicesDeletePopupComponent,
],
providers: [
    OrderSubServicesPopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixOrderSubServicesModule {}
