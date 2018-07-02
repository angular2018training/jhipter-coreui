import { Injectable } from '@angular/core';
import {ToasterService} from "angular2-toaster";

import { TranslateService, LangChangeEvent } from '@ngx-translate/core';
@Injectable({
  providedIn: 'root'
})
export class AlertService {

  constructor(private toasterService: ToasterService,private translateService: TranslateService) { }

  public  error(message : string, key?, data?) {
    if (this.toasterService) {
      let title = '';
      this.translateService.get('common.notify').toPromise().then(data => {
        title = data;
        if (key) {
          this.translateService.get(key, {param: data}).toPromise().then(data => {
            this.toasterService.pop('error', title, data);
          }).catch(e => {
            this.toasterService.pop('error', message, data);
          })
        }else {
          this.toasterService.pop('error', message, data);
        }


      });
    }
  }
}
