import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
    import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { OrderSubServices } from '../../shared/model/order-sub-services.model';
import { OrderSubServicesService } from '../../shared/service/order-sub-services.service';
import { OrderSubServicesComponent } from './order-sub-services.component';
import { OrderSubServicesDetailComponent } from './order-sub-services-detail.component';
import { OrderSubServicesUpdateComponent } from './order-sub-services-update.component';
    import { OrderSubServicesDeletePopupComponent } from './order-sub-services-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class OrderSubServicesResolve implements Resolve<OrderSubServices> {

        constructor(private service: OrderSubServicesService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((orderSubServices: HttpResponse<OrderSubServices>) => orderSubServices.body);
    }
    return Observable.of(new OrderSubServices());
}
}


export const orderSubServicesRoute: Routes = [
    {
        path: 'order-sub-services',
        component: OrderSubServicesComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.orderSubServices.home.title',
    title : 'nextlogixApp.orderSubServices.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'order-sub-services/:id/view',
        component: OrderSubServicesDetailComponent,
        resolve: {
        orderSubServices: OrderSubServicesResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.orderSubServices.home.title',
        title: 'nextlogixApp.orderSubServices.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'order-sub-services/new',
        component: OrderSubServicesUpdateComponent,
    resolve: {
    orderSubServices: OrderSubServicesResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.orderSubServices.home.title',
        title: 'nextlogixApp.orderSubServices.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'order-sub-services/:id/edit',
        component: OrderSubServicesUpdateComponent,
    resolve: {
    orderSubServices: OrderSubServicesResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.orderSubServices.home.title',
        title: 'nextlogixApp.orderSubServices.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const orderSubServicesPopupRoute: Routes = [
    {
        path: 'order-sub-services/:id/delete',
        component: OrderSubServicesDeletePopupComponent,
        resolve: {
        orderSubServices: OrderSubServicesResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.orderSubServices.home.title',
    title: 'nextlogixApp.orderSubServices.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
