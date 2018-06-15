import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import {JhiEventManager, JhiAlertService, JhiLanguageService} from 'ng-jhipster';

import { District } from './district.model';
import { DistrictService } from './district.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-district',
    templateUrl: './district.component.html'
})
export class DistrictComponent implements OnInit, OnDestroy {
districts: District[];
    currentAccount: any;
    eventSubscriber: Subscription;
    currentSearch: string;
    public searchCardIsCollapsed = false;
    districtSearch : District;
    constructor(
        private districtService: DistrictService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private activatedRoute: ActivatedRoute,
        private principal: Principal
    ) {
        this.currentSearch = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['search'] ?
            this.activatedRoute.snapshot.params['search'] : '';
        this.districtSearch = new District();
    }

    loadAll() {
        if (this.currentSearch) {
            this.districtService.search({
                query: this.currentSearch,
                }).subscribe(
                    (res: HttpResponse<District[]>) => this.districts = res.body,
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            return;
       }
        this.districtService.query().subscribe(
            (res: HttpResponse<District[]>) => {
                this.districts = res.body;
                this.currentSearch = '';
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    search(query) {
        if (!query) {
            return this.clear();
        }
        this.currentSearch = query;
        this.loadAll();
    }

    clear() {
        this.currentSearch = '';
        this.loadAll();
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInDistricts();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: District) {
        return item.id;
    }
    registerChangeInDistricts() {
        this.eventSubscriber = this.eventManager.subscribe('districtListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
