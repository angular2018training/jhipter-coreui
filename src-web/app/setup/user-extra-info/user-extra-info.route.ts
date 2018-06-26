import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
    import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { UserExtraInfo } from './user-extra-info.model';
import { UserExtraInfoService } from './user-extra-info.service';
import { UserExtraInfoComponent } from './user-extra-info.component';
import { UserExtraInfoDetailComponent } from './user-extra-info-detail.component';
import { UserExtraInfoUpdateComponent } from './user-extra-info-update.component';
    import { UserExtraInfoDeletePopupComponent } from './user-extra-info-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class UserExtraInfoResolve implements Resolve<UserExtraInfo> {

        constructor(private service: UserExtraInfoService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((userExtraInfo: HttpResponse<UserExtraInfo>) => userExtraInfo.body);
    }
    return Observable.of(new UserExtraInfo());
}
}


export const userExtraInfoRoute: Routes = [
    {
        path: 'user-extra-info',
        component: UserExtraInfoComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.userExtraInfo.home.title',
    title : 'nextlogixApp.userExtraInfo.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'user-extra-info/:id/view',
        component: UserExtraInfoDetailComponent,
        resolve: {
        userExtraInfo: UserExtraInfoResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.userExtraInfo.home.title',
        title: 'nextlogixApp.userExtraInfo.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'user-extra-info/new',
        component: UserExtraInfoUpdateComponent,
    resolve: {
    userExtraInfo: UserExtraInfoResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.userExtraInfo.home.title',
        title: 'nextlogixApp.userExtraInfo.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'user-extra-info/:id/edit',
        component: UserExtraInfoUpdateComponent,
    resolve: {
    userExtraInfo: UserExtraInfoResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.userExtraInfo.home.title',
        title: 'nextlogixApp.userExtraInfo.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const userExtraInfoPopupRoute: Routes = [
    {
        path: 'user-extra-info/:id/delete',
        component: UserExtraInfoDeletePopupComponent,
        resolve: {
        userExtraInfo: UserExtraInfoResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.userExtraInfo.home.title',
    title: 'nextlogixApp.userExtraInfo.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
