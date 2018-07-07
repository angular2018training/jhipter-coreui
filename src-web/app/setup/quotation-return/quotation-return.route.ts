import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
    import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { QuotationReturn } from '../../shared/model/quotation-return.model';
import { QuotationReturnService } from '../../shared/service/quotation-return.service';
import { QuotationReturnComponent } from './quotation-return.component';
import { QuotationReturnDetailComponent } from './quotation-return-detail.component';
import { QuotationReturnUpdateComponent } from './quotation-return-update.component';
    import { QuotationReturnDeletePopupComponent } from './quotation-return-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class QuotationReturnResolve implements Resolve<QuotationReturn> {

        constructor(private service: QuotationReturnService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((quotationReturn: HttpResponse<QuotationReturn>) => quotationReturn.body);
    }
    return Observable.of(new QuotationReturn());
}
}


export const quotationReturnRoute: Routes = [
    {
        path: 'quotation-return',
        component: QuotationReturnComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.quotationReturn.home.title',
    title : 'nextlogixApp.quotationReturn.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'quotation-return/:id/view',
        component: QuotationReturnDetailComponent,
        resolve: {
        quotationReturn: QuotationReturnResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.quotationReturn.home.title',
        title: 'nextlogixApp.quotationReturn.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'quotation-return/new',
        component: QuotationReturnUpdateComponent,
    resolve: {
    quotationReturn: QuotationReturnResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.quotationReturn.home.title',
        title: 'nextlogixApp.quotationReturn.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'quotation-return/:id/edit',
        component: QuotationReturnUpdateComponent,
    resolve: {
    quotationReturn: QuotationReturnResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.quotationReturn.home.title',
        title: 'nextlogixApp.quotationReturn.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const quotationReturnPopupRoute: Routes = [
    {
        path: 'quotation-return/:id/delete',
        component: QuotationReturnDeletePopupComponent,
        resolve: {
        quotationReturn: QuotationReturnResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.quotationReturn.home.title',
    title: 'nextlogixApp.quotationReturn.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
