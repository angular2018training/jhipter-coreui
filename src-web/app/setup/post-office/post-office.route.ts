import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
    import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { PostOffice } from './post-office.model';
import { PostOfficeService } from './post-office.service';
import { PostOfficeComponent } from './post-office.component';
import { PostOfficeDetailComponent } from './post-office-detail.component';
import { PostOfficeUpdateComponent } from './post-office-update.component';
    import { PostOfficeDeletePopupComponent } from './post-office-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class PostOfficeResolve implements Resolve<PostOffice> {

        constructor(private service: PostOfficeService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((postOffice: HttpResponse<PostOffice>) => postOffice.body);
    }
    return Observable.of(new PostOffice());
}
}


export const postOfficeRoute: Routes = [
    {
        path: 'post-office',
        component: PostOfficeComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.postOffice.home.title',
    title : 'nextlogixApp.postOffice.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'post-office/:id/view',
        component: PostOfficeDetailComponent,
        resolve: {
        postOffice: PostOfficeResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.postOffice.home.title',
        title: 'nextlogixApp.postOffice.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'post-office/new',
        component: PostOfficeUpdateComponent,
    resolve: {
    postOffice: PostOfficeResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.postOffice.home.title',
        title: 'nextlogixApp.postOffice.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'post-office/:id/edit',
        component: PostOfficeUpdateComponent,
    resolve: {
    postOffice: PostOfficeResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.postOffice.home.title',
        title: 'nextlogixApp.postOffice.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const postOfficePopupRoute: Routes = [
    {
        path: 'post-office/:id/delete',
        component: PostOfficeDeletePopupComponent,
        resolve: {
        postOffice: PostOfficeResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.postOffice.home.title',
    title: 'nextlogixApp.postOffice.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
