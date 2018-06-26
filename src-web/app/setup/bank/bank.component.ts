import { Component, OnInit, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import {NgForm} from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { Bank } from './bank.model';
import { BankPopupService } from './bank-popup.service';
import { BankService } from './bank.service';
import { BankDeleteDialogComponent } from './bank-delete-dialog.component';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {BankSearch} from './bank.search.model';

import {Company} from '../company/company.model';
import {CompanyService} from "../company/company.service";
@Component({
    selector: 'jhi-bank',
    templateUrl: './bank.component.html'
})
export class BankComponent implements OnInit, OnDestroy {

currentAccount: any;
    banks: Bank[];
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
    bankSearch : BankSearch;

    companies : Company[];
    @ViewChild(NgForm) searchForm: NgForm;

    constructor(
        private bankService: BankService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager,
        private companyService: CompanyService,

        private bankPopupService: BankPopupService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;

        });
        this.companies =[];

        this.bankSearch = new BankSearch();

        this.bankSearch.code = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['code'] ?
                        this.activatedRoute.snapshot.params[' code'] : '';
        this.bankSearch.name = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['name'] ?
                        this.activatedRoute.snapshot.params[' name'] : '';
        this.bankSearch.description = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['description'] ?
                        this.activatedRoute.snapshot.params[' description'] : '';
        this.bankSearch.companyId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['companyId'] ?
                         this.activatedRoute.snapshot.params['companyId'] : '';
    }

    loadAll() {
         var obj = {
         page: this.page -1,
         size: this.itemsPerPage,
         sort: this.sort(),
         code : this.bankSearch.code,
         name : this.bankSearch.name,
         description : this.bankSearch.description,
           companyId : this.bankSearch.companyId,
         };

        this.bankService.searchExample(obj).subscribe(
          (res: HttpResponse<Bank[]>) => this.onSuccess(res.body, res.headers),
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
        this.router.navigate(['/setup/bank'], {queryParams:
            {
                page: this.page,
                size: this.itemsPerPage,
                search: this.currentSearch,
                code : this.bankSearch.code,
                name : this.bankSearch.name,
                description : this.bankSearch.description,
                companyId : this.bankSearch.companyId,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.currentSearch = '';
        this.router.navigate(['/setup/bank', {
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
        this.router.navigate(['/setup/bank', {
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
        this.registerChangeInBanks();
        this.companyService.query().subscribe(
            (res: HttpResponse<Company[]>) => this.companies = res.body,
            (res: HttpErrorResponse) => this.onError(res.message)
        );


    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Bank) {
        return item.id;
    }
    registerChangeInBanks() {
        this.eventSubscriber = this.eventManager.subscribe('bankListModification', (response) => this.loadAll());
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
        this.banks = data;
    }
    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }

    public deleteItem(id:number){
        this.bankPopupService
            .open(BankDeleteDialogComponent as Component, id);
    }
}
