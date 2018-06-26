import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
    import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { CustomerType } from './customer-type.model';
import { CustomerTypeService } from './customer-type.service';
import { CustomerTypeComponent } from './customer-type.component';
import { CustomerTypeDetailComponent } from './customer-type-detail.component';
import { CustomerTypeUpdateComponent } from './customer-type-update.component';
    import { CustomerTypeDeletePopupComponent } from './customer-type-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class CustomerTypeResolve implements Resolve<CustomerType> {

        constructor(private service: CustomerTypeService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((customerType: HttpResponse<CustomerType>) => customerType.body);
    }
    return Observable.of(new CustomerType());
}
}


export const customerTypeRoute: Routes = [
    {
        path: 'customer-type',
        component: CustomerTypeComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.customerType.home.title',
    title : 'nextlogixApp.customerType.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'customer-type/:id/view',
        component: CustomerTypeDetailComponent,
        resolve: {
        customerType: CustomerTypeResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.customerType.home.title',
        title: 'nextlogixApp.customerType.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'customer-type/new',
        component: CustomerTypeUpdateComponent,
    resolve: {
    customerType: CustomerTypeResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.customerType.home.title',
        title: 'nextlogixApp.customerType.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'customer-type/:id/edit',
        component: CustomerTypeUpdateComponent,
    resolve: {
    customerType: CustomerTypeResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.customerType.home.title',
        title: 'nextlogixApp.customerType.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const customerTypePopupRoute: Routes = [
    {
        path: 'customer-type/:id/delete',
        component: CustomerTypeDeletePopupComponent,
        resolve: {
        customerType: CustomerTypeResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.customerType.home.title',
    title: 'nextlogixApp.customerType.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
