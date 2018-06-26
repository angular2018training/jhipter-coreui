import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
    import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { CustomerPayment } from './customer-payment.model';
import { CustomerPaymentService } from './customer-payment.service';
import { CustomerPaymentComponent } from './customer-payment.component';
import { CustomerPaymentDetailComponent } from './customer-payment-detail.component';
import { CustomerPaymentUpdateComponent } from './customer-payment-update.component';
    import { CustomerPaymentDeletePopupComponent } from './customer-payment-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class CustomerPaymentResolve implements Resolve<CustomerPayment> {

        constructor(private service: CustomerPaymentService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((customerPayment: HttpResponse<CustomerPayment>) => customerPayment.body);
    }
    return Observable.of(new CustomerPayment());
}
}


export const customerPaymentRoute: Routes = [
    {
        path: 'customer-payment',
        component: CustomerPaymentComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.customerPayment.home.title',
    title : 'nextlogixApp.customerPayment.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'customer-payment/:id/view',
        component: CustomerPaymentDetailComponent,
        resolve: {
        customerPayment: CustomerPaymentResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.customerPayment.home.title',
        title: 'nextlogixApp.customerPayment.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'customer-payment/new',
        component: CustomerPaymentUpdateComponent,
    resolve: {
    customerPayment: CustomerPaymentResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.customerPayment.home.title',
        title: 'nextlogixApp.customerPayment.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'customer-payment/:id/edit',
        component: CustomerPaymentUpdateComponent,
    resolve: {
    customerPayment: CustomerPaymentResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.customerPayment.home.title',
        title: 'nextlogixApp.customerPayment.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const customerPaymentPopupRoute: Routes = [
    {
        path: 'customer-payment/:id/delete',
        component: CustomerPaymentDeletePopupComponent,
        resolve: {
        customerPayment: CustomerPaymentResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.customerPayment.home.title',
    title: 'nextlogixApp.customerPayment.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
