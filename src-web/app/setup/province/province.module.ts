import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';
import {
ProvinceComponent,
    ProvinceDetailComponent,
    ProvinceUpdateComponent,
    ProvinceDeletePopupComponent,
    ProvinceDeleteDialogComponent,
    provinceRoute,
    provincePopupRoute,
    ProvincePopupService
} from './';

const ENTITY_STATES = [
    ...provinceRoute,
    ...provincePopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    ProvinceComponent,
    ProvinceDetailComponent,
    ProvinceUpdateComponent,
    ProvinceDeleteDialogComponent,
    ProvinceDeletePopupComponent,
],
    entryComponents: [
    ProvinceComponent,
    ProvinceUpdateComponent,
    ProvinceDeleteDialogComponent,
    ProvinceDeletePopupComponent,
],
providers: [
    ProvincePopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixProvinceModule {}
