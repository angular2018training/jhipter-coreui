import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { UserGroup } from './models/user-group.model';
import { UserGroupService } from './services/user-group.service';
import { UserGroupComponent } from './components/user-group/user-group.component';
import { UserGroupDetailComponent } from './components/user-group-detail/user-group-detail.component';
import { UserGroupUpdateComponent } from './components/user-group-update/user-group-update.component';
import { UserGroupDeletePopupComponent } from './dialogs/user-group-delete-dialog/user-group-delete-dialog.component';

@Injectable({ providedIn: 'root' })
export class UserGroupResolve implements Resolve<UserGroup> {

    constructor(private service: UserGroupService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((userGroup: HttpResponse<UserGroup>) => userGroup.body);
        }
        return Observable.of(new UserGroup());
    }
}

export const userGroupRoute: Routes = [
    {
        path: 'user-group',
        component: UserGroupComponent,
        resolve: {
            'pagingParams': JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'nextlogixApp.userGroup.home.title',
            title: 'nextlogixApp.userGroup.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'user-group/:id/view',
        component: UserGroupDetailComponent,
        resolve: {
            userGroup: UserGroupResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.userGroup.home.title',
            title: 'nextlogixApp.userGroup.home.title',
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'user-group/new',
        component: UserGroupUpdateComponent,
        resolve: {
            userGroup: UserGroupResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.userGroup.home.title',
            title: 'nextlogixApp.userGroup.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'user-group/:id/edit',
        component: UserGroupUpdateComponent,
        resolve: {
            userGroup: UserGroupResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.userGroup.home.title',
            title: 'nextlogixApp.userGroup.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
];

export const userGroupPopupRoute: Routes = [
    {
        path: 'user-group/:id/delete',
        component: UserGroupDeletePopupComponent,
        resolve: {
            userGroup: UserGroupResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.userGroup.home.title',
            title: 'nextlogixApp.userGroup.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
