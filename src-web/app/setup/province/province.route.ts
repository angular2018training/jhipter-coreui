import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
    import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { Province } from './province.model';
import { ProvinceService } from './province.service';
import { ProvinceComponent } from './province.component';
import { ProvinceDetailComponent } from './province-detail.component';
import { ProvinceUpdateComponent } from './province-update.component';
    import { ProvinceDeletePopupComponent } from './province-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class ProvinceResolve implements Resolve<Province> {

        constructor(private service: ProvinceService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((province: HttpResponse<Province>) => province.body);
    }
    return Observable.of(new Province());
}
}


export const provinceRoute: Routes = [
    {
        path: 'province',
        component: ProvinceComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.province.home.title',
    title : 'nextlogixApp.province.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'province/:id/view',
        component: ProvinceDetailComponent,
        resolve: {
        province: ProvinceResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.province.home.title',
        title: 'nextlogixApp.province.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'province/new',
        component: ProvinceUpdateComponent,
    resolve: {
    province: ProvinceResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.province.home.title',
        title: 'nextlogixApp.province.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'province/:id/edit',
        component: ProvinceUpdateComponent,
    resolve: {
    province: ProvinceResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.province.home.title',
        title: 'nextlogixApp.province.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const provincePopupRoute: Routes = [
    {
        path: 'province/:id/delete',
        component: ProvinceDeletePopupComponent,
        resolve: {
        province: ProvinceResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.province.home.title',
    title: 'nextlogixApp.province.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
