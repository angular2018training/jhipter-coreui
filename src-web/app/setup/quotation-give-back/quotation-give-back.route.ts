import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
    import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { QuotationGiveBack } from '../../shared/model/quotation-give-back.model';
import { QuotationGiveBackService } from '../../shared/service/quotation-give-back.service';
import { QuotationGiveBackComponent } from './quotation-give-back.component';
import { QuotationGiveBackDetailComponent } from './quotation-give-back-detail.component';
import { QuotationGiveBackUpdateComponent } from './quotation-give-back-update.component';
    import { QuotationGiveBackDeletePopupComponent } from './quotation-give-back-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class QuotationGiveBackResolve implements Resolve<QuotationGiveBack> {

        constructor(private service: QuotationGiveBackService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((quotationGiveBack: HttpResponse<QuotationGiveBack>) => quotationGiveBack.body);
    }
    return Observable.of(new QuotationGiveBack());
}
}


export const quotationGiveBackRoute: Routes = [
    {
        path: 'quotation-give-back',
        component: QuotationGiveBackComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.quotationGiveBack.home.title',
    title : 'nextlogixApp.quotationGiveBack.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'quotation-give-back/:id/view',
        component: QuotationGiveBackDetailComponent,
        resolve: {
        quotationGiveBack: QuotationGiveBackResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.quotationGiveBack.home.title',
        title: 'nextlogixApp.quotationGiveBack.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'quotation-give-back/new',
        component: QuotationGiveBackUpdateComponent,
    resolve: {
    quotationGiveBack: QuotationGiveBackResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.quotationGiveBack.home.title',
        title: 'nextlogixApp.quotationGiveBack.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'quotation-give-back/:id/edit',
        component: QuotationGiveBackUpdateComponent,
    resolve: {
    quotationGiveBack: QuotationGiveBackResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.quotationGiveBack.home.title',
        title: 'nextlogixApp.quotationGiveBack.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const quotationGiveBackPopupRoute: Routes = [
    {
        path: 'quotation-give-back/:id/delete',
        component: QuotationGiveBackDeletePopupComponent,
        resolve: {
        quotationGiveBack: QuotationGiveBackResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.quotationGiveBack.home.title',
    title: 'nextlogixApp.quotationGiveBack.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
