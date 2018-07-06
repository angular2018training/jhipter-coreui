import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { userGroupRoute, userGroupPopupRoute } from './user-group.route';
import { NextlogixSharedModule } from '../../shared';
import { NgSelectModule } from '@ng-select/ng-select';

import {
    UserGroupComponent,
    UserGroupDetailComponent,
    UserGroupUpdateComponent,
    UserGroupDeletePopupComponent,
    UserGroupDeleteDialogComponent,
    UserGroupPopupService,
} from './';

const ENTITY_STATES = [
    ...userGroupRoute,
    ...userGroupPopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
        RouterModule.forChild(ENTITY_STATES),
        NgSelectModule
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
export class NextlogixUserGroupModule { }
