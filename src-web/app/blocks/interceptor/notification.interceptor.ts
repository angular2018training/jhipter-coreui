import { JhiAlertService } from 'ng-jhipster';
import { HttpInterceptor, HttpRequest, HttpResponse, HttpHandler, HttpEvent } from '@angular/common/http';
import { Injector } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/do';
import { ToasterModule, ToasterService, ToasterConfig }  from 'angular2-toaster/angular2-toaster';
import { TranslateService, LangChangeEvent } from '@ngx-translate/core';

export class NotificationInterceptor implements HttpInterceptor {

    private alertService: JhiAlertService;
    private toasterService: ToasterService;
    private translateService: TranslateService;
    // tslint:disable-next-line: no-unused-variable
    constructor(private injector: Injector) {
        setTimeout(() => {
          //this.alertService = injector.get(JhiAlertService);
          this.toasterService = injector.get(ToasterService);
          this.translateService = injector.get(TranslateService);
        });
    }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request).do((event: HttpEvent<any>) => {
            if (event instanceof HttpResponse) {
                const arr = event.headers.keys();
                let alert = null;
                let alertParams = [];
                arr.forEach((entry) => {
                  if (entry.toLowerCase().endsWith('app-alert')) {
                    alert = event.headers.get(entry);
                  } else if (entry.toLowerCase().endsWith('app-params')) {
                    alertParams.push(event.headers.get(entry));
                  }
                });
                if (alert) {
                    if (typeof alert === 'string') {
                        if (this.toasterService) {
                           let title = '';
                           this.translateService.get('common.notify').toPromise().then(data => {
                             title = data;
                             this.translateService.get(alert, {param: alertParams}).toPromise().then(data => {
                               this.toasterService.pop('success',title, data);
                             })

                           });

                        }
                    }
                }
            }
        }, (err: any) => {});
    }
}
