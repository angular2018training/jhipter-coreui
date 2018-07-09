import { Component, OnInit, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import {NgForm} from '@angular/forms';

import {AlertService} from '../../shared/alert/alert-service';
import {ITEMS_QUERY_ALL} from '../../shared/';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { OrderUserRouteType } from './order-user-route-type.model';
import { OrderUserRouteTypePopupService } from './order-user-route-type-popup.service';
import { OrderUserRouteTypeService } from './order-user-route-type.service';
import { OrderUserRouteTypeDeleteDialogComponent } from './order-user-route-type-delete-dialog.component';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {OrderUserRouteTypeSearch} from './order-user-route-type.search.model';
    import { Company, CompanyService } from '../company';


@Component({
    selector: 'jhi-order-user-route-type',
    templateUrl: './order-user-route-type.component.html'
})
export class OrderUserRouteTypeComponent implements OnInit, OnDestroy {

currentAccount: any;
    orderUserRouteTypes: OrderUserRouteType[];
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
    orderUserRouteTypeSearch : OrderUserRouteTypeSearch;
    @ViewChild(NgForm) searchForm: NgForm;

    constructor(
        private orderUserRouteTypeService: OrderUserRouteTypeService,
        private parseLinks: JhiParseLinks,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private alertService :AlertService,
        private router: Router,
        private eventManager: JhiEventManager,

        private orderUserRouteTypePopupService: OrderUserRouteTypePopupService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;

        });

        this.orderUserRouteTypeSearch = new OrderUserRouteTypeSearch();

        this.orderUserRouteTypeSearch.code = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['code'] ?
                        this.activatedRoute.snapshot.params[' code'] : '';
        this.orderUserRouteTypeSearch.name = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['name'] ?
                        this.activatedRoute.snapshot.params[' name'] : '';
        this.orderUserRouteTypeSearch.description = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['description'] ?
                        this.activatedRoute.snapshot.params[' description'] : '';
    }

    loadAll() {
         var obj = {
         page: this.page -1,
         size: this.itemsPerPage,
         sort: this.sort(),
         code : this.orderUserRouteTypeSearch.code,
         name : this.orderUserRouteTypeSearch.name,
         description : this.orderUserRouteTypeSearch.description,
         companyId :this.currentAccount.companyId,
         };

        this.orderUserRouteTypeService.searchExample(obj).subscribe(
          (res: HttpResponse<OrderUserRouteType[]>) => this.onSuccess(res.body, res.headers),
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
        this.router.navigate(['/setup/order-user-route-type'], {queryParams:
            {
                page: this.page,
                size: this.itemsPerPage,
                search: this.currentSearch,
                code : this.orderUserRouteTypeSearch.code,
                name : this.orderUserRouteTypeSearch.name,
                description : this.orderUserRouteTypeSearch.description,
                companyId :this.currentAccount.companyId,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.currentSearch = '';
        this.router.navigate(['/setup/order-user-route-type', {
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
        this.router.navigate(['/setup/order-user-route-type', {
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
        this.registerChangeInOrderUserRouteTypes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: OrderUserRouteType) {
        return item.id;
    }
    registerChangeInOrderUserRouteTypes() {
        this.eventSubscriber = this.eventManager.subscribe('orderUserRouteTypeListModification', (response) => this.loadAll());
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
        this.orderUserRouteTypes = data;
    }
    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    public deleteItem(id:number){
        this.orderUserRouteTypePopupService
            .open(OrderUserRouteTypeDeleteDialogComponent as Component, id);
    }
}
