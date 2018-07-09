import { Component, OnInit, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import {NgForm} from '@angular/forms';

import {AlertService} from '../../shared/alert/alert-service';
import {ITEMS_QUERY_ALL} from '../../shared/';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { Warehouse } from './warehouse.model';
import { WarehousePopupService } from './warehouse-popup.service';
import { WarehouseService } from './warehouse.service';
import { WarehouseDeleteDialogComponent } from './warehouse-delete-dialog.component';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {WarehouseSearch} from './warehouse.search.model';
    import { Company, CompanyService } from '../company';
    import { Province, ProvinceService } from '../province';
    import { District, DistrictService } from '../district';
    import { Ward, WardService } from '../ward';


@Component({
    selector: 'jhi-warehouse',
    templateUrl: './warehouse.component.html'
})
export class WarehouseComponent implements OnInit, OnDestroy {

currentAccount: any;
    warehouses: Warehouse[];
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
    warehouseSearch : WarehouseSearch;

    provinces : Province[];

    districts : District[];

    wards : Ward[];
    @ViewChild(NgForm) searchForm: NgForm;

    constructor(
        private warehouseService: WarehouseService,
        private parseLinks: JhiParseLinks,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private alertService :AlertService,
        private router: Router,
        private eventManager: JhiEventManager,
        private provinceService: ProvinceService,
        private districtService: DistrictService,
        private wardService: WardService,

        private warehousePopupService: WarehousePopupService
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
        this.wards =[];

        this.warehouseSearch = new WarehouseSearch();

        this.warehouseSearch.name = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['name'] ?
                        this.activatedRoute.snapshot.params[' name'] : '';
        this.warehouseSearch.contactName = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['contactName'] ?
                        this.activatedRoute.snapshot.params[' contactName'] : '';
        this.warehouseSearch.contactPhone = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['contactPhone'] ?
                        this.activatedRoute.snapshot.params[' contactPhone'] : '';
        this.warehouseSearch.address = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['address'] ?
                        this.activatedRoute.snapshot.params[' address'] : '';
        this.warehouseSearch.testStr = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['testStr'] ?
                        this.activatedRoute.snapshot.params[' testStr'] : '';
        this.warehouseSearch.createDate = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['createDate'] ?
                        this.activatedRoute.snapshot.params[' createDate'] : '';
        this.warehouseSearch.provinceId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['provinceId'] ?
                         this.activatedRoute.snapshot.params['provinceId'] : '';
        this.warehouseSearch.districtId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['districtId'] ?
                         this.activatedRoute.snapshot.params['districtId'] : '';
        this.warehouseSearch.wardId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['wardId'] ?
                         this.activatedRoute.snapshot.params['wardId'] : '';
    }

    loadAll() {
         var obj = {
         page: this.page -1,
         size: this.itemsPerPage,
         sort: this.sort(),
         name : this.warehouseSearch.name,
         contactName : this.warehouseSearch.contactName,
         contactPhone : this.warehouseSearch.contactPhone,
         address : this.warehouseSearch.address,
         testStr : this.warehouseSearch.testStr,
         createDate : this.warehouseSearch.createDate,
         companyId :this.currentAccount.companyId,
         provinceId :this.warehouseSearch.provinceId,
         districtId :this.warehouseSearch.districtId,
         wardId :this.warehouseSearch.wardId,
         };

        this.warehouseService.searchExample(obj).subscribe(
          (res: HttpResponse<Warehouse[]>) => this.onSuccess(res.body, res.headers),
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
        this.router.navigate(['/setup/warehouse'], {queryParams:
            {
                page: this.page,
                size: this.itemsPerPage,
                search: this.currentSearch,
                name : this.warehouseSearch.name,
                contactName : this.warehouseSearch.contactName,
                contactPhone : this.warehouseSearch.contactPhone,
                address : this.warehouseSearch.address,
                testStr : this.warehouseSearch.testStr,
                createDate : this.warehouseSearch.createDate,
                companyId :this.currentAccount.companyId,
                provinceId :this.warehouseSearch.provinceId,
                districtId :this.warehouseSearch.districtId,
                wardId :this.warehouseSearch.wardId,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.currentSearch = '';
        this.router.navigate(['/setup/warehouse', {
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
        this.router.navigate(['/setup/warehouse', {
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
            this.wardService.query({"companyId.equals": this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL }).subscribe(
                (res: HttpResponse<Ward[]>) => this.wards = res.body,
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        });
        this.registerChangeInWarehouses();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Warehouse) {
        return item.id;
    }
    registerChangeInWarehouses() {
        this.eventSubscriber = this.eventManager.subscribe('warehouseListModification', (response) => this.loadAll());
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
        this.warehouses = data;
    }
    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    public deleteItem(id:number){
        this.warehousePopupService
            .open(WarehouseDeleteDialogComponent as Component, id);
    }
}
