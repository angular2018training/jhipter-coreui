/**
 * Created by USER on 6/13/2018.
 */
import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../shared';
import {provinceRoute} from './province/province.route';
import {districtRoute} from './district/district.route';
import {wardRoute} from "./ward/ward.route";
import {warehouseRoute} from "./warehouse/warehouse.route";
import {postOfficeRoute} from "./post-office/post-office.route";
import {orderServicesRoute} from "./order-services/order-services.route";
import {orderServicesTypeRoute} from "./order-services-type/order-services-type.route";
import {quotationRoute} from "./quotation/quotation.route";
import {bankRoute} from "./bank/bank.route";
import {paymentTypeRoute} from "./payment-type/payment-type.route";
const SETUP_ROUTES = [
  ...provinceRoute,
  ...districtRoute,
  ...wardRoute,
  ...warehouseRoute,
  ...postOfficeRoute,
  ...orderServicesRoute,
  ...orderServicesTypeRoute,
  ...quotationRoute,
  ...bankRoute,
  ...paymentTypeRoute
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
