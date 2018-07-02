/**
 * Created by USER on 6/13/2018.
 */
import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../shared';
import {provinceRoute} from './province/province.route';
import {districtRoute} from './district/district.route';
import {wardRoute} from "./ward/ward.route";
import {warehouseRoute} from "./warehouse/warehouse.route";
import {masterFormRoute} from "./master-form/master-form.route";
import {detailFormRoute} from "./detail-form/detail-form.route";
import {positionRoute} from "./position/position.route";
import {postOfficeRoute} from "./post-office/post-office.route";
const SETUP_ROUTES = [
  ...provinceRoute,
  ...districtRoute,
  ...wardRoute,
  ...warehouseRoute,
  ...masterFormRoute,
  ...detailFormRoute,
  ...positionRoute,
  ...postOfficeRoute
];

export const setupState: Routes = [{
  path: '',
  data: {
    authorities: ['ROLE_ADMIN'],
    title : 'Setup',
    pageTitle:'Setup'
  },
  canActivate: [UserRouteAccessService],
  children: SETUP_ROUTES
}
];
