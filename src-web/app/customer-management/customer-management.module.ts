import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NextlogixSharedLibsModule } from '../shared/shared-libs.module';
import { CustomerManagementDetailComponent } from './customer-management-detail/customer-management-detail.component';
import { CustomerPaymentUpdateComponent } from './customer-payment/customer-payment-update.component';
import { RouterModule } from '@angular/router';
import { customerManagementState } from './customer-management.route';
import { CustomerUpdateComponent } from './customer/customer-update.component';
import { CustomerDeleteDialogComponent } from './customer/customer-delete-dialog.component';
import { CustomerPostOfficeDialogComponent } from './customer-post-office/customer-post-office-dialog.component';
import { CustomerPostOfficeComponent } from './customer-post-office/customer-post-office.component';
import { CustomerPostOfficeDeleteDialogComponent } from './customer-post-office/customer-post-office-delete-dialog.component';
import { CustomerManagementComponent } from './customer-management.component';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from '../shared';
import { CustomerLegalUpdateComponent } from './customer-legal/customer-legal-update.component';
import { CustomerLegalFileUploadComponent } from './customer-legal-file-upload/customer-legal-file-upload.component';
import { CustomerLegalFileUploadDeleteDialogComponent } from './customer-legal-file-upload/customer-legal-file-upload-delete-dialog.component';
import { FileUploadDialogComponent } from './customer-legal-file-upload/file-upload/file-upload-dialog.component';

@NgModule({
  imports: [
    CommonModule,
    NextlogixSharedLibsModule,
    // NextlogixCustomerLegalModule,
    RouterModule.forChild(customerManagementState)
  ],
  declarations: [
    CustomerManagementComponent,
    CustomerManagementDetailComponent,
    // customer
    CustomerUpdateComponent,
    CustomerDeleteDialogComponent,
    // payment
    CustomerPaymentUpdateComponent,
    // postoffice
    CustomerPostOfficeComponent,
    CustomerPostOfficeDialogComponent,
    CustomerPostOfficeDeleteDialogComponent,
    // legal
    CustomerLegalUpdateComponent,
    CustomerLegalFileUploadComponent,
    CustomerLegalFileUploadDeleteDialogComponent,
    FileUploadDialogComponent
  ],
  entryComponents: [
    CustomerDeleteDialogComponent,
    CustomerPostOfficeDialogComponent,
    CustomerPostOfficeDeleteDialogComponent,
    CustomerLegalFileUploadDeleteDialogComponent,
    FileUploadDialogComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixCustomerManagmentModule {

  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
