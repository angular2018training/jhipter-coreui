import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';
import {
DetailFormComponent,
    DetailFormDetailComponent,
    DetailFormUpdateComponent,
    DetailFormDeletePopupComponent,
    DetailFormDeleteDialogComponent,
    detailFormRoute,
    detailFormPopupRoute,
    DetailFormPopupService
} from './';

const ENTITY_STATES = [
    ...detailFormRoute,
    ...detailFormPopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    DetailFormComponent,
    DetailFormDetailComponent,
    DetailFormUpdateComponent,
    DetailFormDeleteDialogComponent,
    DetailFormDeletePopupComponent,
],
    entryComponents: [
    DetailFormComponent,
    DetailFormUpdateComponent,
    DetailFormDeleteDialogComponent,
    DetailFormDeletePopupComponent,
],
providers: [
    DetailFormPopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixDetailFormModule {}
