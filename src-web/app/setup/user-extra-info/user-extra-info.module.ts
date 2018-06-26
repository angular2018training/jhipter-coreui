import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';
import {
UserExtraInfoComponent,
    UserExtraInfoDetailComponent,
    UserExtraInfoUpdateComponent,
    UserExtraInfoDeletePopupComponent,
    UserExtraInfoDeleteDialogComponent,
    userExtraInfoRoute,
    userExtraInfoPopupRoute,
    UserExtraInfoPopupService
} from './';

const ENTITY_STATES = [
    ...userExtraInfoRoute,
    ...userExtraInfoPopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    UserExtraInfoComponent,
    UserExtraInfoDetailComponent,
    UserExtraInfoUpdateComponent,
    UserExtraInfoDeleteDialogComponent,
    UserExtraInfoDeletePopupComponent,
],
    entryComponents: [
    UserExtraInfoComponent,
    UserExtraInfoUpdateComponent,
    UserExtraInfoDeleteDialogComponent,
    UserExtraInfoDeletePopupComponent,
],
providers: [
    UserExtraInfoPopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixUserExtraInfoModule {}
