import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable, Subscription } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService, JhiEventManager, } from 'ng-jhipster';
import { DATE_TIME_FORMAT } from '../../shared/constants/input.constants';
import { UserExtraInfo } from '../../shared/model/user-extra-info.model';
import { Province } from '../../shared/model/province.model';
import { District } from '../../shared/model/district.model';
import { CustomerType } from '../customer-type/customer-type.model';
import { CustomerSource } from '../customer-source/customer-source.model';
import { CustomerTypeService } from '../customer-type/customer-type.service';
import { CustomerSourceService } from '../customer-source/customer-source.service';
import { ProvinceService } from '../../shared/service/province.service';
import { DistrictService } from '../../shared/service/district.service';
import { Customer } from '../../shared/model/customer.model';
import { CustomerService } from '../../shared/service/customer.service';
import { NgForm } from '@angular/forms';
import * as md5 from 'blueimp-md5';
import { UserExtraInfoService } from '../../shared/service/user-extra-info.service';
@Component({
    selector: 'jhi-customer-update',
    templateUrl: './customer-update.component.html'
})
export class CustomerUpdateComponent implements OnInit, OnDestroy {

    private _customer: Customer;

    isSaving: boolean;

    userextrainfos: UserExtraInfo[];

    provinces: Province[];

    districts: District[];

    customertypes: CustomerType[];

    password = '';

    customersources: CustomerSource[];
    createDate: string;
    lastLoginDate: string;
    routeSubcription: Subscription;

    bsConfig = { dateInputFormat: 'DD/MM/YYYY' };
    @ViewChild('editForm') editForm: NgForm;
    constructor(
        private jhiAlertService: JhiAlertService,
        private customerService: CustomerService,
        private userExtraInfoService: UserExtraInfoService,
        private provinceService: ProvinceService,
        private districtService: DistrictService,
        private customerTypeService: CustomerTypeService,
        private customerSourceService: CustomerSourceService,
        private route: ActivatedRoute,
        private eventManager: JhiEventManager,
        private router: Router
    ) { }

    ngOnInit() {
        this.isSaving = false;
        this.routeSubcription = this.route.parent.data.subscribe(({ customer }) => {
            this.customer = customer;
            if (this.customer.companyId) {
                this.getProvince(this.customer.companyId);
                if (this.customer.provinceId) {
                    this.getDistricts(this.customer.provinceId);

                }
            }
        });

        this.userExtraInfoService.query({
            'companyId.equals': this.customer.companyId,
            size: 10000
        }).subscribe(
            (res: HttpResponse<UserExtraInfo[]>) => { this.userextrainfos = res.body; },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.customerTypeService.query({
            'companyId.equals': this.customer.companyId,
            size: 10000
        }).subscribe(
            (res: HttpResponse<CustomerType[]>) => { this.customertypes = res.body; },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.customerSourceService.query({
            'companyId.equals': this.customer.companyId,
            size: 10000
        }).subscribe(
            (res: HttpResponse<CustomerSource[]>) => { this.customersources = res.body; },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnDestroy(): void {
        // this.routeSubcription && this.routeSubcription.unsubscribe();
    }

    backToList() {
        this.router.navigate(['./customer-management']);
    }

    save() {
        if (this.editForm.invalid) {
            for (const key in this.editForm.form.controls) {
                if (this.editForm.form.controls.hasOwnProperty(key)) {
                    const control = this.editForm.form.controls[key];
                    control.markAsDirty();
                    control.markAsTouched();
                }
            }
            this.jhiAlertService.warning('Xin hãy nhập các giá trị bắt buộc.', null, null);
            return;
        }
        this.isSaving = true;
        if (this.password) {
            this.customer.password = md5(this.password);
        }
        // this.customer.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        // this.customer.lastLoginDate = moment(this.lastLoginDate, DATE_TIME_FORMAT);
        if (this.customer.id !== undefined) {
            this.subscribeToSaveResponse(
                this.customerService.update(this.customer));
        } else {
            this.customerService.create(this.customer).subscribe((res: HttpResponse<Customer>) => {
                this.router.navigate(['../../', res.body.id, 'customer'], { relativeTo: this.route });
            }, (res: HttpErrorResponse) => this.onSaveError());
        }
    }

    onCompanyChanged(companyId) {
        this.customer.companyId = companyId;
        this.customer.provinceId = null;
        this.customer.districtId = null;
        this.getProvince(companyId);
    }

    onProvinceChanged(provinceId) {
        this.customer.provinceId = provinceId;
        this.customer.districtId = null;
        this.getDistricts(provinceId);
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Customer>>) {
        result.subscribe((res: HttpResponse<Customer>) =>
            this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    private getProvince(companyId) {
        this.districts = [];
        this.provinces = [];
        this.provinceService.query({
            'companyId.equals': companyId,
            size: 10000
        }).subscribe((res: HttpResponse<Province[]>) => { this.provinces = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    private getDistricts(provinceId) {
        this.districts = [];
        this.districtService.query({
            'provinceId.equals': provinceId,
            size: 10000
        }).subscribe((res: HttpResponse<District[]>) => { this.districts = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
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
        if (!customer.createDate) {
            customer.createDate = new Date();
        }
        this._customer = customer;
    }
}
