import { Component, OnInit, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import {NgForm} from '@angular/forms';

import {AlertService} from '../../shared/alert/alert-service';
import {ITEMS_QUERY_ALL} from '../../shared/';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { QuotationItem } from '../../shared/model/quotation-item.model';
import { QuotationItemPopupService } from './quotation-item-popup.service';
import { QuotationItemService } from '../../shared/service/quotation-item.service';
import { QuotationItemDeleteDialogComponent } from './quotation-item-delete-dialog.component';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {QuotationItemSearch} from '../../shared/model/quotation-item.search.model';
    import { Quotation } from '../../shared/model/quotation.model';
    import { QuotationService } from '../../shared/service/quotation.service';

    import { Company } from '../../shared/model/company.model';
    import { CompanyService } from '../../shared/service/company.service';



@Component({
    selector: 'jhi-quotation-item',
    templateUrl: './quotation-item.component.html'
})
export class QuotationItemComponent implements OnInit, OnDestroy {

currentAccount: any;
    quotationItems: QuotationItem[];
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
    quotationItemSearch : QuotationItemSearch;

    quotations : Quotation[];
    @ViewChild(NgForm) searchForm: NgForm;

    constructor(
        private quotationItemService: QuotationItemService,
        private parseLinks: JhiParseLinks,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private alertService :AlertService,
        private dataUtils: JhiDataUtils,
        private router: Router,
        private eventManager: JhiEventManager,
        private quotationService: QuotationService,

        private quotationItemPopupService: QuotationItemPopupService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;

        });
        this.quotations =[];

        this.quotationItemSearch = new QuotationItemSearch();

        this.quotationItemSearch.quotationFile = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['quotationFile'] ?
                        this.activatedRoute.snapshot.params[' quotationFile'] : '';
        this.quotationItemSearch.createDate = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['createDate'] ?
                        this.activatedRoute.snapshot.params[' createDate'] : '';
        this.quotationItemSearch.quotationId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['quotationId'] ?
                         this.activatedRoute.snapshot.params['quotationId'] : '';
    }

    loadAll() {
         var obj = {
         page: this.page -1,
         size: this.itemsPerPage,
         sort: this.sort(),
         quotationFile : this.quotationItemSearch.quotationFile,
         createDate : this.quotationItemSearch.createDate,
         quotationId :this.quotationItemSearch.quotationId,
         companyId :this.currentAccount.companyId,
         };

        this.quotationItemService.searchExample(obj).subscribe(
          (res: HttpResponse<QuotationItem[]>) => this.onSuccess(res.body, res.headers),
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
        this.router.navigate(['/setup/quotation-item'], {queryParams:
            {
                page: this.page,
                size: this.itemsPerPage,
                search: this.currentSearch,
                quotationFile : this.quotationItemSearch.quotationFile,
                createDate : this.quotationItemSearch.createDate,
                quotationId :this.quotationItemSearch.quotationId,
                companyId :this.currentAccount.companyId,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.currentSearch = '';
        this.router.navigate(['/setup/quotation-item', {
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
        this.router.navigate(['/setup/quotation-item', {
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
            this.quotationService.query({"companyId.equals": this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL }).subscribe(
                (res: HttpResponse<Quotation[]>) => this.quotations = res.body,
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        });
        this.registerChangeInQuotationItems();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: QuotationItem) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    registerChangeInQuotationItems() {
        this.eventSubscriber = this.eventManager.subscribe('quotationItemListModification', (response) => this.loadAll());
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
        this.quotationItems = data;
    }
    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    public deleteItem(id:number){
        this.quotationItemPopupService
            .open(QuotationItemDeleteDialogComponent as Component, id);
    }
}
