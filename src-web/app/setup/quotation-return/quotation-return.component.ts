import { Component, OnInit, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import {NgForm} from '@angular/forms';

import {AlertService} from '../../shared/alert/alert-service';
import {ITEMS_QUERY_ALL} from '../../shared/';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { QuotationReturn } from '../../shared/model/quotation-return.model';
import { QuotationReturnPopupService } from './quotation-return-popup.service';
import { QuotationReturnService } from '../../shared/service/quotation-return.service';
import { QuotationReturnDeleteDialogComponent } from './quotation-return-delete-dialog.component';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {QuotationReturnSearch} from '../../shared/model/quotation-return.search.model';
    import { Company } from '../../shared/model/company.model';
    import { CompanyService } from '../../shared/service/company.service';

    import { DistrictType } from '../../shared/model/district-type.model';
    import { DistrictTypeService } from '../../shared/service/district-type.service';

    import { Region } from '../../shared/model/region.model';
    import { RegionService } from '../../shared/service/region.service';

    import { Quotation } from '../../shared/model/quotation.model';
    import { QuotationService } from '../../shared/service/quotation.service';



@Component({
    selector: 'jhi-quotation-return',
    templateUrl: './quotation-return.component.html'
})
export class QuotationReturnComponent implements OnInit, OnDestroy {

currentAccount: any;
    quotationReturns: QuotationReturn[];
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
    quotationReturnSearch : QuotationReturnSearch;

    districtTypes : DistrictType[];

    regions : Region[];

    quotations : Quotation[];
    @ViewChild(NgForm) searchForm: NgForm;

    constructor(
        private quotationReturnService: QuotationReturnService,
        private parseLinks: JhiParseLinks,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private alertService :AlertService,
        private router: Router,
        private eventManager: JhiEventManager,
        private districtTypeService: DistrictTypeService,
        private regionService: RegionService,
        private quotationService: QuotationService,

        private quotationReturnPopupService: QuotationReturnPopupService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;

        });
        this.districtTypes =[];
        this.regions =[];
        this.quotations =[];

        this.quotationReturnSearch = new QuotationReturnSearch();

        this.quotationReturnSearch.feePercent = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['feePercent'] ?
                        this.activatedRoute.snapshot.params[' feePercent'] : '';
        this.quotationReturnSearch.districtTypeId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['districtTypeId'] ?
                         this.activatedRoute.snapshot.params['districtTypeId'] : '';
        this.quotationReturnSearch.regionId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['regionId'] ?
                         this.activatedRoute.snapshot.params['regionId'] : '';
        this.quotationReturnSearch.quotationParentId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['quotationParentId'] ?
                         this.activatedRoute.snapshot.params['quotationParentId'] : '';
    }

    loadAll() {
         var obj = {
         page: this.page -1,
         size: this.itemsPerPage,
         sort: this.sort(),
         feePercent : this.quotationReturnSearch.feePercent,
         companyId :this.currentAccount.companyId,
         districtTypeId :this.quotationReturnSearch.districtTypeId,
         regionId :this.quotationReturnSearch.regionId,
         quotationParentId :this.quotationReturnSearch.quotationParentId,
         };

        this.quotationReturnService.searchExample(obj).subscribe(
          (res: HttpResponse<QuotationReturn[]>) => this.onSuccess(res.body, res.headers),
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
        this.router.navigate(['/setup/quotation-return'], {queryParams:
            {
                page: this.page,
                size: this.itemsPerPage,
                search: this.currentSearch,
                feePercent : this.quotationReturnSearch.feePercent,
                companyId :this.currentAccount.companyId,
                districtTypeId :this.quotationReturnSearch.districtTypeId,
                regionId :this.quotationReturnSearch.regionId,
                quotationParentId :this.quotationReturnSearch.quotationParentId,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.currentSearch = '';
        this.router.navigate(['/setup/quotation-return', {
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
        this.router.navigate(['/setup/quotation-return', {
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
            this.regionService.query({"companyId.equals": this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL }).subscribe(
                (res: HttpResponse<Region[]>) => this.regions = res.body,
                (res: HttpErrorResponse) => this.onError(res.message)
            );
            this.quotationService.query({"companyId.equals": this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL }).subscribe(
                (res: HttpResponse<Quotation[]>) => this.quotations = res.body,
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        });
        this.registerChangeInQuotationReturns();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: QuotationReturn) {
        return item.id;
    }
    registerChangeInQuotationReturns() {
        this.eventSubscriber = this.eventManager.subscribe('quotationReturnListModification', (response) => this.loadAll());
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
        this.quotationReturns = data;
    }
    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    public deleteItem(id:number){
        this.quotationReturnPopupService
            .open(QuotationReturnDeleteDialogComponent as Component, id);
    }
}
