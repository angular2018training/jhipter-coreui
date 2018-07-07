import { Component, OnInit, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import {NgForm} from '@angular/forms';

import {AlertService} from '../../shared/alert/alert-service';
import {ITEMS_QUERY_ALL} from '../../shared/';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { Weight } from '../../shared/model/weight.model';
import { WeightPopupService } from './weight-popup.service';
import { WeightService } from '../../shared/service/weight.service';
import { WeightDeleteDialogComponent } from './weight-delete-dialog.component';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {WeightSearch} from '../../shared/model/weight.search.model';


@Component({
    selector: 'jhi-weight',
    templateUrl: './weight.component.html'
})
export class WeightComponent implements OnInit, OnDestroy {

currentAccount: any;
    weights: Weight[];
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
    weightSearch : WeightSearch;
    @ViewChild(NgForm) searchForm: NgForm;

    constructor(
        private weightService: WeightService,
        private parseLinks: JhiParseLinks,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private alertService :AlertService,
        private router: Router,
        private eventManager: JhiEventManager,

        private weightPopupService: WeightPopupService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;

        });

        this.weightSearch = new WeightSearch();

        this.weightSearch.minAmount = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['minAmount'] ?
                        this.activatedRoute.snapshot.params[' minAmount'] : '';
        this.weightSearch.maxAmount = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['maxAmount'] ?
                        this.activatedRoute.snapshot.params[' maxAmount'] : '';
        this.weightSearch.name = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['name'] ?
                        this.activatedRoute.snapshot.params[' name'] : '';
        this.weightSearch.description = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['description'] ?
                        this.activatedRoute.snapshot.params[' description'] : '';
    }

    loadAll() {
         var obj = {
         page: this.page -1,
         size: this.itemsPerPage,
         sort: this.sort(),
         minAmount : this.weightSearch.minAmount,
         maxAmount : this.weightSearch.maxAmount,
         name : this.weightSearch.name,
         description : this.weightSearch.description,
         };

        this.weightService.searchExample(obj).subscribe(
          (res: HttpResponse<Weight[]>) => this.onSuccess(res.body, res.headers),
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
        this.router.navigate(['/setup/weight'], {queryParams:
            {
                page: this.page,
                size: this.itemsPerPage,
                search: this.currentSearch,
                minAmount : this.weightSearch.minAmount,
                maxAmount : this.weightSearch.maxAmount,
                name : this.weightSearch.name,
                description : this.weightSearch.description,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.currentSearch = '';
        this.router.navigate(['/setup/weight', {
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
        this.router.navigate(['/setup/weight', {
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
        this.registerChangeInWeights();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Weight) {
        return item.id;
    }
    registerChangeInWeights() {
        this.eventSubscriber = this.eventManager.subscribe('weightListModification', (response) => this.loadAll());
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
        this.weights = data;
    }
    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    public deleteItem(id:number){
        this.weightPopupService
            .open(WeightDeleteDialogComponent as Component, id);
    }
}
