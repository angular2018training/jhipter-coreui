import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
    import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { OrderUserRouteType } from './order-user-route-type.model';
import { OrderUserRouteTypeService } from './order-user-route-type.service';
import { OrderUserRouteTypeComponent } from './order-user-route-type.component';
import { OrderUserRouteTypeDetailComponent } from './order-user-route-type-detail.component';
import { OrderUserRouteTypeUpdateComponent } from './order-user-route-type-update.component';
    import { OrderUserRouteTypeDeletePopupComponent } from './order-user-route-type-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class OrderUserRouteTypeResolve implements Resolve<OrderUserRouteType> {

        constructor(private service: OrderUserRouteTypeService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((orderUserRouteType: HttpResponse<OrderUserRouteType>) => orderUserRouteType.body);
    }
    return Observable.of(new OrderUserRouteType());
}
}


export const orderUserRouteTypeRoute: Routes = [
    {
        path: 'order-user-route-type',
        component: OrderUserRouteTypeComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.orderUserRouteType.home.title',
    title : 'nextlogixApp.orderUserRouteType.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'order-user-route-type/:id/view',
        component: OrderUserRouteTypeDetailComponent,
        resolve: {
        orderUserRouteType: OrderUserRouteTypeResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.orderUserRouteType.home.title',
        title: 'nextlogixApp.orderUserRouteType.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'order-user-route-type/new',
        component: OrderUserRouteTypeUpdateComponent,
    resolve: {
    orderUserRouteType: OrderUserRouteTypeResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.orderUserRouteType.home.title',
        title: 'nextlogixApp.orderUserRouteType.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'order-user-route-type/:id/edit',
        component: OrderUserRouteTypeUpdateComponent,
    resolve: {
    orderUserRouteType: OrderUserRouteTypeResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.orderUserRouteType.home.title',
        title: 'nextlogixApp.orderUserRouteType.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const orderUserRouteTypePopupRoute: Routes = [
    {
        path: 'order-user-route-type/:id/delete',
        component: OrderUserRouteTypeDeletePopupComponent,
        resolve: {
        orderUserRouteType: OrderUserRouteTypeResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.orderUserRouteType.home.title',
    title: 'nextlogixApp.orderUserRouteType.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
