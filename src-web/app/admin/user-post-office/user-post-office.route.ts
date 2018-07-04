import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
    import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { UserPostOffice } from '../../shared/model/user-post-office.model';
import { UserPostOfficeService } from '../../shared/service/user-post-office.service';
import { UserPostOfficeComponent } from './user-post-office.component';
import { UserPostOfficeDetailComponent } from './user-post-office-detail.component';
import { UserPostOfficeUpdateComponent } from './user-post-office-update.component';
    import { UserPostOfficeDeletePopupComponent } from './user-post-office-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class UserPostOfficeResolve implements Resolve<UserPostOffice> {

        constructor(private service: UserPostOfficeService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((userPostOffice: HttpResponse<UserPostOffice>) => userPostOffice.body);
    }
    return Observable.of(new UserPostOffice());
}
}


export const userPostOfficeRoute: Routes = [
    {
        path: 'user-post-office',
        component: UserPostOfficeComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.userPostOffice.home.title',
    title : 'nextlogixApp.userPostOffice.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'user-post-office/:id/view',
        component: UserPostOfficeDetailComponent,
        resolve: {
        userPostOffice: UserPostOfficeResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.userPostOffice.home.title',
        title: 'nextlogixApp.userPostOffice.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'user-post-office/new',
        component: UserPostOfficeUpdateComponent,
    resolve: {
    userPostOffice: UserPostOfficeResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.userPostOffice.home.title',
        title: 'nextlogixApp.userPostOffice.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'user-post-office/:id/edit',
        component: UserPostOfficeUpdateComponent,
    resolve: {
    userPostOffice: UserPostOfficeResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.userPostOffice.home.title',
        title: 'nextlogixApp.userPostOffice.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const userPostOfficePopupRoute: Routes = [
    {
        path: 'user-post-office/:id/delete',
        component: UserPostOfficeDeletePopupComponent,
        resolve: {
        userPostOffice: UserPostOfficeResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.userPostOffice.home.title',
    title: 'nextlogixApp.userPostOffice.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
