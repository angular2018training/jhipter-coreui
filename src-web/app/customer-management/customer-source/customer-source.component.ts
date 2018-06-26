import { Component, OnInit, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import {NgForm} from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { CustomerSource } from './customer-source.model';
import { CustomerSourcePopupService } from './customer-source-popup.service';
import { CustomerSourceService } from './customer-source.service';
import { CustomerSourceDeleteDialogComponent } from './customer-source-delete-dialog.component';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {CustomerSourceSearch} from './customer-source.search.model';

import {Company} from '../../setup/company/company.model';
import {CompanyService} from "../../setup/company/company.service";
@Component({
    selector: 'jhi-customer-source',
    templateUrl: './customer-source.component.html'
})
export class CustomerSourceComponent implements OnInit, OnDestroy {

currentAccount: any;
    customerSources: CustomerSource[];
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
    customerSourceSearch : CustomerSourceSearch;

    companies : Company[];
    @ViewChild(NgForm) searchForm: NgForm;

    constructor(
        private customerSourceService: CustomerSourceService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager,
        private companyService: CompanyService,

        private customerSourcePopupService: CustomerSourcePopupService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;

        });
        this.companies =[];

        this.customerSourceSearch = new CustomerSourceSearch();

        this.customerSourceSearch.code = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['code'] ?
                        this.activatedRoute.snapshot.params[' code'] : '';
        this.customerSourceSearch.name = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['name'] ?
                        this.activatedRoute.snapshot.params[' name'] : '';
        this.customerSourceSearch.description = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['description'] ?
                        this.activatedRoute.snapshot.params[' description'] : '';
        this.customerSourceSearch.companyId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['companyId'] ?
                         this.activatedRoute.snapshot.params['companyId'] : '';
    }

    loadAll() {
         var obj = {
         page: this.page -1,
         size: this.itemsPerPage,
         sort: this.sort(),
         code : this.customerSourceSearch.code,
         name : this.customerSourceSearch.name,
         description : this.customerSourceSearch.description,
           companyId : this.customerSourceSearch.companyId,
         };

        this.customerSourceService.searchExample(obj).subscribe(
          (res: HttpResponse<CustomerSource[]>) => this.onSuccess(res.body, res.headers),
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
        this.router.navigate(['/setup/customer-source'], {queryParams:
            {
                page: this.page,
                size: this.itemsPerPage,
                search: this.currentSearch,
                code : this.customerSourceSearch.code,
                name : this.customerSourceSearch.name,
                description : this.customerSourceSearch.description,
                companyId : this.customerSourceSearch.companyId,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.currentSearch = '';
        this.router.navigate(['/setup/customer-source', {
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
        this.router.navigate(['/setup/customer-source', {
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
        this.registerChangeInCustomerSources();
        this.companyService.query().subscribe(
            (res: HttpResponse<Company[]>) => this.companies = res.body,
            (res: HttpErrorResponse) => this.onError(res.message)
        );


    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: CustomerSource) {
        return item.id;
    }
    registerChangeInCustomerSources() {
        this.eventSubscriber = this.eventManager.subscribe('customerSourceListModification', (response) => this.loadAll());
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
        this.customerSources = data;
    }
    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }

    public deleteItem(id:number){
        this.customerSourcePopupService
            .open(CustomerSourceDeleteDialogComponent as Component, id);
    }
}
