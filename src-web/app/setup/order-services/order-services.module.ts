import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { NextlogixSharedModule } from '../../shared';
import {
OrderServicesComponent,
    OrderServicesDetailComponent,
    OrderServicesUpdateComponent,
    OrderServicesDeletePopupComponent,
    OrderServicesDeleteDialogComponent,
    orderServicesRoute,
    orderServicesPopupRoute,
    OrderServicesPopupService
} from './';

const ENTITY_STATES = [
    ...orderServicesRoute,
    ...orderServicesPopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    OrderServicesComponent,
    OrderServicesDetailComponent,
    OrderServicesUpdateComponent,
    OrderServicesDeleteDialogComponent,
    OrderServicesDeletePopupComponent,
],
    entryComponents: [
    OrderServicesComponent,
    OrderServicesUpdateComponent,
    OrderServicesDeleteDialogComponent,
    OrderServicesDeletePopupComponent,
],
providers: [
    OrderServicesPopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixOrderServicesModule {}
