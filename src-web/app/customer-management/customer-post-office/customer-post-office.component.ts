import { Component, OnInit, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import {NgForm} from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { CustomerPostOffice } from './customer-post-office.model';
import { CustomerPostOfficePopupService } from './customer-post-office-popup.service';
import { CustomerPostOfficeService } from './customer-post-office.service';
import { CustomerPostOfficeDeleteDialogComponent } from './customer-post-office-delete-dialog.component';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {CustomerPostOfficeSearch} from './customer-post-office.search.model';

import {Customer} from '../customer/customer.model';
import {CustomerService} from "../customer/customer.service";

import {Company} from '../../setup/company/company.model';
import {CompanyService} from "../../setup/company/company.service";

import {PostOffice} from '../../setup/post-office/post-office.model';
import {PostOfficeService} from "../../setup/post-office/post-office.service";
@Component({
    selector: 'jhi-customer-post-office',
    templateUrl: './customer-post-office.component.html'
})
export class CustomerPostOfficeComponent implements OnInit, OnDestroy {

currentAccount: any;
    customerPostOffices: CustomerPostOffice[];
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
    customerPostOfficeSearch : CustomerPostOfficeSearch;

    customers : Customer[];

    companies : Company[];

    postOffices : PostOffice[];
    @ViewChild(NgForm) searchForm: NgForm;

    constructor(
        private customerPostOfficeService: CustomerPostOfficeService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager,
        private customerService: CustomerService,
        private companyService: CompanyService,
        private postOfficeService: PostOfficeService,

        private customerPostOfficePopupService: CustomerPostOfficePopupService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;

        });
        this.customers =[];
        this.companies =[];
        this.postOffices =[];

        this.customerPostOfficeSearch = new CustomerPostOfficeSearch();

        this.customerPostOfficeSearch.code = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['code'] ?
                        this.activatedRoute.snapshot.params[' code'] : '';
        this.customerPostOfficeSearch.isActive = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['isActive'] ?
                        this.activatedRoute.snapshot.params[' isActive'] : '';
        this.customerPostOfficeSearch.createDate = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['createDate'] ?
                        this.activatedRoute.snapshot.params[' createDate'] : '';
        this.customerPostOfficeSearch.customerId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['customerId'] ?
                         this.activatedRoute.snapshot.params['customerId'] : '';
        this.customerPostOfficeSearch.companyId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['companyId'] ?
                         this.activatedRoute.snapshot.params['companyId'] : '';
        this.customerPostOfficeSearch.postOfficeId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['postOfficeId'] ?
                         this.activatedRoute.snapshot.params['postOfficeId'] : '';
    }

    loadAll() {
         var obj = {
         page: this.page -1,
         size: this.itemsPerPage,
         sort: this.sort(),
         code : this.customerPostOfficeSearch.code,
         isActive : this.customerPostOfficeSearch.isActive,
         createDate : this.customerPostOfficeSearch.createDate,
           customerId : this.customerPostOfficeSearch.customerId,
           companyId : this.customerPostOfficeSearch.companyId,
           postOfficeId : this.customerPostOfficeSearch.postOfficeId,
         };

        this.customerPostOfficeService.searchExample(obj).subscribe(
          (res: HttpResponse<CustomerPostOffice[]>) => this.onSuccess(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );

    }
    searchInForm(){
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
        this.router.navigate(['/setup/customer-post-office'], {queryParams:
            {
                page: this.page,
                size: this.itemsPerPage,
                search: this.currentSearch,
                code : this.customerPostOfficeSearch.code,
                isActive : this.customerPostOfficeSearch.isActive,
                createDate : this.customerPostOfficeSearch.createDate,
                customerId : this.customerPostOfficeSearch.customerId,
                companyId : this.customerPostOfficeSearch.companyId,
                postOfficeId : this.customerPostOfficeSearch.postOfficeId,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.currentSearch = '';
        this.router.navigate(['/setup/customer-post-office', {
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
        this.router.navigate(['/setup/customer-post-office', {
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
        this.registerChangeInCustomerPostOffices();
        this.customerService.query().subscribe(
            (res: HttpResponse<Customer[]>) => this.customers = res.body,
            (res: HttpErrorResponse) => this.onError(res.message)
        );

        this.companyService.query().subscribe(
            (res: HttpResponse<Company[]>) => this.companies = res.body,
            (res: HttpErrorResponse) => this.onError(res.message)
        );

        this.postOfficeService.query().subscribe(
            (res: HttpResponse<PostOffice[]>) => this.postOffices = res.body,
            (res: HttpErrorResponse) => this.onError(res.message)
        );


    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: CustomerPostOffice) {
        return item.id;
    }
    registerChangeInCustomerPostOffices() {
        this.eventSubscriber = this.eventManager.subscribe('customerPostOfficeListModification', (response) => this.loadAll());
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
        // this.page = pagingParams.page;
        this.customerPostOffices = data;
    }
    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }

    public deleteItem(id:number){
        this.customerPostOfficePopupService
            .open(CustomerPostOfficeDeleteDialogComponent as Component, id);
    }
}
