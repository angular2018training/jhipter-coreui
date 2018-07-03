import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { Principal, ITEMS_PER_PAGE } from '../shared';
import { CustomerPostOffice } from './customer-post-office/customer-post-office.model';
import { CustomerPopupService } from './customer/customer-popup.service';
import { Customer } from './customer/customer.model';
import { CustomerSearch } from './customer/customer.search.model';
import { CustomerService } from './customer/customer.service';
import { CustomerDeleteDialogComponent } from './customer/customer-delete-dialog.component';

@Component({
    selector: 'jhi-customer-management',
    templateUrl: './customer-management.component.html',
    providers: [CustomerPopupService]
})
export class CustomerManagementComponent implements OnInit, OnDestroy {

    currentAccount: any;
    customers: Customer[];
    error: any;
    success: any;
    eventSubscriber: Subscription;
    currentSearch: string;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;
    customerSearch: CustomerSearch;

    customerPostOffices: CustomerPostOffice[];

    searchCardIsCollapsed;

    @ViewChild(NgForm) searchForm: NgForm;

    constructor(

        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager,
        private customerService: CustomerService,
        private customerPopupService: CustomerPopupService
    ) {

        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;

        });

        this.customerPostOffices = [];

        this.customerSearch = new CustomerSearch();

        this.customerSearch.code = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['code'] ?
            this.activatedRoute.snapshot.params[' code'] : '';
        this.customerSearch.name = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['name'] ?
            this.activatedRoute.snapshot.params[' name'] : '';
        this.customerSearch.address = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['address'] ?
            this.activatedRoute.snapshot.params[' address'] : '';
        this.customerSearch.email = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['email'] ?
            this.activatedRoute.snapshot.params[' email'] : '';
        this.customerSearch.phone = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['phone'] ?
            this.activatedRoute.snapshot.params[' phone'] : '';
        this.customerSearch.password = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['password'] ?
            this.activatedRoute.snapshot.params[' password'] : '';
        this.customerSearch.isActive = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['isActive'] ?
            this.activatedRoute.snapshot.params[' isActive'] : '';
        this.customerSearch.createDate = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['createDate'] ?
            this.activatedRoute.snapshot.params[' createDate'] : '';
        this.customerSearch.lastLoginDate = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['lastLoginDate'] ?
            this.activatedRoute.snapshot.params[' lastLoginDate'] : '';
        this.customerSearch.apiToken = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['apiToken'] ?
            this.activatedRoute.snapshot.params[' apiToken'] : '';
        this.customerSearch.legalId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['legalId'] ?
            this.activatedRoute.snapshot.params['legalId'] : '';
        this.customerSearch.paymentId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['paymentId'] ?
            this.activatedRoute.snapshot.params['paymentId'] : '';
        this.customerSearch.warehouseId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['warehouseId'] ?
            this.activatedRoute.snapshot.params['warehouseId'] : '';
        this.customerSearch.companyId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['companyId'] ?
            this.activatedRoute.snapshot.params['companyId'] : '';
        this.customerSearch.manageUserId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['manageUserId'] ?
            this.activatedRoute.snapshot.params['manageUserId'] : '';
        this.customerSearch.saleUserId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['saleUserId'] ?
            this.activatedRoute.snapshot.params['saleUserId'] : '';
        this.customerSearch.provinceId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['provinceId'] ?
            this.activatedRoute.snapshot.params['provinceId'] : '';
        this.customerSearch.districtId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['districtId'] ?
            this.activatedRoute.snapshot.params['districtId'] : '';
        this.customerSearch.customerTypeId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['customerTypeId'] ?
            this.activatedRoute.snapshot.params['customerTypeId'] : '';
        this.customerSearch.customerSourceId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['customerSourceId'] ?
            this.activatedRoute.snapshot.params['customerSourceId'] : '';
    }

    loadAll() {
        const obj = {
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort(),
            code: this.customerSearch.code,
            name: this.customerSearch.name,
            address: this.customerSearch.address,
            email: this.customerSearch.email,
            phone: this.customerSearch.phone,
            password: this.customerSearch.password,
            isActive: this.customerSearch.isActive,
            createDate: this.customerSearch.createDate,
            lastLoginDate: this.customerSearch.lastLoginDate,
            apiToken: this.customerSearch.apiToken,
            legalId: this.customerSearch.legalId,
            paymentId: this.customerSearch.paymentId,
            warehouseId: this.customerSearch.warehouseId,
            companyId: this.customerSearch.companyId,
            manageUserId: this.customerSearch.manageUserId,
            saleUserId: this.customerSearch.saleUserId,
            provinceId: this.customerSearch.provinceId,
            districtId: this.customerSearch.districtId,
            customerTypeId: this.customerSearch.customerTypeId,
            customerSourceId: this.customerSearch.customerSourceId,
        };

        this.customerService.searchExample(obj).subscribe(
            (res: HttpResponse<Customer[]>) => this.onSuccess(res.body, res.headers),
            (res: HttpErrorResponse) => this.onError(res.message)
        );

    }

    searchInForm() {
        this.page = 0;
        this.transition();
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        this.router.navigate(['/customer-management/search'], {
            queryParams:
            {
                page: this.page,
                size: this.itemsPerPage,
                search: this.currentSearch,
                code: this.customerSearch.code,
                name: this.customerSearch.name,
                address: this.customerSearch.address,
                email: this.customerSearch.email,
                phone: this.customerSearch.phone,
                password: this.customerSearch.password,
                isActive: this.customerSearch.isActive,
                createDate: this.customerSearch.createDate,
                lastLoginDate: this.customerSearch.lastLoginDate,
                apiToken: this.customerSearch.apiToken,
                legalId: this.customerSearch.legalId,
                paymentId: this.customerSearch.paymentId,
                warehouseId: this.customerSearch.warehouseId,
                companyId: this.customerSearch.companyId,
                manageUserId: this.customerSearch.manageUserId,
                saleUserId: this.customerSearch.saleUserId,
                provinceId: this.customerSearch.provinceId,
                districtId: this.customerSearch.districtId,
                customerTypeId: this.customerSearch.customerTypeId,
                customerSourceId: this.customerSearch.customerSourceId,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.currentSearch = '';
        this.router.navigate(['/customer-management/customer', {
            page: this.page,
            sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
        }]);
        this.loadAll();
    }

    search(query) {
        if (!query) {
            return this.clear();
        }
        this.page = 0;
        this.currentSearch = query;
        this.router.navigate(['/customer-management/customer', {
            search: this.currentSearch,
            page: this.page,
            sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
        }]);
        this.loadAll();
    }

    ngOnInit() {

        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInCustomers();

        // this.customerSourceService.query().subscribe(
        //     (res: HttpResponse<CustomerSource[]>) => this.customerSources = res.body,
        //     (res: HttpErrorResponse) => this.onError(res.message)
        // );
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Customer) {
        return item.id;
    }

    registerChangeInCustomers() {
        this.eventSubscriber = this.eventManager.subscribe('customerListModification', (response) => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private onSuccess(data, headers) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = headers.get('X-Total-Count');
        this.queryCount = this.totalItems;
        this.customers = data;
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }

    public deleteItem(id: number) {
        this.customerPopupService
            .open(CustomerDeleteDialogComponent as Component, id);
    }
}
