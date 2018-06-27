import { JhiEventManager } from 'ng-jhipster';
import { HttpInterceptor, HttpRequest, HttpErrorResponse, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/do';
import { TranslateService, LangChangeEvent } from '@ngx-translate/core';
import {Injector} from "@angular/core";
import { ToasterModule, ToasterService, ToasterConfig }  from 'angular2-toaster/angular2-toaster';
export class ErrorHandlerInterceptor implements HttpInterceptor {
    private translateService: TranslateService;
    private toasterService: ToasterService;
    constructor(private eventManager: JhiEventManager,private injector: Injector) {
      setTimeout(() => {
        this.toasterService = injector.get(ToasterService);
        this.translateService = injector.get(TranslateService);
      });
    }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request).do((event: HttpEvent<any>) => {}, (err: any) => {
            if (err instanceof HttpErrorResponse) {
                if (!(err.status === 401 && (err.message === '' || (err.url && err.url.indexOf('/api/account') === 0)))) {
                    if (this.eventManager !== undefined) {
                      let i;
                      const httpErrorResponse = err;
                      switch (httpErrorResponse.status) {
                        // connection refused, server not reachable
                        case 0:
                          this.addErrorAlert('Server not reachable', 'error.server.not.reachable');
                          break;

                        case 400:
                          const arr = httpErrorResponse.headers.keys();
                          let errorHeader = null;
                          let entityKey = null;
                          arr.forEach((entry) => {
                            if (entry.endsWith('app-error')) {
                              errorHeader = httpErrorResponse.headers.get(entry);
                            } else if (entry.endsWith('app-params')) {
                              entityKey = httpErrorResponse.headers.get(entry);
                            }
                          });
                          if (errorHeader) {
                            const entityName = entityKey;
                            this.addErrorAlert(errorHeader, errorHeader, { entityName });
                          } else if (httpErrorResponse.error !== '' && httpErrorResponse.error.fieldErrors) {
                            const fieldErrors = httpErrorResponse.error.fieldErrors;
                            for (i = 0; i < fieldErrors.length; i++) {
                              const fieldError = fieldErrors[i];
                              // convert 'something[14].other[4].id' to 'something[].other[].id' so translations can be written to it
                              const convertedField = fieldError.field.replace(/\[\d*\]/g, '[]');
                              const fieldName = convertedField.charAt(0).toUpperCase() +
                                convertedField.slice(1);
                              this.addErrorAlert(
                                'Error on field "' + fieldName + '"', 'error.' + fieldError.message, { fieldName });
                            }
                          } else if (httpErrorResponse.error !== '' && httpErrorResponse.error.message) {
                            this.addErrorAlert(httpErrorResponse.error.message, httpErrorResponse.error.message, httpErrorResponse.error.params);
                          } else {
                            this.addErrorAlert(httpErrorResponse.error);
                          }
                          break;

                        case 404:
                          this.addErrorAlert('Not found', 'error.url.not.found');
                          break;

                        default:
                          if (httpErrorResponse.error !== '' && httpErrorResponse.error.message) {
                            this.addErrorAlert(httpErrorResponse.error.message);
                          } else {
                            this.addErrorAlert(httpErrorResponse.error);
                          }
                      }
                        //this.eventManager.broadcast({name: 'nextLogixApp.httpError', content: err});
                    }
                }
            }
        });
    }
  addErrorAlert(message, key?, data?) {
    if (this.toasterService) {
      let title = '';
      this.translateService.get('common.notify').toPromise().then(data => {
        title = data;
        if(key){
          this.translateService.get(key, {param: data}).toPromise().then(data => {
            this.toasterService.pop('error',title, data);
          }).catch(e =>{
            this.toasterService.pop('error',message, data);
          })
        }


      });

    }
    /*this.alerts.push(
     this.alertService.addAlert(
     {
     type: 'danger',
     msg: message,
     timeout: 5000,
     toast: this.alertService.isToast(),
     scoped: true
     },
     this.alerts
     )
     );*/
  }
}
