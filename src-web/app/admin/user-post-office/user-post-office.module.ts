import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { NextlogixSharedModule } from '../../shared';
import {
UserPostOfficeComponent,
    UserPostOfficeDetailComponent,
    UserPostOfficeUpdateComponent,
    UserPostOfficeDeletePopupComponent,
    UserPostOfficeDeleteDialogComponent,
    userPostOfficeRoute,
    userPostOfficePopupRoute,
    UserPostOfficePopupService
} from './';

const ENTITY_STATES = [
    ...userPostOfficeRoute,
    ...userPostOfficePopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    UserPostOfficeComponent,
    UserPostOfficeDetailComponent,
    UserPostOfficeUpdateComponent,
    UserPostOfficeDeleteDialogComponent,
    UserPostOfficeDeletePopupComponent,
],
    entryComponents: [
    UserPostOfficeComponent,
    UserPostOfficeUpdateComponent,
    UserPostOfficeDeleteDialogComponent,
    UserPostOfficeDeletePopupComponent,
],
providers: [
    UserPostOfficePopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixUserPostOfficeModule {}
