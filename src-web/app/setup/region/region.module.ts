import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';
import {
RegionComponent,
    RegionDetailComponent,
    RegionUpdateComponent,
    RegionDeletePopupComponent,
    RegionDeleteDialogComponent,
    regionRoute,
    regionPopupRoute,
    RegionPopupService
} from './';

const ENTITY_STATES = [
    ...regionRoute,
    ...regionPopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    RegionComponent,
    RegionDetailComponent,
    RegionUpdateComponent,
    RegionDeleteDialogComponent,
    RegionDeletePopupComponent,
],
    entryComponents: [
    RegionComponent,
    RegionUpdateComponent,
    RegionDeleteDialogComponent,
    RegionDeletePopupComponent,
],
providers: [
    RegionPopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixRegionModule {}
