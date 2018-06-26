import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import { CommonModule } from '@angular/common';
import {NextlogixSharedLibsModule} from "../shared/shared-libs.module";
import {NextlogixCustomerModule} from "./customer/customer.module";
import {NextlogixCustomerLegalModule} from "./customer-legal/customer-legal.module";
import {NextlogixCustomerPaymentModule} from "./customer-payment/customer-payment.module";
import {NextlogixCustomerPostOfficeModule} from "./customer-post-office/customer-post-office.module";
import {NextlogixCustomerSourceModule} from "./customer-source/customer-source.module";
import {NextlogixCustomerTypeModule} from "./customer-type/customer-type.module";
import {NextlogixSetupModule} from "../setup/setup.module";
@NgModule({
  imports: [
    CommonModule,
    NextlogixSharedLibsModule,
    NextlogixCustomerModule,
    NextlogixCustomerLegalModule,
    NextlogixCustomerPaymentModule,
    NextlogixCustomerPostOfficeModule,
    NextlogixCustomerSourceModule,
    NextlogixCustomerTypeModule,
    NextlogixSetupModule
    //RouterModule.forChild(setupState)
  ],
  declarations: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixCustomerManagmentModule { }
