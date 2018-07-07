import { Component, OnInit, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import {NgForm} from '@angular/forms';

import {AlertService} from '../../shared/alert/alert-service';
import {ITEMS_QUERY_ALL} from '../../shared/';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { QuotationDomesticDelivery } from '../../shared/model/quotation-domestic-delivery.model';
import { QuotationDomesticDeliveryPopupService } from './quotation-domestic-delivery-popup.service';
import { QuotationDomesticDeliveryService } from '../../shared/service/quotation-domestic-delivery.service';
import { QuotationDomesticDeliveryDeleteDialogComponent } from './quotation-domestic-delivery-delete-dialog.component';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {QuotationDomesticDeliverySearch} from '../../shared/model/quotation-domestic-delivery.search.model';
    import { Company } from '../../shared/model/company.model';
    import { CompanyService } from '../../shared/service/company.service';

    import { DistrictType } from '../../shared/model/district-type.model';
    import { DistrictTypeService } from '../../shared/service/district-type.service';

    import { Region } from '../../shared/model/region.model';
    import { RegionService } from '../../shared/service/region.service';

    import { Weight } from '../../shared/model/weight.model';
    import { WeightService } from '../../shared/service/weight.service';

    import { Quotation } from '../../shared/model/quotation.model';
    import { QuotationService } from '../../shared/service/quotation.service';



@Component({
    selector: 'jhi-quotation-domestic-delivery',
    templateUrl: './quotation-domestic-delivery.component.html'
})
export class QuotationDomesticDeliveryComponent implements OnInit, OnDestroy {

currentAccount: any;
    quotationDomesticDeliveries: QuotationDomesticDelivery[];
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
    quotationDomesticDeliverySearch : QuotationDomesticDeliverySearch;

    districtTypes : DistrictType[];

    regions : Region[];

    weights : Weight[];

    quotations : Quotation[];
    @ViewChild(NgForm) searchForm: NgForm;

    constructor(
        private quotationDomesticDeliveryService: QuotationDomesticDeliveryService,
        private parseLinks: JhiParseLinks,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private alertService :AlertService,
        private router: Router,
        private eventManager: JhiEventManager,
        private districtTypeService: DistrictTypeService,
        private regionService: RegionService,
        private weightService: WeightService,
        private quotationService: QuotationService,

        private quotationDomesticDeliveryPopupService: QuotationDomesticDeliveryPopupService
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
        this.weights =[];
        this.quotations =[];

        this.quotationDomesticDeliverySearch = new QuotationDomesticDeliverySearch();

        this.quotationDomesticDeliverySearch.amount = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['amount'] ?
                        this.activatedRoute.snapshot.params[' amount'] : '';
        this.quotationDomesticDeliverySearch.districtTypeId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['districtTypeId'] ?
                         this.activatedRoute.snapshot.params['districtTypeId'] : '';
        this.quotationDomesticDeliverySearch.regionId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['regionId'] ?
                         this.activatedRoute.snapshot.params['regionId'] : '';
        this.quotationDomesticDeliverySearch.weightId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['weightId'] ?
                         this.activatedRoute.snapshot.params['weightId'] : '';
        this.quotationDomesticDeliverySearch.quotationParentId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['quotationParentId'] ?
                         this.activatedRoute.snapshot.params['quotationParentId'] : '';
    }

    loadAll() {
         var obj = {
         page: this.page -1,
         size: this.itemsPerPage,
         sort: this.sort(),
         amount : this.quotationDomesticDeliverySearch.amount,
         companyId :this.currentAccount.companyId,
         districtTypeId :this.quotationDomesticDeliverySearch.districtTypeId,
         regionId :this.quotationDomesticDeliverySearch.regionId,
         weightId :this.quotationDomesticDeliverySearch.weightId,
         quotationParentId :this.quotationDomesticDeliverySearch.quotationParentId,
         };

        this.quotationDomesticDeliveryService.searchExample(obj).subscribe(
          (res: HttpResponse<QuotationDomesticDelivery[]>) => this.onSuccess(res.body, res.headers),
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
        this.router.navigate(['/setup/quotation-domestic-delivery'], {queryParams:
            {
                page: this.page,
                size: this.itemsPerPage,
                search: this.currentSearch,
                amount : this.quotationDomesticDeliverySearch.amount,
                companyId :this.currentAccount.companyId,
                districtTypeId :this.quotationDomesticDeliverySearch.districtTypeId,
                regionId :this.quotationDomesticDeliverySearch.regionId,
                weightId :this.quotationDomesticDeliverySearch.weightId,
                quotationParentId :this.quotationDomesticDeliverySearch.quotationParentId,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.currentSearch = '';
        this.router.navigate(['/setup/quotation-domestic-delivery', {
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
        this.router.navigate(['/setup/quotation-domestic-delivery', {
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
        this.registerChangeInQuotationDomesticDeliveries();
        this.weightService.query().subscribe(
            (res: HttpResponse<Weight[]>) => this.weights = res.body,
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: QuotationDomesticDelivery) {
        return item.id;
    }
    registerChangeInQuotationDomesticDeliveries() {
        this.eventSubscriber = this.eventManager.subscribe('quotationDomesticDeliveryListModification', (response) => this.loadAll());
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
        this.quotationDomesticDeliveries = data;
    }
    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    public deleteItem(id:number){
        this.quotationDomesticDeliveryPopupService
            .open(QuotationDomesticDeliveryDeleteDialogComponent as Component, id);
    }
}
