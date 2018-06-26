import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NextlogixSharedModule } from '../../shared';
import {
CompanyComponent,
    CompanyDetailComponent,
    CompanyUpdateComponent,
    CompanyDeletePopupComponent,
    CompanyDeleteDialogComponent,
    companyRoute,
    companyPopupRoute,
    CompanyPopupService
} from './';

const ENTITY_STATES = [
    ...companyRoute,
    ...companyPopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    CompanyComponent,
    CompanyDetailComponent,
    CompanyUpdateComponent,
    CompanyDeleteDialogComponent,
    CompanyDeletePopupComponent,
],
    entryComponents: [
    CompanyComponent,
    CompanyUpdateComponent,
    CompanyDeleteDialogComponent,
    CompanyDeletePopupComponent,
],
providers: [
    CompanyPopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixCompanyModule {}
