import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
    import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { Weight } from '../../shared/model/weight.model';
import { WeightService } from '../../shared/service/weight.service';
import { WeightComponent } from './weight.component';
import { WeightDetailComponent } from './weight-detail.component';
import { WeightUpdateComponent } from './weight-update.component';
    import { WeightDeletePopupComponent } from './weight-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class WeightResolve implements Resolve<Weight> {

        constructor(private service: WeightService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((weight: HttpResponse<Weight>) => weight.body);
    }
    return Observable.of(new Weight());
}
}


export const weightRoute: Routes = [
    {
        path: 'weight',
        component: WeightComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.weight.home.title',
    title : 'nextlogixApp.weight.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'weight/:id/view',
        component: WeightDetailComponent,
        resolve: {
        weight: WeightResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.weight.home.title',
        title: 'nextlogixApp.weight.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'weight/new',
        component: WeightUpdateComponent,
    resolve: {
    weight: WeightResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.weight.home.title',
        title: 'nextlogixApp.weight.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'weight/:id/edit',
        component: WeightUpdateComponent,
    resolve: {
    weight: WeightResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.weight.home.title',
        title: 'nextlogixApp.weight.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const weightPopupRoute: Routes = [
    {
        path: 'weight/:id/delete',
        component: WeightDeletePopupComponent,
        resolve: {
        weight: WeightResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.weight.home.title',
    title: 'nextlogixApp.weight.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
