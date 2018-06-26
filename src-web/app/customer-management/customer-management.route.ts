/**
 * Created by USER on 6/13/2018.
 */
import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../shared';
import {customerRoute} from "./customer/customer.route";
import {customerLegalRoute} from "./customer-legal/customer-legal.route";
import {customerPaymentRoute} from "./customer-payment/customer-payment.route";
import {customerPostOfficeRoute} from "./customer-post-office/customer-post-office.route";
import {customerSourceRoute} from "./customer-source/customer-source.route";
import {customerTypeRoute} from "./customer-type/customer-type.route";
const CUSTOMER_MANAGEMENT_ROUTES = [
  ...customerRoute,
  ...customerLegalRoute,
  ...customerPaymentRoute,
  ...customerPostOfficeRoute,
  ...customerSourceRoute,
  ...customerTypeRoute
];

export const customerManagementState: Routes = [{
  path: '',
  data: {
    authorities: ['ROLE_ADMIN'],
    title : 'Customer Management'
  },
  canActivate: [UserRouteAccessService],
  children: CUSTOMER_MANAGEMENT_ROUTES
}
];
