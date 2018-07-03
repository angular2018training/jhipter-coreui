import { Component, OnInit, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import {NgForm} from '@angular/forms';

import {AlertService} from '../../shared/alert/alert-service';
import {ITEMS_QUERY_ALL} from '../../shared/';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { CustomerServices } from './customer-services.model';
import { CustomerServicesPopupService } from './customer-services-popup.service';
import { CustomerServicesService } from './customer-services.service';
import { CustomerServicesDeleteDialogComponent } from './customer-services-delete-dialog.component';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {CustomerServicesSearch} from './customer-services.search.model';
    import { Company, CompanyService } from '../company';
    import { OrderServices, OrderServicesService } from '../order-services';
    import { Quotation, QuotationService } from '../quotation';
    import { Customer, CustomerService } from '../customer';


@Component({
    selector: 'jhi-customer-services',
    templateUrl: './customer-services.component.html'
})
export class CustomerServicesComponent implements OnInit, OnDestroy {

currentAccount: any;
    customerServices: CustomerServices[];
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
    customerServicesSearch : CustomerServicesSearch;

    orderServices : OrderServices[];

    quotations : Quotation[];

    customers : Customer[];
    @ViewChild(NgForm) searchForm: NgForm;

    constructor(
        private customerServicesService: CustomerServicesService,
        private parseLinks: JhiParseLinks,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private alertService :AlertService,
        private router: Router,
        private eventManager: JhiEventManager,
        private orderServicesService: OrderServicesService,
        private quotationService: QuotationService,
        private customerService: CustomerService,

        private customerServicesPopupService: CustomerServicesPopupService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;

        });
        this.orderServices =[];
        this.quotations =[];
        this.customers =[];

        this.customerServicesSearch = new CustomerServicesSearch();

        this.customerServicesSearch.discountPercent = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['discountPercent'] ?
                        this.activatedRoute.snapshot.params[' discountPercent'] : '';
        this.customerServicesSearch.increasePercent = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['increasePercent'] ?
                        this.activatedRoute.snapshot.params[' increasePercent'] : '';
        this.customerServicesSearch.decreasePercent = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['decreasePercent'] ?
                        this.activatedRoute.snapshot.params[' decreasePercent'] : '';
        this.customerServicesSearch.orderServicesId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['orderServicesId'] ?
                         this.activatedRoute.snapshot.params['orderServicesId'] : '';
        this.customerServicesSearch.quotationId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['quotationId'] ?
                         this.activatedRoute.snapshot.params['quotationId'] : '';
        this.customerServicesSearch.customerParentId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['customerParentId'] ?
                         this.activatedRoute.snapshot.params['customerParentId'] : '';
    }

    loadAll() {
         var obj = {
         page: this.page -1,
         size: this.itemsPerPage,
         sort: this.sort(),
         discountPercent : this.customerServicesSearch.discountPercent,
         increasePercent : this.customerServicesSearch.increasePercent,
         decreasePercent : this.customerServicesSearch.decreasePercent,
         companyId :this.currentAccount.companyId,
         orderServicesId :this.customerServicesSearch.orderServicesId,
         quotationId :this.customerServicesSearch.quotationId,
         customerParentId :this.customerServicesSearch.customerParentId,
         };

        this.customerServicesService.searchExample(obj).subscribe(
          (res: HttpResponse<CustomerServices[]>) => this.onSuccess(res.body, res.headers),
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
        this.router.navigate(['/setup/customer-services'], {queryParams:
            {
                page: this.page,
                size: this.itemsPerPage,
                search: this.currentSearch,
                discountPercent : this.customerServicesSearch.discountPercent,
                increasePercent : this.customerServicesSearch.increasePercent,
                decreasePercent : this.customerServicesSearch.decreasePercent,
                companyId :this.currentAccount.companyId,
                orderServicesId :this.customerServicesSearch.orderServicesId,
                quotationId :this.customerServicesSearch.quotationId,
                customerParentId :this.customerServicesSearch.customerParentId,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.currentSearch = '';
        this.router.navigate(['/setup/customer-services', {
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
        this.router.navigate(['/setup/customer-services', {
            search: this.currentSearch,
            page: this.page,
            sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
        }]);
        this.loadAll();
    }

    ngOnInit() {

        this.principal.identity().then((account) => {
            this.currentAccount = account;
            this.loadAll();
            this.orderServicesService.query({"companyId.equals": this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL }).subscribe(
                (res: HttpResponse<OrderServices[]>) => this.orderServices = res.body,
                (res: HttpErrorResponse) => this.onError(res.message)
            );
            this.quotationService.query({"companyId.equals": this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL }).subscribe(
                (res: HttpResponse<Quotation[]>) => this.quotations = res.body,
                (res: HttpErrorResponse) => this.onError(res.message)
            );
            this.customerService.query({"companyId.equals": this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL }).subscribe(
                (res: HttpResponse<Customer[]>) => this.customers = res.body,
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        });
        this.registerChangeInCustomerServices();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: CustomerServices) {
        return item.id;
    }
    registerChangeInCustomerServices() {
        this.eventSubscriber = this.eventManager.subscribe('customerServicesListModification', (response) => this.loadAll());
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
        this.customerServices = data;
    }
    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    public deleteItem(id:number){
        this.customerServicesPopupService
            .open(CustomerServicesDeleteDialogComponent as Component, id);
    }
}
