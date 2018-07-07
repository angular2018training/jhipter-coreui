import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
    import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { QuotationPickup } from '../../shared/model/quotation-pickup.model';
import { QuotationPickupService } from '../../shared/service/quotation-pickup.service';
import { QuotationPickupComponent } from './quotation-pickup.component';
import { QuotationPickupDetailComponent } from './quotation-pickup-detail.component';
import { QuotationPickupUpdateComponent } from './quotation-pickup-update.component';
    import { QuotationPickupDeletePopupComponent } from './quotation-pickup-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class QuotationPickupResolve implements Resolve<QuotationPickup> {

        constructor(private service: QuotationPickupService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((quotationPickup: HttpResponse<QuotationPickup>) => quotationPickup.body);
    }
    return Observable.of(new QuotationPickup());
}
}


export const quotationPickupRoute: Routes = [
    {
        path: 'quotation-pickup',
        component: QuotationPickupComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.quotationPickup.home.title',
    title : 'nextlogixApp.quotationPickup.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'quotation-pickup/:id/view',
        component: QuotationPickupDetailComponent,
        resolve: {
        quotationPickup: QuotationPickupResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.quotationPickup.home.title',
        title: 'nextlogixApp.quotationPickup.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'quotation-pickup/new',
        component: QuotationPickupUpdateComponent,
    resolve: {
    quotationPickup: QuotationPickupResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.quotationPickup.home.title',
        title: 'nextlogixApp.quotationPickup.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'quotation-pickup/:id/edit',
        component: QuotationPickupUpdateComponent,
    resolve: {
    quotationPickup: QuotationPickupResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.quotationPickup.home.title',
        title: 'nextlogixApp.quotationPickup.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const quotationPickupPopupRoute: Routes = [
    {
        path: 'quotation-pickup/:id/delete',
        component: QuotationPickupDeletePopupComponent,
        resolve: {
        quotationPickup: QuotationPickupResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.quotationPickup.home.title',
    title: 'nextlogixApp.quotationPickup.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
