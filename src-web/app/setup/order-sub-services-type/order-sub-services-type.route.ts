import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
    import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { OrderSubServicesType } from '../../shared/model/order-sub-services-type.model';
import { OrderSubServicesTypeService } from '../../shared/service/order-sub-services-type.service';
import { OrderSubServicesTypeComponent } from './order-sub-services-type.component';
import { OrderSubServicesTypeDetailComponent } from './order-sub-services-type-detail.component';
import { OrderSubServicesTypeUpdateComponent } from './order-sub-services-type-update.component';
    import { OrderSubServicesTypeDeletePopupComponent } from './order-sub-services-type-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class OrderSubServicesTypeResolve implements Resolve<OrderSubServicesType> {

        constructor(private service: OrderSubServicesTypeService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((orderSubServicesType: HttpResponse<OrderSubServicesType>) => orderSubServicesType.body);
    }
    return Observable.of(new OrderSubServicesType());
}
}


export const orderSubServicesTypeRoute: Routes = [
    {
        path: 'order-sub-services-type',
        component: OrderSubServicesTypeComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.orderSubServicesType.home.title',
    title : 'nextlogixApp.orderSubServicesType.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'order-sub-services-type/:id/view',
        component: OrderSubServicesTypeDetailComponent,
        resolve: {
        orderSubServicesType: OrderSubServicesTypeResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.orderSubServicesType.home.title',
        title: 'nextlogixApp.orderSubServicesType.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'order-sub-services-type/new',
        component: OrderSubServicesTypeUpdateComponent,
    resolve: {
    orderSubServicesType: OrderSubServicesTypeResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.orderSubServicesType.home.title',
        title: 'nextlogixApp.orderSubServicesType.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'order-sub-services-type/:id/edit',
        component: OrderSubServicesTypeUpdateComponent,
    resolve: {
    orderSubServicesType: OrderSubServicesTypeResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.orderSubServicesType.home.title',
        title: 'nextlogixApp.orderSubServicesType.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const orderSubServicesTypePopupRoute: Routes = [
    {
        path: 'order-sub-services-type/:id/delete',
        component: OrderSubServicesTypeDeletePopupComponent,
        resolve: {
        orderSubServicesType: OrderSubServicesTypeResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.orderSubServicesType.home.title',
    title: 'nextlogixApp.orderSubServicesType.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
