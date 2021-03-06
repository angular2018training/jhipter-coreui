import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { DatePipe } from '@angular/common';
import { NextlogixSharedLibsModule } from './shared-libs.module';
import { NextlogixSharedCommonModule } from './shared-common.module';
import { HasAnyAuthorityDirective } from './auth/has-any-authority.directive';
import { LoginService } from './login/login.service';
import { AccountService } from './auth/account.service';
import { StateStorageService } from './auth/state-storage.service';
import { Principal } from './auth/principal.service';
import { CSRFService } from './auth/csrf.service';
import { AuthServerProvider } from './auth/auth-jwt.service';
import { UserService } from './user/user.service';
import { FilterPipe } from './pipes/filter.pipe';

@NgModule({
    imports: [
        NextlogixSharedLibsModule,
        NextlogixSharedCommonModule
    ],
    declarations: [
        HasAnyAuthorityDirective,
        FilterPipe
    ],
    providers: [
        LoginService,
        AccountService,
        StateStorageService,
        Principal,
        CSRFService,
        AuthServerProvider,
        UserService,
        DatePipe,
        FilterPipe
    ],
    entryComponents: [],
    exports: [
        NextlogixSharedCommonModule,
        HasAnyAuthorityDirective,
        DatePipe,
        FilterPipe
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]

})
export class NextlogixSharedModule { }
