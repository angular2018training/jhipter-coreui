import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
    import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { DetailForm } from './detail-form.model';
import { DetailFormService } from './detail-form.service';
import { DetailFormComponent } from './detail-form.component';
import { DetailFormDetailComponent } from './detail-form-detail.component';
import { DetailFormUpdateComponent } from './detail-form-update.component';
    import { DetailFormDeletePopupComponent } from './detail-form-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class DetailFormResolve implements Resolve<DetailForm> {

        constructor(private service: DetailFormService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((detailForm: HttpResponse<DetailForm>) => detailForm.body);
    }
    return Observable.of(new DetailForm());
}
}


export const detailFormRoute: Routes = [
    {
        path: 'detail-form',
        component: DetailFormComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.detailForm.home.title',
    title : 'nextlogixApp.detailForm.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'detail-form/:id/view',
        component: DetailFormDetailComponent,
        resolve: {
        detailForm: DetailFormResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.detailForm.home.title',
        title: 'nextlogixApp.detailForm.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'detail-form/new',
        component: DetailFormUpdateComponent,
    resolve: {
    detailForm: DetailFormResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.detailForm.home.title',
        title: 'nextlogixApp.detailForm.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'detail-form/:id/edit',
        component: DetailFormUpdateComponent,
    resolve: {
    detailForm: DetailFormResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.detailForm.home.title',
        title: 'nextlogixApp.detailForm.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const detailFormPopupRoute: Routes = [
    {
        path: 'detail-form/:id/delete',
        component: DetailFormDeletePopupComponent,
        resolve: {
        detailForm: DetailFormResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.detailForm.home.title',
    title: 'nextlogixApp.detailForm.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
