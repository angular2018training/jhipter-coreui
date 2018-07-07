import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
    import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { QuotationCod } from '../../shared/model/quotation-cod.model';
import { QuotationCodService } from '../../shared/service/quotation-cod.service';
import { QuotationCodComponent } from './quotation-cod.component';
import { QuotationCodDetailComponent } from './quotation-cod-detail.component';
import { QuotationCodUpdateComponent } from './quotation-cod-update.component';
    import { QuotationCodDeletePopupComponent } from './quotation-cod-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class QuotationCodResolve implements Resolve<QuotationCod> {

        constructor(private service: QuotationCodService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((quotationCod: HttpResponse<QuotationCod>) => quotationCod.body);
    }
    return Observable.of(new QuotationCod());
}
}


export const quotationCodRoute: Routes = [
    {
        path: 'quotation-cod',
        component: QuotationCodComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.quotationCod.home.title',
    title : 'nextlogixApp.quotationCod.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'quotation-cod/:id/view',
        component: QuotationCodDetailComponent,
        resolve: {
        quotationCod: QuotationCodResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.quotationCod.home.title',
        title: 'nextlogixApp.quotationCod.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'quotation-cod/new',
        component: QuotationCodUpdateComponent,
    resolve: {
    quotationCod: QuotationCodResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.quotationCod.home.title',
        title: 'nextlogixApp.quotationCod.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'quotation-cod/:id/edit',
        component: QuotationCodUpdateComponent,
    resolve: {
    quotationCod: QuotationCodResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.quotationCod.home.title',
        title: 'nextlogixApp.quotationCod.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const quotationCodPopupRoute: Routes = [
    {
        path: 'quotation-cod/:id/delete',
        component: QuotationCodDeletePopupComponent,
        resolve: {
        quotationCod: QuotationCodResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.quotationCod.home.title',
    title: 'nextlogixApp.quotationCod.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
