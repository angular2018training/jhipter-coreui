import { Component, OnInit, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import {NgForm} from '@angular/forms';

import {AlertService} from '../../shared/alert/alert-service';
import {ITEMS_QUERY_ALL} from '../../shared/';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { QuotationSubServices } from '../../shared/model/quotation-sub-services.model';
import { QuotationSubServicesPopupService } from './quotation-sub-services-popup.service';
import { QuotationSubServicesService } from '../../shared/service/quotation-sub-services.service';
import { QuotationSubServicesDeleteDialogComponent } from './quotation-sub-services-delete-dialog.component';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {QuotationSubServicesSearch} from '../../shared/model/quotation-sub-services.search.model';
    import { Company } from '../../shared/model/company.model';
    import { CompanyService } from '../../shared/service/company.service';

    import { OrderSubServicesType } from '../../shared/model/order-sub-services-type.model';
    import { OrderSubServicesTypeService } from '../../shared/service/order-sub-services-type.service';

    import { OrderSubServices } from '../../shared/model/order-sub-services.model';
    import { OrderSubServicesService } from '../../shared/service/order-sub-services.service';

    import { Quotation } from '../../shared/model/quotation.model';
    import { QuotationService } from '../../shared/service/quotation.service';



@Component({
    selector: 'jhi-quotation-sub-services',
    templateUrl: './quotation-sub-services.component.html'
})
export class QuotationSubServicesComponent implements OnInit, OnDestroy {

currentAccount: any;
    quotationSubServices: QuotationSubServices[];
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
    quotationSubServicesSearch : QuotationSubServicesSearch;

    orderSubServicesTypes : OrderSubServicesType[];

    orderSubServices : OrderSubServices[];

    quotations : Quotation[];
    @ViewChild(NgForm) searchForm: NgForm;

    constructor(
        private quotationSubServicesService: QuotationSubServicesService,
        private parseLinks: JhiParseLinks,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private alertService :AlertService,
        private router: Router,
        private eventManager: JhiEventManager,
        private orderSubServicesTypeService: OrderSubServicesTypeService,
        private orderSubServicesService: OrderSubServicesService,
        private quotationService: QuotationService,

        private quotationSubServicesPopupService: QuotationSubServicesPopupService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;

        });
        this.orderSubServicesTypes =[];
        this.orderSubServices =[];
        this.quotations =[];

        this.quotationSubServicesSearch = new QuotationSubServicesSearch();

        this.quotationSubServicesSearch.amountFrom = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['amountFrom'] ?
                        this.activatedRoute.snapshot.params[' amountFrom'] : '';
        this.quotationSubServicesSearch.amountTo = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['amountTo'] ?
                        this.activatedRoute.snapshot.params[' amountTo'] : '';
        this.quotationSubServicesSearch.feeAmountMin = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['feeAmountMin'] ?
                        this.activatedRoute.snapshot.params[' feeAmountMin'] : '';
        this.quotationSubServicesSearch.feeAmountMax = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['feeAmountMax'] ?
                        this.activatedRoute.snapshot.params[' feeAmountMax'] : '';
        this.quotationSubServicesSearch.feeAmount = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['feeAmount'] ?
                        this.activatedRoute.snapshot.params[' feeAmount'] : '';
        this.quotationSubServicesSearch.autoSelect = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['autoSelect'] ?
                        this.activatedRoute.snapshot.params[' autoSelect'] : '';
        this.quotationSubServicesSearch.orderSubServicesTypeId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['orderSubServicesTypeId'] ?
                         this.activatedRoute.snapshot.params['orderSubServicesTypeId'] : '';
        this.quotationSubServicesSearch.orderSubServicesId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['orderSubServicesId'] ?
                         this.activatedRoute.snapshot.params['orderSubServicesId'] : '';
        this.quotationSubServicesSearch.quotationParentId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['quotationParentId'] ?
                         this.activatedRoute.snapshot.params['quotationParentId'] : '';
    }

    loadAll() {
         var obj = {
         page: this.page -1,
         size: this.itemsPerPage,
         sort: this.sort(),
         amountFrom : this.quotationSubServicesSearch.amountFrom,
         amountTo : this.quotationSubServicesSearch.amountTo,
         feeAmountMin : this.quotationSubServicesSearch.feeAmountMin,
         feeAmountMax : this.quotationSubServicesSearch.feeAmountMax,
         feeAmount : this.quotationSubServicesSearch.feeAmount,
         autoSelect : this.quotationSubServicesSearch.autoSelect,
         companyId :this.currentAccount.companyId,
         orderSubServicesTypeId :this.quotationSubServicesSearch.orderSubServicesTypeId,
         orderSubServicesId :this.quotationSubServicesSearch.orderSubServicesId,
         quotationParentId :this.quotationSubServicesSearch.quotationParentId,
         };

        this.quotationSubServicesService.searchExample(obj).subscribe(
          (res: HttpResponse<QuotationSubServices[]>) => this.onSuccess(res.body, res.headers),
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
        this.router.navigate(['/setup/quotation-sub-services'], {queryParams:
            {
                page: this.page,
                size: this.itemsPerPage,
                search: this.currentSearch,
                amountFrom : this.quotationSubServicesSearch.amountFrom,
                amountTo : this.quotationSubServicesSearch.amountTo,
                feeAmountMin : this.quotationSubServicesSearch.feeAmountMin,
                feeAmountMax : this.quotationSubServicesSearch.feeAmountMax,
                feeAmount : this.quotationSubServicesSearch.feeAmount,
                autoSelect : this.quotationSubServicesSearch.autoSelect,
                companyId :this.currentAccount.companyId,
                orderSubServicesTypeId :this.quotationSubServicesSearch.orderSubServicesTypeId,
                orderSubServicesId :this.quotationSubServicesSearch.orderSubServicesId,
                quotationParentId :this.quotationSubServicesSearch.quotationParentId,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.currentSearch = '';
        this.router.navigate(['/setup/quotation-sub-services', {
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
        this.router.navigate(['/setup/quotation-sub-services', {
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
            this.orderSubServicesTypeService.query({"companyId.equals": this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL }).subscribe(
                (res: HttpResponse<OrderSubServicesType[]>) => this.orderSubServicesTypes = res.body,
                (res: HttpErrorResponse) => this.onError(res.message)
            );
            this.orderSubServicesService.query({"companyId.equals": this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL }).subscribe(
                (res: HttpResponse<OrderSubServices[]>) => this.orderSubServices = res.body,
                (res: HttpErrorResponse) => this.onError(res.message)
            );
            this.quotationService.query({"companyId.equals": this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL }).subscribe(
                (res: HttpResponse<Quotation[]>) => this.quotations = res.body,
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        });
        this.registerChangeInQuotationSubServices();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: QuotationSubServices) {
        return item.id;
    }
    registerChangeInQuotationSubServices() {
        this.eventSubscriber = this.eventManager.subscribe('quotationSubServicesListModification', (response) => this.loadAll());
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
        this.quotationSubServices = data;
    }
    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    public deleteItem(id:number){
        this.quotationSubServicesPopupService
            .open(QuotationSubServicesDeleteDialogComponent as Component, id);
    }
}
