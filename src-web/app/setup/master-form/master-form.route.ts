import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
    import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { MasterForm } from './master-form.model';
import { MasterFormService } from './master-form.service';
import { MasterFormComponent } from './master-form.component';
import { MasterFormDetailComponent } from './master-form-detail.component';
import { MasterFormUpdateComponent } from './master-form-update.component';
    import { MasterFormDeletePopupComponent } from './master-form-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class MasterFormResolve implements Resolve<MasterForm> {

        constructor(private service: MasterFormService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((masterForm: HttpResponse<MasterForm>) => masterForm.body);
    }
    return Observable.of(new MasterForm());
}
}


export const masterFormRoute: Routes = [
    {
        path: 'master-form',
        component: MasterFormComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.masterForm.home.title',
    title : 'nextlogixApp.masterForm.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'master-form/:id/view',
        component: MasterFormDetailComponent,
        resolve: {
        masterForm: MasterFormResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.masterForm.home.title',
        title: 'nextlogixApp.masterForm.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'master-form/new',
        component: MasterFormUpdateComponent,
    resolve: {
    masterForm: MasterFormResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.masterForm.home.title',
        title: 'nextlogixApp.masterForm.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'master-form/:id/edit',
        component: MasterFormUpdateComponent,
    resolve: {
    masterForm: MasterFormResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.masterForm.home.title',
        title: 'nextlogixApp.masterForm.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const masterFormPopupRoute: Routes = [
    {
        path: 'master-form/:id/delete',
        component: MasterFormDeletePopupComponent,
        resolve: {
        masterForm: MasterFormResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.masterForm.home.title',
    title: 'nextlogixApp.masterForm.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
