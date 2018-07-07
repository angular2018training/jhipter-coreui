import { Component, OnInit, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import {NgForm} from '@angular/forms';

import {AlertService} from '../../shared/alert/alert-service';
import {ITEMS_QUERY_ALL} from '../../shared/';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { OrderSubServicesType } from '../../shared/model/order-sub-services-type.model';
import { OrderSubServicesTypePopupService } from './order-sub-services-type-popup.service';
import { OrderSubServicesTypeService } from '../../shared/service/order-sub-services-type.service';
import { OrderSubServicesTypeDeleteDialogComponent } from './order-sub-services-type-delete-dialog.component';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {OrderSubServicesTypeSearch} from '../../shared/model/order-sub-services-type.search.model';
    import { Company } from '../../shared/model/company.model';
    import { CompanyService } from '../../shared/service/company.service';



@Component({
    selector: 'jhi-order-sub-services-type',
    templateUrl: './order-sub-services-type.component.html'
})
export class OrderSubServicesTypeComponent implements OnInit, OnDestroy {

currentAccount: any;
    orderSubServicesTypes: OrderSubServicesType[];
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
    orderSubServicesTypeSearch : OrderSubServicesTypeSearch;
    @ViewChild(NgForm) searchForm: NgForm;

    constructor(
        private orderSubServicesTypeService: OrderSubServicesTypeService,
        private parseLinks: JhiParseLinks,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private alertService :AlertService,
        private router: Router,
        private eventManager: JhiEventManager,

        private orderSubServicesTypePopupService: OrderSubServicesTypePopupService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;

        });

        this.orderSubServicesTypeSearch = new OrderSubServicesTypeSearch();

        this.orderSubServicesTypeSearch.code = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['code'] ?
                        this.activatedRoute.snapshot.params[' code'] : '';
        this.orderSubServicesTypeSearch.name = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['name'] ?
                        this.activatedRoute.snapshot.params[' name'] : '';
        this.orderSubServicesTypeSearch.description = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['description'] ?
                        this.activatedRoute.snapshot.params[' description'] : '';
    }

    loadAll() {
         var obj = {
         page: this.page -1,
         size: this.itemsPerPage,
         sort: this.sort(),
         code : this.orderSubServicesTypeSearch.code,
         name : this.orderSubServicesTypeSearch.name,
         description : this.orderSubServicesTypeSearch.description,
         companyId :this.currentAccount.companyId,
         };

        this.orderSubServicesTypeService.searchExample(obj).subscribe(
          (res: HttpResponse<OrderSubServicesType[]>) => this.onSuccess(res.body, res.headers),
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
        this.router.navigate(['/setup/order-sub-services-type'], {queryParams:
            {
                page: this.page,
                size: this.itemsPerPage,
                search: this.currentSearch,
                code : this.orderSubServicesTypeSearch.code,
                name : this.orderSubServicesTypeSearch.name,
                description : this.orderSubServicesTypeSearch.description,
                companyId :this.currentAccount.companyId,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.currentSearch = '';
        this.router.navigate(['/setup/order-sub-services-type', {
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
        this.router.navigate(['/setup/order-sub-services-type', {
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
        this.registerChangeInOrderSubServicesTypes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: OrderSubServicesType) {
        return item.id;
    }
    registerChangeInOrderSubServicesTypes() {
        this.eventSubscriber = this.eventManager.subscribe('orderSubServicesTypeListModification', (response) => this.loadAll());
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
        this.orderSubServicesTypes = data;
    }
    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    public deleteItem(id:number){
        this.orderSubServicesTypePopupService
            .open(OrderSubServicesTypeDeleteDialogComponent as Component, id);
    }
}
