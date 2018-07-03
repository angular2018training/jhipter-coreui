import { Component, OnInit, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import {NgForm} from '@angular/forms';

import {AlertService} from '../../shared/alert/alert-service';
import {ITEMS_QUERY_ALL} from '../../shared/';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { Quotation } from '../../shared/model/quotation.model';
import { QuotationPopupService } from './quotation-popup.service';
import { QuotationService } from '../../shared/service/quotation.service';
import { QuotationDeleteDialogComponent } from './quotation-delete-dialog.component';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {QuotationSearch} from '../../shared/model/quotation.search.model';
    import { QuotationItem } from '../../shared/model/quotation-item.model';
    import { QuotationItemService } from '../../shared/service/quotation-item.service';

    import { Company } from '../../shared/model/company.model';
    import { CompanyService } from '../../shared/service/company.service';



@Component({
    selector: 'jhi-quotation',
    templateUrl: './quotation.component.html'
})
export class QuotationComponent implements OnInit, OnDestroy {

currentAccount: any;
    quotations: Quotation[];
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
    quotationSearch : QuotationSearch;

    quotationItems : QuotationItem[];
    @ViewChild(NgForm) searchForm: NgForm;

    constructor(
        private quotationService: QuotationService,
        private parseLinks: JhiParseLinks,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private alertService :AlertService,
        private router: Router,
        private eventManager: JhiEventManager,

        private quotationPopupService: QuotationPopupService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;

        });

        this.quotationSearch = new QuotationSearch();

        this.quotationSearch.name = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['name'] ?
                        this.activatedRoute.snapshot.params[' name'] : '';
        this.quotationSearch.isActive = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['isActive'] ?
                        this.activatedRoute.snapshot.params[' isActive'] : '';
        this.quotationSearch.description = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['description'] ?
                        this.activatedRoute.snapshot.params[' description'] : '';
        this.quotationSearch.createDate = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['createDate'] ?
                        this.activatedRoute.snapshot.params[' createDate'] : '';
    }

    loadAll() {
         var obj = {
         page: this.page -1,
         size: this.itemsPerPage,
         sort: this.sort(),
         name : this.quotationSearch.name,
         isActive : this.quotationSearch.isActive,
         description : this.quotationSearch.description,
         createDate : this.quotationSearch.createDate,
         companyId :this.currentAccount.companyId,
         };

        this.quotationService.searchExample(obj).subscribe(
          (res: HttpResponse<Quotation[]>) => this.onSuccess(res.body, res.headers),
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
        this.router.navigate(['/setup/quotation'], {queryParams:
            {
                page: this.page,
                size: this.itemsPerPage,
                search: this.currentSearch,
                name : this.quotationSearch.name,
                isActive : this.quotationSearch.isActive,
                description : this.quotationSearch.description,
                createDate : this.quotationSearch.createDate,
                companyId :this.currentAccount.companyId,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.currentSearch = '';
        this.router.navigate(['/setup/quotation', {
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
        this.router.navigate(['/setup/quotation', {
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
        this.registerChangeInQuotations();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Quotation) {
        return item.id;
    }
    registerChangeInQuotations() {
        this.eventSubscriber = this.eventManager.subscribe('quotationListModification', (response) => this.loadAll());
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
        this.quotations = data;
    }
    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    public deleteItem(id:number){
        this.quotationPopupService
            .open(QuotationDeleteDialogComponent as Component, id);
    }
}
