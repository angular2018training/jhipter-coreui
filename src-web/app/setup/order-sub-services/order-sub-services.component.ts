import { Component, OnInit, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import {NgForm} from '@angular/forms';

import {AlertService} from '../../shared/alert/alert-service';
import {ITEMS_QUERY_ALL} from '../../shared/';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { OrderSubServices } from '../../shared/model/order-sub-services.model';
import { OrderSubServicesPopupService } from './order-sub-services-popup.service';
import { OrderSubServicesService } from '../../shared/service/order-sub-services.service';
import { OrderSubServicesDeleteDialogComponent } from './order-sub-services-delete-dialog.component';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {OrderSubServicesSearch} from '../../shared/model/order-sub-services.search.model';
    import { Company } from '../../shared/model/company.model';
    import { CompanyService } from '../../shared/service/company.service';



@Component({
    selector: 'jhi-order-sub-services',
    templateUrl: './order-sub-services.component.html'
})
export class OrderSubServicesComponent implements OnInit, OnDestroy {

currentAccount: any;
    orderSubServices: OrderSubServices[];
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
    orderSubServicesSearch : OrderSubServicesSearch;
    @ViewChild(NgForm) searchForm: NgForm;

    constructor(
        private orderSubServicesService: OrderSubServicesService,
        private parseLinks: JhiParseLinks,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private alertService :AlertService,
        private router: Router,
        private eventManager: JhiEventManager,

        private orderSubServicesPopupService: OrderSubServicesPopupService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;

        });

        this.orderSubServicesSearch = new OrderSubServicesSearch();

        this.orderSubServicesSearch.code = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['code'] ?
                        this.activatedRoute.snapshot.params[' code'] : '';
        this.orderSubServicesSearch.name = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['name'] ?
                        this.activatedRoute.snapshot.params[' name'] : '';
        this.orderSubServicesSearch.isActive = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['isActive'] ?
                        this.activatedRoute.snapshot.params[' isActive'] : '';
        this.orderSubServicesSearch.description = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['description'] ?
                        this.activatedRoute.snapshot.params[' description'] : '';
    }

    loadAll() {
         var obj = {
         page: this.page -1,
         size: this.itemsPerPage,
         sort: this.sort(),
         code : this.orderSubServicesSearch.code,
         name : this.orderSubServicesSearch.name,
         isActive : this.orderSubServicesSearch.isActive,
         description : this.orderSubServicesSearch.description,
         companyId :this.currentAccount.companyId,
         };

        this.orderSubServicesService.searchExample(obj).subscribe(
          (res: HttpResponse<OrderSubServices[]>) => this.onSuccess(res.body, res.headers),
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
        this.router.navigate(['/setup/order-sub-services'], {queryParams:
            {
                page: this.page,
                size: this.itemsPerPage,
                search: this.currentSearch,
                code : this.orderSubServicesSearch.code,
                name : this.orderSubServicesSearch.name,
                isActive : this.orderSubServicesSearch.isActive,
                description : this.orderSubServicesSearch.description,
                companyId :this.currentAccount.companyId,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.currentSearch = '';
        this.router.navigate(['/setup/order-sub-services', {
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
        this.router.navigate(['/setup/order-sub-services', {
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
        this.registerChangeInOrderSubServices();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: OrderSubServices) {
        return item.id;
    }
    registerChangeInOrderSubServices() {
        this.eventSubscriber = this.eventManager.subscribe('orderSubServicesListModification', (response) => this.loadAll());
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
        this.orderSubServices = data;
    }
    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    public deleteItem(id:number){
        this.orderSubServicesPopupService
            .open(OrderSubServicesDeleteDialogComponent as Component, id);
    }
}
