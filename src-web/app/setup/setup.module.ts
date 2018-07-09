import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import { CommonModule } from '@angular/common';
import {NextlogixProvinceModule} from './province/province.module';
import {NextlogixDistrictModule} from './district/district.module';
import {NextlogixSharedLibsModule} from '../shared/shared-libs.module';
import {NextlogixWardModule} from './ward/ward.module';
import {NextlogixWarehouseModule} from './warehouse/warehouse.module';
import {RouterModule} from '@angular/router';
import {setupState} from './setup.route';
import {JhiLanguageService} from 'ng-jhipster';
import {JhiLanguageHelper} from '../shared/language/language.helper';
import {NextlogixPostOfficeModule} from './post-office/post-office.module';
import {NextlogixOrderServicesModule} from './order-services/order-services.module';
import {NextlogixOrderServicesTypeModule} from './order-services-type/order-services-type.module';
import {NextlogixBankModule} from './bank/bank.module';
import {NextlogixPaymentTypeModule} from './payment-type/payment-type.module';
import {NextlogixDistrictTypeModule} from './district-type/district-type.module';
import {NextlogixOrderStatusModule} from './order-status/order-status.module';
import {NextlogixOrderSubServicesModule} from './order-sub-services/order-sub-services.module';
import {NextlogixOrderSubServicesTypeModule} from './order-sub-services-type/order-sub-services-type.module';
@NgModule({
  imports: [
    CommonModule,
    NextlogixProvinceModule,
    NextlogixDistrictModule,
    NextlogixWardModule,
    NextlogixWarehouseModule,
    NextlogixSharedLibsModule,
    NextlogixPostOfficeModule,
    NextlogixOrderServicesModule,
    NextlogixOrderServicesTypeModule,
    NextlogixBankModule,
    NextlogixPaymentTypeModule,
    NextlogixDistrictTypeModule,
    NextlogixOrderStatusModule,
    NextlogixOrderSubServicesModule,
    NextlogixOrderSubServicesTypeModule,
    RouterModule.forChild(setupState)
  ],
  declarations: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixSetupModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
