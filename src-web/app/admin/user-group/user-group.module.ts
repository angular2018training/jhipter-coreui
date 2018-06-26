import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';
import {
UserGroupComponent,
    UserGroupDetailComponent,
    UserGroupUpdateComponent,
    UserGroupDeletePopupComponent,
    UserGroupDeleteDialogComponent,
    userGroupRoute,
    userGroupPopupRoute,
    UserGroupPopupService
} from './';

const ENTITY_STATES = [
    ...userGroupRoute,
    ...userGroupPopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    UserGroupComponent,
    UserGroupDetailComponent,
    UserGroupUpdateComponent,
    UserGroupDeleteDialogComponent,
    UserGroupDeletePopupComponent,
],
    entryComponents: [
    UserGroupComponent,
    UserGroupUpdateComponent,
    UserGroupDeleteDialogComponent,
    UserGroupDeletePopupComponent,
],
providers: [
    UserGroupPopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixUserGroupModule {}
