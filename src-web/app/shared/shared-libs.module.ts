import {ModuleWithProviders, NgModule} from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {JhiLanguageService, NgJhipsterModule} from 'ng-jhipster';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { CookieModule } from 'ngx-cookie';
import { ToasterModule, ToasterService} from 'angular2-toaster/angular2-toaster';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import {BsDatepickerModule, TimepickerModule} from "ngx-bootstrap";
@NgModule({
    imports: [
        NgbModule.forRoot(),
        NgJhipsterModule.forRoot({
          // set below to true to make alerts look like toast
          alertAsToast: true,
          i18nEnabled: true,
          defaultI18nLang: 'en',
          alertTimeout :60000

        }),
        InfiniteScrollModule,
        CookieModule.forRoot(),
        TimepickerModule.forRoot(),
        BsDatepickerModule.forRoot(),
        CommonModule,
        FontAwesomeModule
    ],
    exports: [
        FormsModule,
        //HttpClientModule,
        CommonModule,
        NgbModule,
        NgJhipsterModule,
        InfiniteScrollModule
    ]
})
export class NextlogixSharedLibsModule {
  static forRoot() : ModuleWithProviders {
    return {
      ngModule: NextlogixSharedLibsModule                 //<<<====here
    };
  }
}
