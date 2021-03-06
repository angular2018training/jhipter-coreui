import { Component, OnInit, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import {NgForm} from '@angular/forms';

import {AlertService} from '../../shared/alert/alert-service';
import {ITEMS_QUERY_ALL} from '../../shared/';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { Company } from './company.model';
import { CompanyPopupService } from './company-popup.service';
import { CompanyService } from './company.service';
import { CompanyDeleteDialogComponent } from './company-delete-dialog.component';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {CompanySearch} from './company.search.model';


@Component({
    selector: 'jhi-company',
    templateUrl: './company.component.html'
})
export class CompanyComponent implements OnInit, OnDestroy {

currentAccount: any;
    companies: Company[];
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
    companySearch : CompanySearch;
    @ViewChild(NgForm) searchForm: NgForm;

    constructor(
        private companyService: CompanyService,
        private parseLinks: JhiParseLinks,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private alertService :AlertService,
        private router: Router,
        private eventManager: JhiEventManager,

        private companyPopupService: CompanyPopupService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;

        });

        this.companySearch = new CompanySearch();

        this.companySearch.code = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['code'] ?
                        this.activatedRoute.snapshot.params[' code'] : '';
        this.companySearch.name = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['name'] ?
                        this.activatedRoute.snapshot.params[' name'] : '';
        this.companySearch.address = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['address'] ?
                        this.activatedRoute.snapshot.params[' address'] : '';
        this.companySearch.phone = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['phone'] ?
                        this.activatedRoute.snapshot.params[' phone'] : '';
        this.companySearch.email = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['email'] ?
                        this.activatedRoute.snapshot.params[' email'] : '';
        this.companySearch.website = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['website'] ?
                        this.activatedRoute.snapshot.params[' website'] : '';
        this.companySearch.description = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['description'] ?
                        this.activatedRoute.snapshot.params[' description'] : '';
    }

    loadAll() {
         var obj = {
         page: this.page -1,
         size: this.itemsPerPage,
         sort: this.sort(),
         code : this.companySearch.code,
         name : this.companySearch.name,
         address : this.companySearch.address,
         phone : this.companySearch.phone,
         email : this.companySearch.email,
         website : this.companySearch.website,
         description : this.companySearch.description,
         };

        this.companyService.searchExample(obj).subscribe(
          (res: HttpResponse<Company[]>) => this.onSuccess(res.body, res.headers),
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
        this.router.navigate(['/setup/company'], {queryParams:
            {
                page: this.page,
                size: this.itemsPerPage,
                search: this.currentSearch,
                code : this.companySearch.code,
                name : this.companySearch.name,
                address : this.companySearch.address,
                phone : this.companySearch.phone,
                email : this.companySearch.email,
                website : this.companySearch.website,
                description : this.companySearch.description,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.currentSearch = '';
        this.router.navigate(['/setup/company', {
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
        this.router.navigate(['/setup/company', {
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
        });
        this.registerChangeInCompanies();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Company) {
        return item.id;
    }
    registerChangeInCompanies() {
        this.eventSubscriber = this.eventManager.subscribe('companyListModification', (response) => this.loadAll());
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
        this.companies = data;
    }
    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    public deleteItem(id:number){
        this.companyPopupService
            .open(CompanyDeleteDialogComponent as Component, id);
    }
}
