import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
    import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { OrderServicesType } from './order-services-type.model';
import { OrderServicesTypeService } from './order-services-type.service';
import { OrderServicesTypeComponent } from './order-services-type.component';
import { OrderServicesTypeDetailComponent } from './order-services-type-detail.component';
import { OrderServicesTypeUpdateComponent } from './order-services-type-update.component';
    import { OrderServicesTypeDeletePopupComponent } from './order-services-type-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class OrderServicesTypeResolve implements Resolve<OrderServicesType> {

        constructor(private service: OrderServicesTypeService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((orderServicesType: HttpResponse<OrderServicesType>) => orderServicesType.body);
    }
    return Observable.of(new OrderServicesType());
}
}


export const orderServicesTypeRoute: Routes = [
    {
        path: 'order-services-type',
        component: OrderServicesTypeComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.orderServicesType.home.title',
    title : 'nextlogixApp.orderServicesType.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'order-services-type/:id/view',
        component: OrderServicesTypeDetailComponent,
        resolve: {
        orderServicesType: OrderServicesTypeResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.orderServicesType.home.title',
        title: 'nextlogixApp.orderServicesType.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'order-services-type/new',
        component: OrderServicesTypeUpdateComponent,
    resolve: {
    orderServicesType: OrderServicesTypeResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.orderServicesType.home.title',
        title: 'nextlogixApp.orderServicesType.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'order-services-type/:id/edit',
        component: OrderServicesTypeUpdateComponent,
    resolve: {
    orderServicesType: OrderServicesTypeResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.orderServicesType.home.title',
        title: 'nextlogixApp.orderServicesType.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const orderServicesTypePopupRoute: Routes = [
    {
        path: 'order-services-type/:id/delete',
        component: OrderServicesTypeDeletePopupComponent,
        resolve: {
        orderServicesType: OrderServicesTypeResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.orderServicesType.home.title',
    title: 'nextlogixApp.orderServicesType.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
