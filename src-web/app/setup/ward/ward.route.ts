import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
    import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { Ward } from './ward.model';
import { WardService } from './ward.service';
import { WardComponent } from './ward.component';
import { WardDetailComponent } from './ward-detail.component';
import { WardUpdateComponent } from './ward-update.component';
    import { WardDeletePopupComponent } from './ward-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class WardResolve implements Resolve<Ward> {

        constructor(private service: WardService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((ward: HttpResponse<Ward>) => ward.body);
    }
    return Observable.of(new Ward());
}
}


export const wardRoute: Routes = [
    {
        path: 'ward',
        component: WardComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.ward.home.title',
    title : 'nextlogixApp.ward.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'ward/:id/view',
        component: WardDetailComponent,
        resolve: {
        ward: WardResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.ward.home.title',
        title: 'nextlogixApp.ward.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'ward/new',
        component: WardUpdateComponent,
    resolve: {
    ward: WardResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.ward.home.title',
        title: 'nextlogixApp.ward.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'ward/:id/edit',
        component: WardUpdateComponent,
    resolve: {
    ward: WardResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.ward.home.title',
        title: 'nextlogixApp.ward.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const wardPopupRoute: Routes = [
    {
        path: 'ward/:id/delete',
        component: WardDeletePopupComponent,
        resolve: {
        ward: WardResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.ward.home.title',
    title: 'nextlogixApp.ward.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
