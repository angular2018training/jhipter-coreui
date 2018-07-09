import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
    import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { DistrictType } from './district-type.model';
import { DistrictTypeService } from './district-type.service';
import { DistrictTypeComponent } from './district-type.component';
import { DistrictTypeDetailComponent } from './district-type-detail.component';
import { DistrictTypeUpdateComponent } from './district-type-update.component';
    import { DistrictTypeDeletePopupComponent } from './district-type-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class DistrictTypeResolve implements Resolve<DistrictType> {

        constructor(private service: DistrictTypeService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((districtType: HttpResponse<DistrictType>) => districtType.body);
    }
    return Observable.of(new DistrictType());
}
}


export const districtTypeRoute: Routes = [
    {
        path: 'district-type',
        component: DistrictTypeComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.districtType.home.title',
    title : 'nextlogixApp.districtType.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'district-type/:id/view',
        component: DistrictTypeDetailComponent,
        resolve: {
        districtType: DistrictTypeResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.districtType.home.title',
        title: 'nextlogixApp.districtType.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'district-type/new',
        component: DistrictTypeUpdateComponent,
    resolve: {
    districtType: DistrictTypeResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.districtType.home.title',
        title: 'nextlogixApp.districtType.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'district-type/:id/edit',
        component: DistrictTypeUpdateComponent,
    resolve: {
    districtType: DistrictTypeResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.districtType.home.title',
        title: 'nextlogixApp.districtType.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const districtTypePopupRoute: Routes = [
    {
        path: 'district-type/:id/delete',
        component: DistrictTypeDeletePopupComponent,
        resolve: {
        districtType: DistrictTypeResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.districtType.home.title',
    title: 'nextlogixApp.districtType.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
