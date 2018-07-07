import { Component, OnInit, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import {NgForm} from '@angular/forms';

import {AlertService} from '../../shared/alert/alert-service';
import {ITEMS_QUERY_ALL} from '../../shared/';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { QuotationCod } from '../../shared/model/quotation-cod.model';
import { QuotationCodPopupService } from './quotation-cod-popup.service';
import { QuotationCodService } from '../../shared/service/quotation-cod.service';
import { QuotationCodDeleteDialogComponent } from './quotation-cod-delete-dialog.component';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {QuotationCodSearch} from '../../shared/model/quotation-cod.search.model';
    import { Company } from '../../shared/model/company.model';
    import { CompanyService } from '../../shared/service/company.service';

    import { DistrictType } from '../../shared/model/district-type.model';
    import { DistrictTypeService } from '../../shared/service/district-type.service';

    import { Quotation } from '../../shared/model/quotation.model';
    import { QuotationService } from '../../shared/service/quotation.service';



@Component({
    selector: 'jhi-quotation-cod',
    templateUrl: './quotation-cod.component.html'
})
export class QuotationCodComponent implements OnInit, OnDestroy {

currentAccount: any;
    quotationCods: QuotationCod[];
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
    quotationCodSearch : QuotationCodSearch;

    districtTypes : DistrictType[];

    quotations : Quotation[];
    @ViewChild(NgForm) searchForm: NgForm;

    constructor(
        private quotationCodService: QuotationCodService,
        private parseLinks: JhiParseLinks,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private alertService :AlertService,
        private router: Router,
        private eventManager: JhiEventManager,
        private districtTypeService: DistrictTypeService,
        private quotationService: QuotationService,

        private quotationCodPopupService: QuotationCodPopupService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;

        });
        this.districtTypes =[];
        this.quotations =[];

        this.quotationCodSearch = new QuotationCodSearch();

        this.quotationCodSearch.amountFrom = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['amountFrom'] ?
                        this.activatedRoute.snapshot.params[' amountFrom'] : '';
        this.quotationCodSearch.amountTo = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['amountTo'] ?
                        this.activatedRoute.snapshot.params[' amountTo'] : '';
        this.quotationCodSearch.feeAmountMin = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['feeAmountMin'] ?
                        this.activatedRoute.snapshot.params[' feeAmountMin'] : '';
        this.quotationCodSearch.feeAmount = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['feeAmount'] ?
                        this.activatedRoute.snapshot.params[' feeAmount'] : '';
        this.quotationCodSearch.districtTypeId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['districtTypeId'] ?
                         this.activatedRoute.snapshot.params['districtTypeId'] : '';
        this.quotationCodSearch.quotationParentId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['quotationParentId'] ?
                         this.activatedRoute.snapshot.params['quotationParentId'] : '';
    }

    loadAll() {
         var obj = {
         page: this.page -1,
         size: this.itemsPerPage,
         sort: this.sort(),
         amountFrom : this.quotationCodSearch.amountFrom,
         amountTo : this.quotationCodSearch.amountTo,
         feeAmountMin : this.quotationCodSearch.feeAmountMin,
         feeAmount : this.quotationCodSearch.feeAmount,
         companyId :this.currentAccount.companyId,
         districtTypeId :this.quotationCodSearch.districtTypeId,
         quotationParentId :this.quotationCodSearch.quotationParentId,
         };

        this.quotationCodService.searchExample(obj).subscribe(
          (res: HttpResponse<QuotationCod[]>) => this.onSuccess(res.body, res.headers),
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
        this.router.navigate(['/setup/quotation-cod'], {queryParams:
            {
                page: this.page,
                size: this.itemsPerPage,
                search: this.currentSearch,
                amountFrom : this.quotationCodSearch.amountFrom,
                amountTo : this.quotationCodSearch.amountTo,
                feeAmountMin : this.quotationCodSearch.feeAmountMin,
                feeAmount : this.quotationCodSearch.feeAmount,
                companyId :this.currentAccount.companyId,
                districtTypeId :this.quotationCodSearch.districtTypeId,
                quotationParentId :this.quotationCodSearch.quotationParentId,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.currentSearch = '';
        this.router.navigate(['/setup/quotation-cod', {
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
        this.router.navigate(['/setup/quotation-cod', {
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
            this.districtTypeService.query({"companyId.equals": this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL }).subscribe(
                (res: HttpResponse<DistrictType[]>) => this.districtTypes = res.body,
                (res: HttpErrorResponse) => this.onError(res.message)
            );
            this.quotationService.query({"companyId.equals": this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL }).subscribe(
                (res: HttpResponse<Quotation[]>) => this.quotations = res.body,
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        });
        this.registerChangeInQuotationCods();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: QuotationCod) {
        return item.id;
    }
    registerChangeInQuotationCods() {
        this.eventSubscriber = this.eventManager.subscribe('quotationCodListModification', (response) => this.loadAll());
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
        this.quotationCods = data;
    }
    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    public deleteItem(id:number){
        this.quotationCodPopupService
            .open(QuotationCodDeleteDialogComponent as Component, id);
    }
}
