import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
    import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { CustomerPostOffice } from './customer-post-office.model';
import { CustomerPostOfficeService } from './customer-post-office.service';
import { CustomerPostOfficeComponent } from './customer-post-office.component';
import { CustomerPostOfficeDetailComponent } from './customer-post-office-detail.component';
import { CustomerPostOfficeUpdateComponent } from './customer-post-office-update.component';
    import { CustomerPostOfficeDeletePopupComponent } from './customer-post-office-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class CustomerPostOfficeResolve implements Resolve<CustomerPostOffice> {

        constructor(private service: CustomerPostOfficeService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((customerPostOffice: HttpResponse<CustomerPostOffice>) => customerPostOffice.body);
    }
    return Observable.of(new CustomerPostOffice());
}
}


export const customerPostOfficeRoute: Routes = [
    {
        path: 'customer-post-office',
        component: CustomerPostOfficeComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.customerPostOffice.home.title',
    title : 'nextlogixApp.customerPostOffice.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'customer-post-office/:id/view',
        component: CustomerPostOfficeDetailComponent,
        resolve: {
        customerPostOffice: CustomerPostOfficeResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.customerPostOffice.home.title',
        title: 'nextlogixApp.customerPostOffice.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'customer-post-office/new',
        component: CustomerPostOfficeUpdateComponent,
    resolve: {
    customerPostOffice: CustomerPostOfficeResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.customerPostOffice.home.title',
        title: 'nextlogixApp.customerPostOffice.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'customer-post-office/:id/edit',
        component: CustomerPostOfficeUpdateComponent,
    resolve: {
    customerPostOffice: CustomerPostOfficeResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.customerPostOffice.home.title',
        title: 'nextlogixApp.customerPostOffice.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const customerPostOfficePopupRoute: Routes = [
    {
        path: 'customer-post-office/:id/delete',
        component: CustomerPostOfficeDeletePopupComponent,
        resolve: {
        customerPostOffice: CustomerPostOfficeResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.customerPostOffice.home.title',
    title: 'nextlogixApp.customerPostOffice.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
