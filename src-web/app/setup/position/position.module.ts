import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';
import {
PositionComponent,
    PositionDetailComponent,
    PositionUpdateComponent,
    PositionDeletePopupComponent,
    PositionDeleteDialogComponent,
    positionRoute,
    positionPopupRoute,
    PositionPopupService
} from './';

const ENTITY_STATES = [
    ...positionRoute,
    ...positionPopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    PositionComponent,
    PositionDetailComponent,
    PositionUpdateComponent,
    PositionDeleteDialogComponent,
    PositionDeletePopupComponent,
],
    entryComponents: [
    PositionComponent,
    PositionUpdateComponent,
    PositionDeleteDialogComponent,
    PositionDeletePopupComponent,
],
providers: [
    PositionPopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixPositionModule {}
