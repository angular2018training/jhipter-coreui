import { Component, OnInit, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import {NgForm} from '@angular/forms';

import {AlertService} from '../../shared/alert/alert-service';
import {ITEMS_QUERY_ALL} from '../../shared/';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { OrderServices } from '../../shared/model/order-services.model';
import { OrderServicesPopupService } from './order-services-popup.service';
import { OrderServicesService } from '../../shared/service/order-services.service';
import { OrderServicesDeleteDialogComponent } from './order-services-delete-dialog.component';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {OrderServicesSearch} from '../../shared/model/order-services.search.model';
    import { Company } from '../../shared/model/company.model';
    import { CompanyService } from '../../shared/service/company.service';

    import { OrderServicesType } from '../../shared/model/order-services-type.model';
    import { OrderServicesTypeService } from '../../shared/service/order-services-type.service';

    import { Quotation } from '../../shared/model/quotation.model';
    import { QuotationService } from '../../shared/service/quotation.service';



@Component({
    selector: 'jhi-order-services',
    templateUrl: './order-services.component.html'
})
export class OrderServicesComponent implements OnInit, OnDestroy {

currentAccount: any;
    orderServices: OrderServices[];
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
    orderServicesSearch : OrderServicesSearch;

    orderServicesTypes : OrderServicesType[];

    quotations : Quotation[];
    @ViewChild(NgForm) searchForm: NgForm;

    constructor(
        private orderServicesService: OrderServicesService,
        private parseLinks: JhiParseLinks,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private alertService :AlertService,
        private router: Router,
        private eventManager: JhiEventManager,
        private orderServicesTypeService: OrderServicesTypeService,

        private orderServicesPopupService: OrderServicesPopupService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;

        });
        this.orderServicesTypes =[];

        this.orderServicesSearch = new OrderServicesSearch();

        this.orderServicesSearch.code = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['code'] ?
                        this.activatedRoute.snapshot.params[' code'] : '';
        this.orderServicesSearch.name = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['name'] ?
                        this.activatedRoute.snapshot.params[' name'] : '';
        this.orderServicesSearch.isActive = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['isActive'] ?
                        this.activatedRoute.snapshot.params[' isActive'] : '';
        this.orderServicesSearch.description = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['description'] ?
                        this.activatedRoute.snapshot.params[' description'] : '';
        this.orderServicesSearch.orderServicesTypeId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['orderServicesTypeId'] ?
                         this.activatedRoute.snapshot.params['orderServicesTypeId'] : '';
    }

    loadAll() {
         var obj = {
         page: this.page -1,
         size: this.itemsPerPage,
         sort: this.sort(),
         code : this.orderServicesSearch.code,
         name : this.orderServicesSearch.name,
         isActive : this.orderServicesSearch.isActive,
         description : this.orderServicesSearch.description,
         companyId :this.currentAccount.companyId,
         orderServicesTypeId :this.orderServicesSearch.orderServicesTypeId,
         };

        this.orderServicesService.searchExample(obj).subscribe(
          (res: HttpResponse<OrderServices[]>) => this.onSuccess(res.body, res.headers),
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
        this.router.navigate(['/setup/order-services'], {queryParams:
            {
                page: this.page,
                size: this.itemsPerPage,
                search: this.currentSearch,
                code : this.orderServicesSearch.code,
                name : this.orderServicesSearch.name,
                isActive : this.orderServicesSearch.isActive,
                description : this.orderServicesSearch.description,
                companyId :this.currentAccount.companyId,
                orderServicesTypeId :this.orderServicesSearch.orderServicesTypeId,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.currentSearch = '';
        this.router.navigate(['/setup/order-services', {
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
        this.router.navigate(['/setup/order-services', {
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
        this.registerChangeInOrderServices();
        this.orderServicesTypeService.query().subscribe(
            (res: HttpResponse<OrderServicesType[]>) => this.orderServicesTypes = res.body,
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: OrderServices) {
        return item.id;
    }
    registerChangeInOrderServices() {
        this.eventSubscriber = this.eventManager.subscribe('orderServicesListModification', (response) => this.loadAll());
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
        this.orderServices = data;
    }
    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    public deleteItem(id:number){
        this.orderServicesPopupService
            .open(OrderServicesDeleteDialogComponent as Component, id);
    }
}
