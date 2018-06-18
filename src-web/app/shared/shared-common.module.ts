import { NgModule, LOCALE_ID } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { registerLocaleData } from '@angular/common';
import locale from '@angular/common/locales/en';

import {FindLanguageFromKeyPipe} from "./language/find-language-from-key.pipe";
import {JhiLanguageHelper} from "./language/language.helper";
import {NextlogixSharedLibsModule} from "./shared-libs.module";
import {JhiAlertComponent} from "./alert/alert.component";
import {JhiAlertErrorComponent} from "./alert/alert-error.component";

@NgModule({
    imports: [
        NextlogixSharedLibsModule
    ],
    declarations: [
        FindLanguageFromKeyPipe,
        JhiAlertComponent,
        JhiAlertErrorComponent
    ],
    providers: [
        JhiLanguageHelper,
        Title,
        {
            provide: LOCALE_ID,
            useValue: 'en'
        },
    ],
    exports: [
        NextlogixSharedLibsModule,
        FindLanguageFromKeyPipe,
        JhiAlertComponent,
        JhiAlertErrorComponent
    ]
})
export class NextlogixSharedCommonModule {
    constructor() {
        registerLocaleData(locale);
    }
}
