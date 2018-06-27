import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import { CommonModule } from '@angular/common';
import {NextlogixProvinceModule} from "./province/province.module";
import {NextlogixDistrictModule} from "./district/district.module";
import {NextlogixSharedLibsModule} from "../shared/shared-libs.module";
import {NextlogixWardModule} from "./ward/ward.module";
import {NextlogixWarehouseModule} from "./warehouse/warehouse.module";
import {RouterModule} from "@angular/router";
import {setupState} from "./setup.route";
import {JhiLanguageService} from "ng-jhipster";
import {JhiLanguageHelper} from "../shared/language/language.helper";
@NgModule({
  imports: [
    CommonModule,
    NextlogixProvinceModule,
    NextlogixDistrictModule,
    NextlogixWardModule,
    NextlogixWarehouseModule,
    NextlogixSharedLibsModule,
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
