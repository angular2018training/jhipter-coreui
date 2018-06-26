import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';
import {
UserPositionComponent,
    UserPositionDetailComponent,
    UserPositionUpdateComponent,
    UserPositionDeletePopupComponent,
    UserPositionDeleteDialogComponent,
    userPositionRoute,
    userPositionPopupRoute,
    UserPositionPopupService
} from './';

const ENTITY_STATES = [
    ...userPositionRoute,
    ...userPositionPopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    UserPositionComponent,
    UserPositionDetailComponent,
    UserPositionUpdateComponent,
    UserPositionDeleteDialogComponent,
    UserPositionDeletePopupComponent,
],
    entryComponents: [
    UserPositionComponent,
    UserPositionUpdateComponent,
    UserPositionDeleteDialogComponent,
    UserPositionDeletePopupComponent,
],
providers: [
    UserPositionPopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixUserPositionModule {}
