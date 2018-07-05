import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { NextlogixSharedModule } from '../../shared';
import {
DistrictComponent,
    DistrictDetailComponent,
    DistrictUpdateComponent,
    DistrictDeletePopupComponent,
    DistrictDeleteDialogComponent,
    districtRoute,
    districtPopupRoute,
    DistrictPopupService
} from './';

const ENTITY_STATES = [
    ...districtRoute,
    ...districtPopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    DistrictComponent,
    DistrictDetailComponent,
    DistrictUpdateComponent,
    DistrictDeleteDialogComponent,
    DistrictDeletePopupComponent,
],
    entryComponents: [
    DistrictComponent,
    DistrictUpdateComponent,
    DistrictDeleteDialogComponent,
    DistrictDeletePopupComponent,
],
providers: [
    DistrictPopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixDistrictModule {}
