import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
    import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { QuotationType } from '../../shared/model/quotation-type.model';
import { QuotationTypeService } from '../../shared/service/quotation-type.service';
import { QuotationTypeComponent } from './quotation-type.component';
import { QuotationTypeDetailComponent } from './quotation-type-detail.component';
import { QuotationTypeUpdateComponent } from './quotation-type-update.component';
    import { QuotationTypeDeletePopupComponent } from './quotation-type-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class QuotationTypeResolve implements Resolve<QuotationType> {

        constructor(private service: QuotationTypeService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((quotationType: HttpResponse<QuotationType>) => quotationType.body);
    }
    return Observable.of(new QuotationType());
}
}


export const quotationTypeRoute: Routes = [
    {
        path: 'quotation-type',
        component: QuotationTypeComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.quotationType.home.title',
    title : 'nextlogixApp.quotationType.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'quotation-type/:id/view',
        component: QuotationTypeDetailComponent,
        resolve: {
        quotationType: QuotationTypeResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.quotationType.home.title',
        title: 'nextlogixApp.quotationType.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'quotation-type/new',
        component: QuotationTypeUpdateComponent,
    resolve: {
    quotationType: QuotationTypeResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.quotationType.home.title',
        title: 'nextlogixApp.quotationType.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'quotation-type/:id/edit',
        component: QuotationTypeUpdateComponent,
    resolve: {
    quotationType: QuotationTypeResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.quotationType.home.title',
        title: 'nextlogixApp.quotationType.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const quotationTypePopupRoute: Routes = [
    {
        path: 'quotation-type/:id/delete',
        component: QuotationTypeDeletePopupComponent,
        resolve: {
        quotationType: QuotationTypeResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.quotationType.home.title',
    title: 'nextlogixApp.quotationType.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
