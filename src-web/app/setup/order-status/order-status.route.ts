import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
    import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { OrderStatus } from './order-status.model';
import { OrderStatusService } from './order-status.service';
import { OrderStatusComponent } from './order-status.component';
import { OrderStatusDetailComponent } from './order-status-detail.component';
import { OrderStatusUpdateComponent } from './order-status-update.component';
    import { OrderStatusDeletePopupComponent } from './order-status-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class OrderStatusResolve implements Resolve<OrderStatus> {

        constructor(private service: OrderStatusService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((orderStatus: HttpResponse<OrderStatus>) => orderStatus.body);
    }
    return Observable.of(new OrderStatus());
}
}


export const orderStatusRoute: Routes = [
    {
        path: 'order-status',
        component: OrderStatusComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.orderStatus.home.title',
    title : 'nextlogixApp.orderStatus.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'order-status/:id/view',
        component: OrderStatusDetailComponent,
        resolve: {
        orderStatus: OrderStatusResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.orderStatus.home.title',
        title: 'nextlogixApp.orderStatus.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'order-status/new',
        component: OrderStatusUpdateComponent,
    resolve: {
    orderStatus: OrderStatusResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.orderStatus.home.title',
        title: 'nextlogixApp.orderStatus.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'order-status/:id/edit',
        component: OrderStatusUpdateComponent,
    resolve: {
    orderStatus: OrderStatusResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.orderStatus.home.title',
        title: 'nextlogixApp.orderStatus.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const orderStatusPopupRoute: Routes = [
    {
        path: 'order-status/:id/delete',
        component: OrderStatusDeletePopupComponent,
        resolve: {
        orderStatus: OrderStatusResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.orderStatus.home.title',
    title: 'nextlogixApp.orderStatus.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
