import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from '../../shared/constants/input.constants';
import { JhiAlertService,  } from 'ng-jhipster';

import { CustomerService } from './customer.service';

import { Customer } from './customer.model';
            import { CustomerLegal, CustomerLegalService } from '../customer-legal';
            import { CustomerPayment, CustomerPaymentService } from '../customer-payment';
            import { Warehouse, WarehouseService } from '../../setup/warehouse';
            import { Company, CompanyService } from '../../setup/company';
            import { UserExtraInfo, UserExtraInfoService } from '../../setup/user-extra-info';
            import { Province, ProvinceService } from '../../setup/province';
            import { District, DistrictService } from '../../setup/district';
            import { CustomerType, CustomerTypeService } from '../customer-type';
            import { CustomerSource, CustomerSourceService } from '../customer-source';

@Component({
    selector: 'jhi-customer-update',
    templateUrl: './customer-update.component.html'
})
export class CustomerUpdateComponent implements OnInit {

    private _customer: Customer;
    isSaving: boolean;

    legals: CustomerLegal[];

    payments: CustomerPayment[];

    warehouses: Warehouse[];

    companies: Company[];

    userextrainfos: UserExtraInfo[];

    provinces: Province[];

    districts: District[];

    customertypes: CustomerType[];

    customersources: CustomerSource[];
    createDate: string;
    lastLoginDate: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private customerService: CustomerService,
        private customerLegalService: CustomerLegalService,
        private customerPaymentService: CustomerPaymentService,
        private warehouseService: WarehouseService,
        private companyService: CompanyService,
        private userExtraInfoService: UserExtraInfoService,
        private provinceService: ProvinceService,
        private districtService: DistrictService,
        private customerTypeService: CustomerTypeService,
        private customerSourceService: CustomerSourceService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({customer}) => {
            this.customer = customer;
        });
        this.customerLegalService
            .query({filter: 'customer-is-null'})
            .subscribe((res: HttpResponse<CustomerLegal[]>) => {
                if (!this.customer.legalId) {
                    this.legals = res.body;
                } else {
                    this.customerLegalService
                        .find(this.customer.legalId)
                        .subscribe((subRes: HttpResponse<CustomerLegal>) => {
                            this.legals = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.customerPaymentService
            .query({filter: 'customer-is-null'})
            .subscribe((res: HttpResponse<CustomerPayment[]>) => {
                if (!this.customer.paymentId) {
                    this.payments = res.body;
                } else {
                    this.customerPaymentService
                        .find(this.customer.paymentId)
                        .subscribe((subRes: HttpResponse<CustomerPayment>) => {
                            this.payments = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.warehouseService
            .query({filter: 'customer-is-null'})
            .subscribe((res: HttpResponse<Warehouse[]>) => {
                if (!this.customer.warehouseId) {
                    this.warehouses = res.body;
                } else {
                    this.warehouseService
                        .find(this.customer.warehouseId)
                        .subscribe((subRes: HttpResponse<Warehouse>) => {
                            this.warehouses = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.companyService.query()
            .subscribe((res: HttpResponse<Company[]>) => { this.companies = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.userExtraInfoService.query()
            .subscribe((res: HttpResponse<UserExtraInfo[]>) => { this.userextrainfos = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.provinceService.query()
            .subscribe((res: HttpResponse<Province[]>) => { this.provinces = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.districtService.query()
            .subscribe((res: HttpResponse<District[]>) => { this.districts = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.customerTypeService.query()
            .subscribe((res: HttpResponse<CustomerType[]>) => { this.customertypes = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.customerSourceService.query()
            .subscribe((res: HttpResponse<CustomerSource[]>) => { this.customersources = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
                this.customer.createDate = moment(this.createDate, DATE_TIME_FORMAT);
                this.customer.lastLoginDate = moment(this.lastLoginDate, DATE_TIME_FORMAT);
        if (this.customer.id !== undefined) {
            this.subscribeToSaveResponse(
                this.customerService.update(this.customer));
        } else {
            this.subscribeToSaveResponse(
                this.customerService.create(this.customer));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Customer>>) {
        result.subscribe((res: HttpResponse<Customer>) =>
            this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackCustomerLegalById(index: number, item: CustomerLegal) {
        return item.id;
    }

    trackCustomerPaymentById(index: number, item: CustomerPayment) {
        return item.id;
    }

    trackWarehouseById(index: number, item: Warehouse) {
        return item.id;
    }

    trackCompanyById(index: number, item: Company) {
        return item.id;
    }

    trackUserExtraInfoById(index: number, item: UserExtraInfo) {
        return item.id;
    }

    trackProvinceById(index: number, item: Province) {
        return item.id;
    }

    trackDistrictById(index: number, item: District) {
        return item.id;
    }

    trackCustomerTypeById(index: number, item: CustomerType) {
        return item.id;
    }

    trackCustomerSourceById(index: number, item: CustomerSource) {
        return item.id;
    }
    get customer() {
        return this._customer;
    }

    set customer(customer: Customer) {
        this._customer = customer;
        this.createDate = moment(customer.createDate).format(DATE_TIME_FORMAT);
        this.lastLoginDate = moment(customer.lastLoginDate).format(DATE_TIME_FORMAT);
    }
}
