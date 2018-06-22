import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';
import {
WardComponent,
    WardDetailComponent,
    WardUpdateComponent,
    WardDeletePopupComponent,
    WardDeleteDialogComponent,
    wardRoute,
    wardPopupRoute,
    WardPopupService
} from './';

const ENTITY_STATES = [
    ...wardRoute,
    ...wardPopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
    RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    WardComponent,
    WardDetailComponent,
    WardUpdateComponent,
    WardDeleteDialogComponent,
    WardDeletePopupComponent,
],
    entryComponents: [
    WardComponent,
    WardUpdateComponent,
    WardDeleteDialogComponent,
    WardDeletePopupComponent,
],
providers: [
    WardPopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixWardModule {}
