import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { Quotation } from '../../shared/model/quotation.model';
import { QuotationService } from '../../shared/service/quotation.service';
import { QuotationComponent } from './quotation.component';
import { QuotationDetailComponent } from './quotation-detail.component';
import { QuotationUpdateComponent } from './quotation-update.component';
import { QuotationDeletePopupComponent } from './quotation-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class QuotationResolve implements Resolve<Quotation> {

        constructor(private service: QuotationService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((quotation: HttpResponse<Quotation>) => quotation.body);
    }
    return Observable.of(new Quotation());
}
}

export const quotationRoute: Routes = [
    {
        path: 'quotation',
        component: QuotationComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.quotation.home.title',
    title : 'nextlogixApp.quotation.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'quotation/:id/view',
        component: QuotationDetailComponent,
        resolve: {
        quotation: QuotationResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.quotation.home.title',
        title: 'nextlogixApp.quotation.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'quotation/new',
        component: QuotationUpdateComponent,
    resolve: {
    quotation: QuotationResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.quotation.home.title',
        title: 'nextlogixApp.quotation.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'quotation/:id/edit',
        component: QuotationUpdateComponent,
    resolve: {
    quotation: QuotationResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.quotation.home.title',
        title: 'nextlogixApp.quotation.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const quotationPopupRoute: Routes = [
    {
        path: 'quotation/:id/delete',
        component: QuotationDeletePopupComponent,
        resolve: {
        quotation: QuotationResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.quotation.home.title',
    title: 'nextlogixApp.quotation.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
