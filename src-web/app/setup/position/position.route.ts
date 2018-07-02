import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
    import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { Position } from './position.model';
import { PositionService } from './position.service';
import { PositionComponent } from './position.component';
import { PositionDetailComponent } from './position-detail.component';
import { PositionUpdateComponent } from './position-update.component';
    import { PositionDeletePopupComponent } from './position-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class PositionResolve implements Resolve<Position> {

        constructor(private service: PositionService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((position: HttpResponse<Position>) => position.body);
    }
    return Observable.of(new Position());
}
}


export const positionRoute: Routes = [
    {
        path: 'position',
        component: PositionComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.position.home.title',
    title : 'nextlogixApp.position.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'position/:id/view',
        component: PositionDetailComponent,
        resolve: {
        position: PositionResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.position.home.title',
        title: 'nextlogixApp.position.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'position/new',
        component: PositionUpdateComponent,
    resolve: {
    position: PositionResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.position.home.title',
        title: 'nextlogixApp.position.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'position/:id/edit',
        component: PositionUpdateComponent,
    resolve: {
    position: PositionResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.position.home.title',
        title: 'nextlogixApp.position.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const positionPopupRoute: Routes = [
    {
        path: 'position/:id/delete',
        component: PositionDeletePopupComponent,
        resolve: {
        position: PositionResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.position.home.title',
    title: 'nextlogixApp.position.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
