import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
    import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { District } from '../../shared/model/district.model';
import { DistrictService } from '../../shared/service/district.service';
import { DistrictComponent } from './district.component';
import { DistrictDetailComponent } from './district-detail.component';
import { DistrictUpdateComponent } from './district-update.component';
    import { DistrictDeletePopupComponent } from './district-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class DistrictResolve implements Resolve<District> {

        constructor(private service: DistrictService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((district: HttpResponse<District>) => district.body);
    }
    return Observable.of(new District());
}
}


export const districtRoute: Routes = [
    {
        path: 'district',
        component: DistrictComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.district.home.title',
    title : 'nextlogixApp.district.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'district/:id/view',
        component: DistrictDetailComponent,
        resolve: {
        district: DistrictResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.district.home.title',
        title: 'nextlogixApp.district.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'district/new',
        component: DistrictUpdateComponent,
    resolve: {
    district: DistrictResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.district.home.title',
        title: 'nextlogixApp.district.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'district/:id/edit',
        component: DistrictUpdateComponent,
    resolve: {
    district: DistrictResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.district.home.title',
        title: 'nextlogixApp.district.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const districtPopupRoute: Routes = [
    {
        path: 'district/:id/delete',
        component: DistrictDeletePopupComponent,
        resolve: {
        district: DistrictResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.district.home.title',
    title: 'nextlogixApp.district.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
