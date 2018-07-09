import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';
import {
WeightComponent,
    WeightDetailComponent,
    WeightUpdateComponent,
    WeightDeletePopupComponent,
    WeightDeleteDialogComponent,
    weightRoute,
    weightPopupRoute,
    WeightPopupService
} from './';

const ENTITY_STATES = [
    ...weightRoute,
    ...weightPopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    WeightComponent,
    WeightDetailComponent,
    WeightUpdateComponent,
    WeightDeleteDialogComponent,
    WeightDeletePopupComponent,
],
    entryComponents: [
    WeightComponent,
    WeightUpdateComponent,
    WeightDeleteDialogComponent,
    WeightDeletePopupComponent,
],
providers: [
    WeightPopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixWeightModule {}
