import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

/* jhipster-needle-add-admin-module-import - JHipster will add admin modules imports here */

import {
  errorRoute

} from './';

import {NextlogixSharedModule} from "../../shared/shared.module";
import {ErrorComponent} from "./error.component";

@NgModule({
  imports: [

    NextlogixSharedModule,
    RouterModule.forChild(errorRoute)
    /* jhipster-needle-add-admin-module - JHipster will add admin modules here */
  ],
  declarations: [
    ErrorComponent
  ],
  entryComponents: [
    ErrorComponent
  ],
  providers: [
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixErrorModule {}
