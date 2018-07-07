import { Component, OnInit, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import {NgForm} from '@angular/forms';

import {AlertService} from '../../shared/alert/alert-service';
import {ITEMS_QUERY_ALL} from '../../shared/';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, } from 'ng-jhipster';

import { PostOfficePopupService } from './post-office-popup.service';
import { PostOfficeDeleteDialogComponent } from './post-office-delete-dialog.component';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {PostOffice} from "../../shared/model/post-office.model";
import {PostOfficeSearch} from "../../shared/model/post-office.search.model";
import {Province} from "../province/province.model";
import {District} from "../../shared/model/district.model";
import {PostOfficeService} from "../../shared/service/post-office.service";
import {ProvinceService} from "../../shared/service/province.service";
import {DistrictService} from "../../shared/service/district.service";


@Component({
    selector: 'jhi-post-office',
    templateUrl: './post-office.component.html'
})
export class PostOfficeComponent implements OnInit, OnDestroy {

currentAccount: any;
    postOffices: PostOffice[];
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
    postOfficeSearch : PostOfficeSearch;

    provinces : Province[];

    districts : District[];
    @ViewChild(NgForm) searchForm: NgForm;

    constructor(
        private postOfficeService: PostOfficeService,
        private parseLinks: JhiParseLinks,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private alertService :AlertService,
        private router: Router,
        private eventManager: JhiEventManager,
        private provinceService: ProvinceService,
        private districtService: DistrictService,

        private postOfficePopupService: PostOfficePopupService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;

        });
        this.provinces =[];
        this.districts =[];

        this.postOfficeSearch = new PostOfficeSearch();

        this.postOfficeSearch.code = this.activatedRoute.snapshot && this.activatedRoute.snapshot.queryParams['code'] ?
                        this.activatedRoute.snapshot.queryParams[' code'] : '';
        this.postOfficeSearch.name = this.activatedRoute.snapshot && this.activatedRoute.snapshot.queryParams['name'] ?
                        this.activatedRoute.snapshot.queryParams[' name'] : '';
        this.postOfficeSearch.address = this.activatedRoute.snapshot && this.activatedRoute.snapshot.queryParams['address'] ?
                        this.activatedRoute.snapshot.queryParams[' address'] : '';
        this.postOfficeSearch.sortOrder = this.activatedRoute.snapshot && this.activatedRoute.snapshot.queryParams['sortOrder'] ?
                        this.activatedRoute.snapshot.queryParams[' sortOrder'] : '';
        this.postOfficeSearch.phone = this.activatedRoute.snapshot && this.activatedRoute.snapshot.queryParams['phone'] ?
                        this.activatedRoute.snapshot.queryParams[' phone'] : '';
        this.postOfficeSearch.latitude = this.activatedRoute.snapshot && this.activatedRoute.snapshot.queryParams['latitude'] ?
                        this.activatedRoute.snapshot.queryParams[' latitude'] : '';
        this.postOfficeSearch.longitude = this.activatedRoute.snapshot && this.activatedRoute.snapshot.queryParams['longitude'] ?
                        this.activatedRoute.snapshot.queryParams[' longitude'] : '';
        this.postOfficeSearch.description = this.activatedRoute.snapshot && this.activatedRoute.snapshot.queryParams['description'] ?
                        this.activatedRoute.snapshot.queryParams[' description'] : '';
        this.postOfficeSearch.provinceId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.queryParams['provinceId'] ?
                         this.activatedRoute.snapshot.queryParams['provinceId'] : '';
        this.postOfficeSearch.districtId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.queryParams['districtId'] ?
                         this.activatedRoute.snapshot.queryParams['districtId'] : '';
    }

    loadAll() {
         var obj = {
         page: this.page -1,
         size: this.itemsPerPage,
         sort: this.sort(),
         code : this.postOfficeSearch.code,
         name : this.postOfficeSearch.name,
         address : this.postOfficeSearch.address,
         sortOrder : this.postOfficeSearch.sortOrder,
         phone : this.postOfficeSearch.phone,
         latitude : this.postOfficeSearch.latitude,
         longitude : this.postOfficeSearch.longitude,
         description : this.postOfficeSearch.description,
         companyId :this.currentAccount.companyId,
         provinceId :this.postOfficeSearch.provinceId,
         districtId :this.postOfficeSearch.districtId,
         };

        this.postOfficeService.searchExample(obj).subscribe(
          (res: HttpResponse<PostOffice[]>) => this.onSuccess(res.body, res.headers),
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
        this.router.navigate(['/setup/post-office'], {queryParams:
            {
                page: this.page,
                size: this.itemsPerPage,
                search: this.currentSearch,
                code : this.postOfficeSearch.code,
                name : this.postOfficeSearch.name,
                address : this.postOfficeSearch.address,
                sortOrder : this.postOfficeSearch.sortOrder,
                phone : this.postOfficeSearch.phone,
                latitude : this.postOfficeSearch.latitude,
                longitude : this.postOfficeSearch.longitude,
                description : this.postOfficeSearch.description,
                companyId :this.currentAccount.companyId,
                provinceId :this.postOfficeSearch.provinceId,
                districtId :this.postOfficeSearch.districtId,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.currentSearch = '';
        this.router.navigate(['/setup/post-office', {
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
        this.router.navigate(['/setup/post-office', {
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
            this.provinceService.query({"companyId.equals": this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL }).subscribe(
                (res: HttpResponse<Province[]>) => this.provinces = res.body,
                (res: HttpErrorResponse) => this.onError(res.message)
            );
            this.districtService.query({"companyId.equals": this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL }).subscribe(
                (res: HttpResponse<District[]>) => this.districts = res.body,
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        });
        this.registerChangeInPostOffices();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: PostOffice) {
        return item.id;
    }
    registerChangeInPostOffices() {
        this.eventSubscriber = this.eventManager.subscribe('postOfficeListModification', (response) => this.loadAll());
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
        this.postOffices = data;
    }
    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    public deleteItem(id:number){
        this.postOfficePopupService
            .open(PostOfficeDeleteDialogComponent as Component, id);
    }
}
