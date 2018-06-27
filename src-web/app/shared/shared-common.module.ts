import { NgModule, LOCALE_ID } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { registerLocaleData } from '@angular/common';
import locale from '@angular/common/locales/en';

import {FindLanguageFromKeyPipe} from "./language/find-language-from-key.pipe";
import {NextlogixSharedLibsModule} from "./shared-libs.module";
import {JhiAlertComponent} from "./alert/alert.component";
import {JhiAlertErrorComponent} from "./alert/alert-error.component";
import {FindFlagLanguageFromKeyPipe} from "./language/find-flag-language-from-key.pipe";

@NgModule({
    imports: [
        NextlogixSharedLibsModule
    ],
    declarations: [
        FindLanguageFromKeyPipe,
        FindFlagLanguageFromKeyPipe,
        JhiAlertComponent,
        JhiAlertErrorComponent
    ],
    providers: [
        Title,
        {
            provide: LOCALE_ID,
            useValue: 'en'
        },
    ],
    exports: [
        NextlogixSharedLibsModule,
        FindLanguageFromKeyPipe,
        FindFlagLanguageFromKeyPipe,
        JhiAlertComponent,
        JhiAlertErrorComponent
    ]
})
export class NextlogixSharedCommonModule {
    constructor() {
        registerLocaleData(locale);
    }
}
