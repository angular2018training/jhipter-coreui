import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
    import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { UserPosition } from './user-position.model';
import { UserPositionService } from './user-position.service';
import { UserPositionComponent } from './user-position.component';
import { UserPositionDetailComponent } from './user-position-detail.component';
import { UserPositionUpdateComponent } from './user-position-update.component';
    import { UserPositionDeletePopupComponent } from './user-position-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class UserPositionResolve implements Resolve<UserPosition> {

        constructor(private service: UserPositionService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((userPosition: HttpResponse<UserPosition>) => userPosition.body);
    }
    return Observable.of(new UserPosition());
}
}


export const userPositionRoute: Routes = [
    {
        path: 'user-position',
        component: UserPositionComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.userPosition.home.title',
    title : 'nextlogixApp.userPosition.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'user-position/:id/view',
        component: UserPositionDetailComponent,
        resolve: {
        userPosition: UserPositionResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.userPosition.home.title',
        title: 'nextlogixApp.userPosition.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'user-position/new',
        component: UserPositionUpdateComponent,
    resolve: {
    userPosition: UserPositionResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.userPosition.home.title',
        title: 'nextlogixApp.userPosition.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'user-position/:id/edit',
        component: UserPositionUpdateComponent,
    resolve: {
    userPosition: UserPositionResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.userPosition.home.title',
        title: 'nextlogixApp.userPosition.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const userPositionPopupRoute: Routes = [
    {
        path: 'user-position/:id/delete',
        component: UserPositionDeletePopupComponent,
        resolve: {
        userPosition: UserPositionResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.userPosition.home.title',
    title: 'nextlogixApp.userPosition.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
