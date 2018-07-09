import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';
import {
DistrictTypeComponent,
    DistrictTypeDetailComponent,
    DistrictTypeUpdateComponent,
    DistrictTypeDeletePopupComponent,
    DistrictTypeDeleteDialogComponent,
    districtTypeRoute,
    districtTypePopupRoute,
    DistrictTypePopupService
} from './';

const ENTITY_STATES = [
    ...districtTypeRoute,
    ...districtTypePopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    DistrictTypeComponent,
    DistrictTypeDetailComponent,
    DistrictTypeUpdateComponent,
    DistrictTypeDeleteDialogComponent,
    DistrictTypeDeletePopupComponent,
],
    entryComponents: [
    DistrictTypeComponent,
    DistrictTypeUpdateComponent,
    DistrictTypeDeleteDialogComponent,
    DistrictTypeDeletePopupComponent,
],
providers: [
    DistrictTypePopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixDistrictTypeModule {}
