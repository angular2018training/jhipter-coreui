import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
    import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { CustomerSource } from './customer-source.model';
import { CustomerSourceService } from './customer-source.service';
import { CustomerSourceComponent } from './customer-source.component';
import { CustomerSourceDetailComponent } from './customer-source-detail.component';
import { CustomerSourceUpdateComponent } from './customer-source-update.component';
    import { CustomerSourceDeletePopupComponent } from './customer-source-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class CustomerSourceResolve implements Resolve<CustomerSource> {

        constructor(private service: CustomerSourceService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((customerSource: HttpResponse<CustomerSource>) => customerSource.body);
    }
    return Observable.of(new CustomerSource());
}
}


export const customerSourceRoute: Routes = [
    {
        path: 'customer-source',
        component: CustomerSourceComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.customerSource.home.title',
    title : 'nextlogixApp.customerSource.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'customer-source/:id/view',
        component: CustomerSourceDetailComponent,
        resolve: {
        customerSource: CustomerSourceResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.customerSource.home.title',
        title: 'nextlogixApp.customerSource.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'customer-source/new',
        component: CustomerSourceUpdateComponent,
    resolve: {
    customerSource: CustomerSourceResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.customerSource.home.title',
        title: 'nextlogixApp.customerSource.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'customer-source/:id/edit',
        component: CustomerSourceUpdateComponent,
    resolve: {
    customerSource: CustomerSourceResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.customerSource.home.title',
        title: 'nextlogixApp.customerSource.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const customerSourcePopupRoute: Routes = [
    {
        path: 'customer-source/:id/delete',
        component: CustomerSourceDeletePopupComponent,
        resolve: {
        customerSource: CustomerSourceResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.customerSource.home.title',
    title: 'nextlogixApp.customerSource.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
