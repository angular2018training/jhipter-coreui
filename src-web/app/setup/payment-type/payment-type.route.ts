import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
    import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { PaymentType } from './payment-type.model';
import { PaymentTypeService } from './payment-type.service';
import { PaymentTypeComponent } from './payment-type.component';
import { PaymentTypeDetailComponent } from './payment-type-detail.component';
import { PaymentTypeUpdateComponent } from './payment-type-update.component';
    import { PaymentTypeDeletePopupComponent } from './payment-type-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class PaymentTypeResolve implements Resolve<PaymentType> {

        constructor(private service: PaymentTypeService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((paymentType: HttpResponse<PaymentType>) => paymentType.body);
    }
    return Observable.of(new PaymentType());
}
}


export const paymentTypeRoute: Routes = [
    {
        path: 'payment-type',
        component: PaymentTypeComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.paymentType.home.title',
    title : 'nextlogixApp.paymentType.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'payment-type/:id/view',
        component: PaymentTypeDetailComponent,
        resolve: {
        paymentType: PaymentTypeResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.paymentType.home.title',
        title: 'nextlogixApp.paymentType.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'payment-type/new',
        component: PaymentTypeUpdateComponent,
    resolve: {
    paymentType: PaymentTypeResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.paymentType.home.title',
        title: 'nextlogixApp.paymentType.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'payment-type/:id/edit',
        component: PaymentTypeUpdateComponent,
    resolve: {
    paymentType: PaymentTypeResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.paymentType.home.title',
        title: 'nextlogixApp.paymentType.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const paymentTypePopupRoute: Routes = [
    {
        path: 'payment-type/:id/delete',
        component: PaymentTypeDeletePopupComponent,
        resolve: {
        paymentType: PaymentTypeResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.paymentType.home.title',
    title: 'nextlogixApp.paymentType.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
