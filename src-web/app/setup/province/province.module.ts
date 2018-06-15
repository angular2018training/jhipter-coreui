import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';
import {
    ProvinceService,
    ProvincePopupService,
    ProvinceComponent,
    ProvinceDetailComponent,
    ProvinceDialogComponent,
    ProvinceDeletePopupComponent,
    ProvinceDeleteDialogComponent,
    provinceRoute,
} from './';

const ENTITY_STATES = [
    ...provinceRoute
];

@NgModule({
    imports: [
        NextlogixSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ProvinceComponent,
        ProvinceDetailComponent,
        ProvinceDialogComponent,
        ProvinceDeleteDialogComponent,
        ProvinceDeletePopupComponent,
    ],
    entryComponents: [
        ProvinceComponent,
        ProvinceDialogComponent,
        ProvinceDeleteDialogComponent,
        ProvinceDeletePopupComponent,
    ],
    providers: [
        ProvinceService,
        ProvincePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixProvinceModule {}
