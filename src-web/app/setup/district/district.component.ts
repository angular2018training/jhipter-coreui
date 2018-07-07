import { Component, OnInit, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import {NgForm} from '@angular/forms';

import {AlertService} from '../../shared/alert/alert-service';
import {ITEMS_QUERY_ALL} from '../../shared/';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { District } from '../../shared/model/district.model';
import { DistrictPopupService } from './district-popup.service';
import { DistrictService } from '../../shared/service/district.service';
import { DistrictDeleteDialogComponent } from './district-delete-dialog.component';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {DistrictSearch} from '../../shared/model/district.search.model';
    import { Company } from '../../shared/model/company.model';
    import { CompanyService } from '../../shared/service/company.service';

    import { Province } from '../../shared/model/province.model';
    import { ProvinceService } from '../../shared/service/province.service';

    import { DistrictType } from '../../shared/model/district-type.model';
    import { DistrictTypeService } from '../../shared/service/district-type.service';



@Component({
    selector: 'jhi-district',
    templateUrl: './district.component.html'
})
export class DistrictComponent implements OnInit, OnDestroy {

currentAccount: any;
    districts: District[];
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
    districtSearch : DistrictSearch;

    provinces : Province[];

    districtTypes : DistrictType[];
    @ViewChild(NgForm) searchForm: NgForm;

    constructor(
        private districtService: DistrictService,
        private parseLinks: JhiParseLinks,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private alertService :AlertService,
        private router: Router,
        private eventManager: JhiEventManager,
        private provinceService: ProvinceService,
        private districtTypeService: DistrictTypeService,

        private districtPopupService: DistrictPopupService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;

        });
        this.provinces =[];
        this.districtTypes =[];

        this.districtSearch = new DistrictSearch();

        this.districtSearch.code = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['code'] ?
                        this.activatedRoute.snapshot.params[' code'] : '';
        this.districtSearch.name = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['name'] ?
                        this.activatedRoute.snapshot.params[' name'] : '';
        this.districtSearch.pickupActive = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['pickupActive'] ?
                        this.activatedRoute.snapshot.params[' pickupActive'] : '';
        this.districtSearch.deliveryActive = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['deliveryActive'] ?
                        this.activatedRoute.snapshot.params[' deliveryActive'] : '';
        this.districtSearch.description = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['description'] ?
                        this.activatedRoute.snapshot.params[' description'] : '';
        this.districtSearch.provinceId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['provinceId'] ?
                         this.activatedRoute.snapshot.params['provinceId'] : '';
        this.districtSearch.districtTypeId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['districtTypeId'] ?
                         this.activatedRoute.snapshot.params['districtTypeId'] : '';
    }

    loadAll() {
         var obj = {
         page: this.page -1,
         size: this.itemsPerPage,
         sort: this.sort(),
         code : this.districtSearch.code,
         name : this.districtSearch.name,
         pickupActive : this.districtSearch.pickupActive,
         deliveryActive : this.districtSearch.deliveryActive,
         description : this.districtSearch.description,
         companyId :this.currentAccount.companyId,
         provinceId :this.districtSearch.provinceId,
         districtTypeId :this.districtSearch.districtTypeId,
         };

        this.districtService.searchExample(obj).subscribe(
          (res: HttpResponse<District[]>) => this.onSuccess(res.body, res.headers),
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
        this.router.navigate(['/setup/district'], {queryParams:
            {
                page: this.page,
                size: this.itemsPerPage,
                search: this.currentSearch,
                code : this.districtSearch.code,
                name : this.districtSearch.name,
                pickupActive : this.districtSearch.pickupActive,
                deliveryActive : this.districtSearch.deliveryActive,
                description : this.districtSearch.description,
                companyId :this.currentAccount.companyId,
                provinceId :this.districtSearch.provinceId,
                districtTypeId :this.districtSearch.districtTypeId,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.currentSearch = '';
        this.router.navigate(['/setup/district', {
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
        this.router.navigate(['/setup/district', {
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
            this.districtTypeService.query({"companyId.equals": this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL }).subscribe(
                (res: HttpResponse<DistrictType[]>) => this.districtTypes = res.body,
                (res: HttpErrorResponse) => this.onError(res.message)
            );
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
        this.districts = data;
    }
    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    public deleteItem(id:number){
        this.districtPopupService
            .open(DistrictDeleteDialogComponent as Component, id);
    }
}
