import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
    import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { CustomerLegal } from './customer-legal.model';
import { CustomerLegalService } from './customer-legal.service';
import { CustomerLegalComponent } from './customer-legal.component';
import { CustomerLegalDetailComponent } from './customer-legal-detail.component';
import { CustomerLegalUpdateComponent } from './customer-legal-update.component';
    import { CustomerLegalDeletePopupComponent } from './customer-legal-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class CustomerLegalResolve implements Resolve<CustomerLegal> {

        constructor(private service: CustomerLegalService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((customerLegal: HttpResponse<CustomerLegal>) => customerLegal.body);
    }
    return Observable.of(new CustomerLegal());
}
}


export const customerLegalRoute: Routes = [
    {
        path: 'customer-legal',
        component: CustomerLegalComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.customerLegal.home.title',
    title : 'nextlogixApp.customerLegal.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'customer-legal/:id/view',
        component: CustomerLegalDetailComponent,
        resolve: {
        customerLegal: CustomerLegalResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.customerLegal.home.title',
        title: 'nextlogixApp.customerLegal.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'customer-legal/new',
        component: CustomerLegalUpdateComponent,
    resolve: {
    customerLegal: CustomerLegalResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.customerLegal.home.title',
        title: 'nextlogixApp.customerLegal.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'customer-legal/:id/edit',
        component: CustomerLegalUpdateComponent,
    resolve: {
    customerLegal: CustomerLegalResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.customerLegal.home.title',
        title: 'nextlogixApp.customerLegal.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const customerLegalPopupRoute: Routes = [
    {
        path: 'customer-legal/:id/delete',
        component: CustomerLegalDeletePopupComponent,
        resolve: {
        customerLegal: CustomerLegalResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.customerLegal.home.title',
    title: 'nextlogixApp.customerLegal.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
