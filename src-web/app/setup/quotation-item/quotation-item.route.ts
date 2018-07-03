import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
    import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { QuotationItem } from '../../shared/model/quotation-item.model';
import { QuotationItemService } from '../../shared/service/quotation-item.service';
import { QuotationItemComponent } from './quotation-item.component';
import { QuotationItemDetailComponent } from './quotation-item-detail.component';
import { QuotationItemUpdateComponent } from './quotation-item-update.component';
    import { QuotationItemDeletePopupComponent } from './quotation-item-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class QuotationItemResolve implements Resolve<QuotationItem> {

        constructor(private service: QuotationItemService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((quotationItem: HttpResponse<QuotationItem>) => quotationItem.body);
    }
    return Observable.of(new QuotationItem());
}
}


export const quotationItemRoute: Routes = [
    {
        path: 'quotation-item',
        component: QuotationItemComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.quotationItem.home.title',
    title : 'nextlogixApp.quotationItem.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'quotation-item/:id/view',
        component: QuotationItemDetailComponent,
        resolve: {
        quotationItem: QuotationItemResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.quotationItem.home.title',
        title: 'nextlogixApp.quotationItem.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'quotation-item/new',
        component: QuotationItemUpdateComponent,
    resolve: {
    quotationItem: QuotationItemResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.quotationItem.home.title',
        title: 'nextlogixApp.quotationItem.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'quotation-item/:id/edit',
        component: QuotationItemUpdateComponent,
    resolve: {
    quotationItem: QuotationItemResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.quotationItem.home.title',
        title: 'nextlogixApp.quotationItem.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const quotationItemPopupRoute: Routes = [
    {
        path: 'quotation-item/:id/delete',
        component: QuotationItemDeletePopupComponent,
        resolve: {
        quotationItem: QuotationItemResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.quotationItem.home.title',
    title: 'nextlogixApp.quotationItem.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
