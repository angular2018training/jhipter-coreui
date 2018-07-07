import { Component, OnInit, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import {NgForm} from '@angular/forms';

import {AlertService} from '../../shared/alert/alert-service';
import {ITEMS_QUERY_ALL} from '../../shared/';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { QuotationInsurance } from '../../shared/model/quotation-insurance.model';
import { QuotationInsurancePopupService } from './quotation-insurance-popup.service';
import { QuotationInsuranceService } from '../../shared/service/quotation-insurance.service';
import { QuotationInsuranceDeleteDialogComponent } from './quotation-insurance-delete-dialog.component';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {QuotationInsuranceSearch} from '../../shared/model/quotation-insurance.search.model';
    import { Company } from '../../shared/model/company.model';
    import { CompanyService } from '../../shared/service/company.service';

    import { DistrictType } from '../../shared/model/district-type.model';
    import { DistrictTypeService } from '../../shared/service/district-type.service';

    import { Quotation } from '../../shared/model/quotation.model';
    import { QuotationService } from '../../shared/service/quotation.service';



@Component({
    selector: 'jhi-quotation-insurance',
    templateUrl: './quotation-insurance.component.html'
})
export class QuotationInsuranceComponent implements OnInit, OnDestroy {

currentAccount: any;
    quotationInsurances: QuotationInsurance[];
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
    quotationInsuranceSearch : QuotationInsuranceSearch;

    districtTypes : DistrictType[];

    quotations : Quotation[];
    @ViewChild(NgForm) searchForm: NgForm;

    constructor(
        private quotationInsuranceService: QuotationInsuranceService,
        private parseLinks: JhiParseLinks,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private alertService :AlertService,
        private router: Router,
        private eventManager: JhiEventManager,
        private districtTypeService: DistrictTypeService,
        private quotationService: QuotationService,

        private quotationInsurancePopupService: QuotationInsurancePopupService
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

        this.quotationInsuranceSearch = new QuotationInsuranceSearch();

        this.quotationInsuranceSearch.amountFrom = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['amountFrom'] ?
                        this.activatedRoute.snapshot.params[' amountFrom'] : '';
        this.quotationInsuranceSearch.amountTo = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['amountTo'] ?
                        this.activatedRoute.snapshot.params[' amountTo'] : '';
        this.quotationInsuranceSearch.feeAmountMin = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['feeAmountMin'] ?
                        this.activatedRoute.snapshot.params[' feeAmountMin'] : '';
        this.quotationInsuranceSearch.feeAmountMax = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['feeAmountMax'] ?
                        this.activatedRoute.snapshot.params[' feeAmountMax'] : '';
        this.quotationInsuranceSearch.feePercent = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['feePercent'] ?
                        this.activatedRoute.snapshot.params[' feePercent'] : '';
        this.quotationInsuranceSearch.districtTypeId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['districtTypeId'] ?
                         this.activatedRoute.snapshot.params['districtTypeId'] : '';
        this.quotationInsuranceSearch.quotationParentId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['quotationParentId'] ?
                         this.activatedRoute.snapshot.params['quotationParentId'] : '';
    }

    loadAll() {
         var obj = {
         page: this.page -1,
         size: this.itemsPerPage,
         sort: this.sort(),
         amountFrom : this.quotationInsuranceSearch.amountFrom,
         amountTo : this.quotationInsuranceSearch.amountTo,
         feeAmountMin : this.quotationInsuranceSearch.feeAmountMin,
         feeAmountMax : this.quotationInsuranceSearch.feeAmountMax,
         feePercent : this.quotationInsuranceSearch.feePercent,
         companyId :this.currentAccount.companyId,
         districtTypeId :this.quotationInsuranceSearch.districtTypeId,
         quotationParentId :this.quotationInsuranceSearch.quotationParentId,
         };

        this.quotationInsuranceService.searchExample(obj).subscribe(
          (res: HttpResponse<QuotationInsurance[]>) => this.onSuccess(res.body, res.headers),
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
        this.router.navigate(['/setup/quotation-insurance'], {queryParams:
            {
                page: this.page,
                size: this.itemsPerPage,
                search: this.currentSearch,
                amountFrom : this.quotationInsuranceSearch.amountFrom,
                amountTo : this.quotationInsuranceSearch.amountTo,
                feeAmountMin : this.quotationInsuranceSearch.feeAmountMin,
                feeAmountMax : this.quotationInsuranceSearch.feeAmountMax,
                feePercent : this.quotationInsuranceSearch.feePercent,
                companyId :this.currentAccount.companyId,
                districtTypeId :this.quotationInsuranceSearch.districtTypeId,
                quotationParentId :this.quotationInsuranceSearch.quotationParentId,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.currentSearch = '';
        this.router.navigate(['/setup/quotation-insurance', {
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
        this.router.navigate(['/setup/quotation-insurance', {
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
        this.registerChangeInQuotationInsurances();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: QuotationInsurance) {
        return item.id;
    }
    registerChangeInQuotationInsurances() {
        this.eventSubscriber = this.eventManager.subscribe('quotationInsuranceListModification', (response) => this.loadAll());
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
        this.quotationInsurances = data;
    }
    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    public deleteItem(id:number){
        this.quotationInsurancePopupService
            .open(QuotationInsuranceDeleteDialogComponent as Component, id);
    }
}
