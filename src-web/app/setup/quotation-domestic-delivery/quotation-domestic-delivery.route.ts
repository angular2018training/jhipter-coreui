import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
    import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { QuotationDomesticDelivery } from '../../shared/model/quotation-domestic-delivery.model';
import { QuotationDomesticDeliveryService } from '../../shared/service/quotation-domestic-delivery.service';
import { QuotationDomesticDeliveryComponent } from './quotation-domestic-delivery.component';
import { QuotationDomesticDeliveryDetailComponent } from './quotation-domestic-delivery-detail.component';
import { QuotationDomesticDeliveryUpdateComponent } from './quotation-domestic-delivery-update.component';
    import { QuotationDomesticDeliveryDeletePopupComponent } from './quotation-domestic-delivery-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class QuotationDomesticDeliveryResolve implements Resolve<QuotationDomesticDelivery> {

        constructor(private service: QuotationDomesticDeliveryService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((quotationDomesticDelivery: HttpResponse<QuotationDomesticDelivery>) => quotationDomesticDelivery.body);
    }
    return Observable.of(new QuotationDomesticDelivery());
}
}


export const quotationDomesticDeliveryRoute: Routes = [
    {
        path: 'quotation-domestic-delivery',
        component: QuotationDomesticDeliveryComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.quotationDomesticDelivery.home.title',
    title : 'nextlogixApp.quotationDomesticDelivery.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'quotation-domestic-delivery/:id/view',
        component: QuotationDomesticDeliveryDetailComponent,
        resolve: {
        quotationDomesticDelivery: QuotationDomesticDeliveryResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.quotationDomesticDelivery.home.title',
        title: 'nextlogixApp.quotationDomesticDelivery.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'quotation-domestic-delivery/new',
        component: QuotationDomesticDeliveryUpdateComponent,
    resolve: {
    quotationDomesticDelivery: QuotationDomesticDeliveryResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.quotationDomesticDelivery.home.title',
        title: 'nextlogixApp.quotationDomesticDelivery.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'quotation-domestic-delivery/:id/edit',
        component: QuotationDomesticDeliveryUpdateComponent,
    resolve: {
    quotationDomesticDelivery: QuotationDomesticDeliveryResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.quotationDomesticDelivery.home.title',
        title: 'nextlogixApp.quotationDomesticDelivery.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const quotationDomesticDeliveryPopupRoute: Routes = [
    {
        path: 'quotation-domestic-delivery/:id/delete',
        component: QuotationDomesticDeliveryDeletePopupComponent,
        resolve: {
        quotationDomesticDelivery: QuotationDomesticDeliveryResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.quotationDomesticDelivery.home.title',
    title: 'nextlogixApp.quotationDomesticDelivery.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
