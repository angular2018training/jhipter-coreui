import { Component, OnInit, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import {NgForm} from '@angular/forms';

import {AlertService} from '../../shared/alert/alert-service';
import {ITEMS_QUERY_ALL} from '../../shared/';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { OrderStatus } from './order-status.model';
import { OrderStatusPopupService } from './order-status-popup.service';
import { OrderStatusService } from './order-status.service';
import { OrderStatusDeleteDialogComponent } from './order-status-delete-dialog.component';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {OrderStatusSearch} from './order-status.search.model';
    import { Company, CompanyService } from '../company';


@Component({
    selector: 'jhi-order-status',
    templateUrl: './order-status.component.html'
})
export class OrderStatusComponent implements OnInit, OnDestroy {

currentAccount: any;
    orderStatuses: OrderStatus[];
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
    orderStatusSearch : OrderStatusSearch;
    @ViewChild(NgForm) searchForm: NgForm;

    constructor(
        private orderStatusService: OrderStatusService,
        private parseLinks: JhiParseLinks,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private alertService :AlertService,
        private router: Router,
        private eventManager: JhiEventManager,

        private orderStatusPopupService: OrderStatusPopupService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;

        });

        this.orderStatusSearch = new OrderStatusSearch();

        this.orderStatusSearch.code = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['code'] ?
                        this.activatedRoute.snapshot.params[' code'] : '';
        this.orderStatusSearch.name = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['name'] ?
                        this.activatedRoute.snapshot.params[' name'] : '';
        this.orderStatusSearch.description = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['description'] ?
                        this.activatedRoute.snapshot.params[' description'] : '';
    }

    loadAll() {
         var obj = {
         page: this.page -1,
         size: this.itemsPerPage,
         sort: this.sort(),
         code : this.orderStatusSearch.code,
         name : this.orderStatusSearch.name,
         description : this.orderStatusSearch.description,
         companyId :this.currentAccount.companyId,
         };

        this.orderStatusService.searchExample(obj).subscribe(
          (res: HttpResponse<OrderStatus[]>) => this.onSuccess(res.body, res.headers),
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
        this.router.navigate(['/setup/order-status'], {queryParams:
            {
                page: this.page,
                size: this.itemsPerPage,
                search: this.currentSearch,
                code : this.orderStatusSearch.code,
                name : this.orderStatusSearch.name,
                description : this.orderStatusSearch.description,
                companyId :this.currentAccount.companyId,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.currentSearch = '';
        this.router.navigate(['/setup/order-status', {
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
        this.router.navigate(['/setup/order-status', {
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
        this.registerChangeInOrderStatuses();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: OrderStatus) {
        return item.id;
    }
    registerChangeInOrderStatuses() {
        this.eventSubscriber = this.eventManager.subscribe('orderStatusListModification', (response) => this.loadAll());
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
        this.orderStatuses = data;
    }
    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    public deleteItem(id:number){
        this.orderStatusPopupService
            .open(OrderStatusDeleteDialogComponent as Component, id);
    }
}
