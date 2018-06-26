import { BrowserModule } from '@angular/platform-browser';
import {Injector, NgModule} from '@angular/core';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';

import { PerfectScrollbarModule } from 'ngx-perfect-scrollbar';
import { PERFECT_SCROLLBAR_CONFIG } from 'ngx-perfect-scrollbar';
import { PerfectScrollbarConfigInterface } from 'ngx-perfect-scrollbar';

const DEFAULT_PERFECT_SCROLLBAR_CONFIG: PerfectScrollbarConfigInterface = {
  suppressScrollX: true
};

import { AppComponent } from './app.component';
import './vendor.ts';
// Import containers
import { DefaultLayoutComponent } from './containers';

import { P404Component } from './views/error/404.component';
import { P500Component } from './views/error/500.component';
import { LoginComponent } from './views/login/login.component';
import { RegisterComponent } from './views/register/register.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
const APP_CONTAINERS = [
  DefaultLayoutComponent
];

import {
  AppAsideModule,
  AppBreadcrumbModule,
  AppHeaderModule,
  AppFooterModule,
  AppSidebarModule,
} from '@coreui/angular'

import { AuthInterceptor } from './blocks/interceptor/auth.interceptor';
// Import routing module
import { AppRoutingModule } from './app.routing';

// Import 3rd party components
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { TabsModule } from 'ngx-bootstrap/tabs';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import {NextlogixSharedModule} from "./shared/shared.module";
import {LocalStorageService, Ng2Webstorage, SessionStorageService} from "ngx-webstorage";
import {ProfileService} from "./layouts/profiles/profile.service";
import {PaginationConfig} from "ngx-bootstrap";
import {UserRouteAccessService} from "./shared/auth/user-route-access-service";
import {AuthExpiredInterceptor} from "./blocks/interceptor/auth-expired.interceptor";
import {JhiEventManager, NgJhipsterModule} from "ng-jhipster";
import {ErrorHandlerInterceptor} from "./blocks/interceptor/errorhandler.interceptor";
import {NotificationInterceptor} from "./blocks/interceptor/notification.interceptor";
import {NextlogixAdminModule} from "./admin/admin.module";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {NextlogixErrorModule} from "./layouts/error/error.module";
import {NextlogixSharedLibsModule} from "./shared/shared-libs.module";
import {NextlogixSetupModule} from "./setup/setup.module";
import {NextlogixCustomerManagmentModule} from "./customer-management/customer-management.module";

@NgModule({
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    AppAsideModule,
    AppBreadcrumbModule.forRoot(),
    AppFooterModule,
    AppHeaderModule,
    AppSidebarModule,
    PerfectScrollbarModule,
    BsDropdownModule.forRoot(),
    TabsModule.forRoot(),
    ChartsModule,
    NextlogixSharedModule,
    NgbModule.forRoot(),
    NextlogixErrorModule,
    NextlogixSharedLibsModule.forRoot(),
    NextlogixSetupModule,

    NextlogixAdminModule,
    NextlogixCustomerManagmentModule,
    Ng2Webstorage.forRoot({ prefix: 'jhi', separator: '-'}),
  ],
  declarations: [
    AppComponent,
    ...APP_CONTAINERS,
    P404Component,
    P500Component,
    LoginComponent,
    RegisterComponent
  ],
  providers: [{
    provide: LocationStrategy,
    useClass: HashLocationStrategy
  },
    ProfileService,
    PaginationConfig,
    UserRouteAccessService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
      deps: [
        LocalStorageService,
        SessionStorageService
      ]
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthExpiredInterceptor,
      multi: true,
      deps: [
        Injector
      ]
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ErrorHandlerInterceptor,
      multi: true,
      deps: [
        JhiEventManager
      ]
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: NotificationInterceptor,
      multi: true,
      deps: [
        Injector
      ]
    }],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
