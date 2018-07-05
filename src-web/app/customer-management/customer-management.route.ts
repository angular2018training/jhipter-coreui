/**
 * Created by USER on 6/13/2018.
 */
import { Routes, Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../shared';
import { CustomerManagementComponent } from './customer-management.component';
import { CustomerManagementDetailComponent } from './customer-management-detail/customer-management-detail.component';
import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CustomerPaymentService } from './customer-payment/customer-payment.service';
import { CustomerPayment } from './customer-payment/customer-payment.model';
import { Customer } from './customer/customer.model';
import { CustomerService } from './customer/customer.service';
import { CustomerUpdateComponent } from './customer/customer-update.component';

@Injectable({ providedIn: 'root' })
export class CustomerPaymentResolve implements Resolve<any> {

  constructor(private service: CustomerPaymentService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const paymentId = route.parent.params['id'] ? +route.parent.params['id'] : null;
    const customerId = route.parent.parent.params['id'] ? +route.parent.parent.params['id'] : null;
    if (paymentId) {
      return this.service.find(paymentId).map((customerPayment: HttpResponse<CustomerPayment>) => {
        return {
          customerPayment: customerPayment.body,
          customerId
        };
      });
    }
    return Observable.of({
      customerPayment: new CustomerPayment(),
      customerId
    });
  }
}

@Injectable({ providedIn: 'root' })
export class CustomerResolve implements Resolve<Customer> {

  constructor(private service: CustomerService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    // tslint:disable-next-line:no-debugger
    debugger;
    const id = route.params['id'] ? route.params['id'] : null;
    if (+id) {
      return this.service.find(id).map((customer: HttpResponse<Customer>) => customer.body);
    }
    return Observable.of(new Customer());
  }
}

@Injectable({ providedIn: 'root' })
class CanActivateAnotherTab implements CanActivate {
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    const id = +route.parent.params['id'] ? route.parent.params['id'] : 0;
    return !Number.isNaN(Number(id)) && Number(id) > 0;
  }
}

export const customerManagementState: Routes = [
  {
    path: '',
    component: CustomerManagementComponent,
    data: {
      authorities: ['ROLE_ADMIN'],
      title: 'Customer Management'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id',
    component: CustomerManagementDetailComponent,
    resolve: {
      customer: CustomerResolve
    },
    children: [
      {
        path: 'customer',
        component: CustomerUpdateComponent
      }
    ]
  }
];
