import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
    import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { Company } from './company.model';
import { CompanyService } from './company.service';
import { CompanyComponent } from './company.component';
import { CompanyDetailComponent } from './company-detail.component';
import { CompanyUpdateComponent } from './company-update.component';
    import { CompanyDeletePopupComponent } from './company-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class CompanyResolve implements Resolve<Company> {

        constructor(private service: CompanyService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((company: HttpResponse<Company>) => company.body);
    }
    return Observable.of(new Company());
}
}


export const companyRoute: Routes = [
    {
        path: 'company',
        component: CompanyComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.company.home.title',
    title : 'nextlogixApp.company.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'company/:id/view',
        component: CompanyDetailComponent,
        resolve: {
        company: CompanyResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.company.home.title',
        title: 'nextlogixApp.company.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'company/new',
        component: CompanyUpdateComponent,
    resolve: {
    company: CompanyResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.company.home.title',
        title: 'nextlogixApp.company.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'company/:id/edit',
        component: CompanyUpdateComponent,
    resolve: {
    company: CompanyResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.company.home.title',
        title: 'nextlogixApp.company.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const companyPopupRoute: Routes = [
    {
        path: 'company/:id/delete',
        component: CompanyDeletePopupComponent,
        resolve: {
        company: CompanyResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.company.home.title',
    title: 'nextlogixApp.company.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
