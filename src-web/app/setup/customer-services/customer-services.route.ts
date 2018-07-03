import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
    import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { CustomerServices } from './customer-services.model';
import { CustomerServicesService } from './customer-services.service';
import { CustomerServicesComponent } from './customer-services.component';
import { CustomerServicesDetailComponent } from './customer-services-detail.component';
import { CustomerServicesUpdateComponent } from './customer-services-update.component';
    import { CustomerServicesDeletePopupComponent } from './customer-services-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class CustomerServicesResolve implements Resolve<CustomerServices> {

        constructor(private service: CustomerServicesService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((customerServices: HttpResponse<CustomerServices>) => customerServices.body);
    }
    return Observable.of(new CustomerServices());
}
}


export const customerServicesRoute: Routes = [
    {
        path: 'customer-services',
        component: CustomerServicesComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.customerServices.home.title',
    title : 'nextlogixApp.customerServices.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'customer-services/:id/view',
        component: CustomerServicesDetailComponent,
        resolve: {
        customerServices: CustomerServicesResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.customerServices.home.title',
        title: 'nextlogixApp.customerServices.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'customer-services/new',
        component: CustomerServicesUpdateComponent,
    resolve: {
    customerServices: CustomerServicesResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.customerServices.home.title',
        title: 'nextlogixApp.customerServices.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'customer-services/:id/edit',
        component: CustomerServicesUpdateComponent,
    resolve: {
    customerServices: CustomerServicesResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.customerServices.home.title',
        title: 'nextlogixApp.customerServices.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const customerServicesPopupRoute: Routes = [
    {
        path: 'customer-services/:id/delete',
        component: CustomerServicesDeletePopupComponent,
        resolve: {
        customerServices: CustomerServicesResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.customerServices.home.title',
    title: 'nextlogixApp.customerServices.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
