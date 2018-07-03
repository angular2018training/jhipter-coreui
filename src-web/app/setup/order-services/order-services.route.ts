import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
    import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { OrderServices } from '../../shared/model/order-services.model';
import { OrderServicesService } from '../../shared/service/order-services.service';
import { OrderServicesComponent } from './order-services.component';
import { OrderServicesDetailComponent } from './order-services-detail.component';
import { OrderServicesUpdateComponent } from './order-services-update.component';
    import { OrderServicesDeletePopupComponent } from './order-services-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class OrderServicesResolve implements Resolve<OrderServices> {

        constructor(private service: OrderServicesService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((orderServices: HttpResponse<OrderServices>) => orderServices.body);
    }
    return Observable.of(new OrderServices());
}
}


export const orderServicesRoute: Routes = [
    {
        path: 'order-services',
        component: OrderServicesComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.orderServices.home.title',
    title : 'nextlogixApp.orderServices.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'order-services/:id/view',
        component: OrderServicesDetailComponent,
        resolve: {
        orderServices: OrderServicesResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.orderServices.home.title',
        title: 'nextlogixApp.orderServices.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'order-services/new',
        component: OrderServicesUpdateComponent,
    resolve: {
    orderServices: OrderServicesResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.orderServices.home.title',
        title: 'nextlogixApp.orderServices.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'order-services/:id/edit',
        component: OrderServicesUpdateComponent,
    resolve: {
    orderServices: OrderServicesResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.orderServices.home.title',
        title: 'nextlogixApp.orderServices.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const orderServicesPopupRoute: Routes = [
    {
        path: 'order-services/:id/delete',
        component: OrderServicesDeletePopupComponent,
        resolve: {
        orderServices: OrderServicesResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.orderServices.home.title',
    title: 'nextlogixApp.orderServices.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
