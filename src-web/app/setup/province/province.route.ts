import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { ProvinceComponent } from './province.component';
import { ProvinceDetailComponent } from './province-detail.component';
import {ProvinceDialogComponent} from './province-dialog.component';
import { ProvinceDeletePopupComponent } from './province-delete-dialog.component';

export const provinceRoute: Routes = [
    {
        path: 'province',
        component: ProvinceComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Provinces'
        },
        canActivate: [UserRouteAccessService],

    }, {
        path: 'province/detail/:id',
        component: ProvinceDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Provinces'
        },
        canActivate: [UserRouteAccessService]
    },
    {
      path: 'province/new',
      component: ProvinceDialogComponent,
      data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'Provinces'
      },
      canActivate: [UserRouteAccessService]
    },
    {
      path: 'province/edit/:id',
      component: ProvinceDialogComponent,
      data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'Provinces'
      },
      canActivate: [UserRouteAccessService]
    }
];

