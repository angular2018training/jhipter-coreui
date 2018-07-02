import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { userGroupRoute, userGroupPopupRoute } from './user-group.route';
import { NextlogixSharedModule } from '../../shared';

import {
    UserGroupComponent,
    UserGroupDetailComponent,
    UserGroupUpdateComponent,
    UserGroupDeletePopupComponent,
    UserGroupDeleteDialogComponent,
    UserGroupPopupService,
    DataFilterPipe
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
        DataFilterPipe
    ],
    entryComponents: [
        UserGroupComponent,
        UserGroupUpdateComponent,
        UserGroupDeleteDialogComponent,
        UserGroupDeletePopupComponent,
    ],
    providers: [
        UserGroupPopupService,
        DataFilterPipe
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixUserGroupModule { }
