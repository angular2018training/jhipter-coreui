import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import { CommonModule } from '@angular/common';
import {NextlogixProvinceModule} from "./province/province.module";
import {NextlogixDistrictModule} from "./district/district.module";
import {NextlogixSharedLibsModule} from "../shared/shared-libs.module";
@NgModule({
  imports: [
    CommonModule,
    NextlogixProvinceModule,
    NextlogixDistrictModule,
    NextlogixSharedLibsModule
    //RouterModule.forChild(setupState)
  ],
  declarations: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixSetupModule { }
