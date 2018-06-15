import {Routes} from '@angular/router';

import {UserRouteAccessService} from '../../shared';
import {DistrictComponent} from './district.component';
import {DistrictDetailComponent} from './district-detail.component';
import {DistrictDialogComponent, DistrictPopupComponent} from './district-dialog.component';
import {DistrictDeletePopupComponent} from './district-delete-dialog.component';

export const districtRoute: Routes = [
  {
    path: 'district',
    component: DistrictComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'nextlogixApp.district.home.title'
    },
    canActivate: [UserRouteAccessService]
  }, {
    path: 'district/detail/:id',
    component: DistrictDetailComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'nextlogixApp.district.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'district/new',
    component: DistrictDialogComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'nextlogixApp.district.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'district/edit/:id',
    component: DistrictPopupComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'nextlogixApp.district.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'district/delete/:id',
    component: DistrictDeletePopupComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'nextlogixApp.district.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const districtPopupRoute: Routes = [];
