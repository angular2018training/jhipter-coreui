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
import { CustomerPayment } from './customer-payment/customer-payment.model';
import { CustomerUpdateComponent } from './customer/customer-update.component';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { CustomerPaymentUpdateComponent } from './customer-payment/customer-payment-update.component';
import { CustomerLegalUpdateComponent } from './customer-legal/customer-legal-update.component';
import { CustomerLegal } from '../shared/model/customer-legal.model';
import { CustomerLegalService } from '../shared/service/customer-legal.service';
import { CustomerPaymentService } from '../shared/service/customer-payment.service';
import { CustomerService } from '../shared/service/customer.service';
import { Customer } from '../shared/model/customer.model';
import { CustomerWarehouseComponent } from './customer-warehouse/customer-warehouse.component';
import { CustomerServicesComponent } from './customer-services/customer-services.component';

@Injectable({ providedIn: 'root' })
export class CustomerLegalResolve implements Resolve<CustomerLegal> {
  constructor(private service: CustomerLegalService) { }
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const legalId = route.params['id'] ? +route.params['id'] : null;
    const customerId = route.parent.params['id'] ? +route.parent.params['id'] : null;
    if (Number(legalId) > 0) {
      return this.service.find(legalId).map((customerLegal: HttpResponse<CustomerLegal>) => {
        return {
          customerLegal: customerLegal.body,
          customerId
        };
      });
    }
    return Observable.of({
      customerLegal: new CustomerLegal(),
      customerId
    });
  }
}

@Injectable({ providedIn: 'root' })
export class CustomerPaymentResolve implements Resolve<any> {
  constructor(private service: CustomerPaymentService) { }
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const paymentId = route.params['id'] ? +route.params['id'] : null;
    const customerId = route.parent.params['id'] ? +route.parent.params['id'] : null;
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
    resolve: {
      'pagingParams': JhiResolvePagingParams
    },
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
      },
      {
        path: 'payment/:id',
        component: CustomerPaymentUpdateComponent,
        resolve: {
          resolved: CustomerPaymentResolve
        },
        canActivate: [CanActivateAnotherTab]
      },
      {
        path: 'legal/:id',
        component: CustomerLegalUpdateComponent,
        resolve: {
          resolved: CustomerLegalResolve
        },
        data: {
          authorities: ['ROLE_USER'],
          pageTitle: 'nextlogixApp.customerLegal.home.title',
          title: 'nextlogixApp.customerLegal.home.title'
        },
        canActivate: [UserRouteAccessService, CanActivateAnotherTab]
      },
      {
        path: 'warehouse',
        component: CustomerWarehouseComponent,
        data: {
          authorities: ['ROLE_USER'],
          pageTitle: 'nextlogixApp.customerLegal.home.title',
          title: 'nextlogixApp.customerLegal.home.title'
        },
        canActivate: [CanActivateAnotherTab]
      },
      {
        path: 'service',
        component: CustomerServicesComponent,
        data: {
          authorities: ['ROLE_USER'],
          pageTitle: 'nextlogixApp.customerLegal.home.title',
          title: 'nextlogixApp.customerLegal.home.title'
        },
        canActivate: [CanActivateAnotherTab]
      }
    ]
  }
];
