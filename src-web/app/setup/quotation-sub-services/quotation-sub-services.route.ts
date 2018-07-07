import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
    import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { QuotationSubServices } from '../../shared/model/quotation-sub-services.model';
import { QuotationSubServicesService } from '../../shared/service/quotation-sub-services.service';
import { QuotationSubServicesComponent } from './quotation-sub-services.component';
import { QuotationSubServicesDetailComponent } from './quotation-sub-services-detail.component';
import { QuotationSubServicesUpdateComponent } from './quotation-sub-services-update.component';
    import { QuotationSubServicesDeletePopupComponent } from './quotation-sub-services-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class QuotationSubServicesResolve implements Resolve<QuotationSubServices> {

        constructor(private service: QuotationSubServicesService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((quotationSubServices: HttpResponse<QuotationSubServices>) => quotationSubServices.body);
    }
    return Observable.of(new QuotationSubServices());
}
}


export const quotationSubServicesRoute: Routes = [
    {
        path: 'quotation-sub-services',
        component: QuotationSubServicesComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.quotationSubServices.home.title',
    title : 'nextlogixApp.quotationSubServices.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'quotation-sub-services/:id/view',
        component: QuotationSubServicesDetailComponent,
        resolve: {
        quotationSubServices: QuotationSubServicesResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.quotationSubServices.home.title',
        title: 'nextlogixApp.quotationSubServices.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'quotation-sub-services/new',
        component: QuotationSubServicesUpdateComponent,
    resolve: {
    quotationSubServices: QuotationSubServicesResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.quotationSubServices.home.title',
        title: 'nextlogixApp.quotationSubServices.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'quotation-sub-services/:id/edit',
        component: QuotationSubServicesUpdateComponent,
    resolve: {
    quotationSubServices: QuotationSubServicesResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.quotationSubServices.home.title',
        title: 'nextlogixApp.quotationSubServices.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const quotationSubServicesPopupRoute: Routes = [
    {
        path: 'quotation-sub-services/:id/delete',
        component: QuotationSubServicesDeletePopupComponent,
        resolve: {
        quotationSubServices: QuotationSubServicesResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.quotationSubServices.home.title',
    title: 'nextlogixApp.quotationSubServices.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
