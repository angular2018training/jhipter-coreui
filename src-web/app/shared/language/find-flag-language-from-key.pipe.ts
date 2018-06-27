import { Pipe, PipeTransform } from '@angular/core';

@Pipe({name: 'findFlagLanguageFromKey'})
export class FindFlagLanguageFromKeyPipe implements PipeTransform {
  private languages: any = {
    'en': { name: 'English', flagClass:'flag-icon flag-icon-gb h5'},
    'vi': { name: 'Tiếng Việt',flagClass:'flag-icon flag-icon-vn h5' }
    // jhipster-needle-i18n-language-key-pipe - JHipster will add/remove languages in this object
  };
  transform(lang: string): string {
    return this.languages[lang].flagClass;
  }
}
