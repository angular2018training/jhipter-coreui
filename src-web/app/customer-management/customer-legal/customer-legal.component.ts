import { Component, OnInit, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import {NgForm} from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { CustomerLegal } from './customer-legal.model';
import { CustomerLegalPopupService } from './customer-legal-popup.service';
import { CustomerLegalService } from './customer-legal.service';
import { CustomerLegalDeleteDialogComponent } from './customer-legal-delete-dialog.component';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {CustomerLegalSearch} from './customer-legal.search.model';

import {Company} from '../../setup/company/company.model';
import {CompanyService} from "../../setup/company/company.service";

import {Province} from '../../setup/province/province.model';
import {ProvinceService} from "../../setup/province/province.service";

import {District} from '../../setup/district/district.model';
import {DistrictService} from "../../setup/district/district.service";

import {FileUpload} from '../../setup/file-upload/file-upload.model';
import {FileUploadService} from "../../setup/file-upload/file-upload.service";
@Component({
    selector: 'jhi-customer-legal',
    templateUrl: './customer-legal.component.html'
})
export class CustomerLegalComponent implements OnInit, OnDestroy {

currentAccount: any;
    customerLegals: CustomerLegal[];
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
    customerLegalSearch : CustomerLegalSearch;

    companies : Company[];

    provinces : Province[];

    districts : District[];

    fileUploads : FileUpload[];
    @ViewChild(NgForm) searchForm: NgForm;

    constructor(
        private customerLegalService: CustomerLegalService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager,
        private companyService: CompanyService,
        private provinceService: ProvinceService,
        private districtService: DistrictService,
        private fileUploadService: FileUploadService,

        private customerLegalPopupService: CustomerLegalPopupService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;

        });
        this.companies =[];
        this.provinces =[];
        this.districts =[];
        this.fileUploads =[];

        this.customerLegalSearch = new CustomerLegalSearch();

        this.customerLegalSearch.contractCustomerName = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['contractCustomerName'] ?
                        this.activatedRoute.snapshot.params[' contractCustomerName'] : '';
        this.customerLegalSearch.contractAddress = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['contractAddress'] ?
                        this.activatedRoute.snapshot.params[' contractAddress'] : '';
        this.customerLegalSearch.contractContactName = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['contractContactName'] ?
                        this.activatedRoute.snapshot.params[' contractContactName'] : '';
        this.customerLegalSearch.contractContactPhone = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['contractContactPhone'] ?
                        this.activatedRoute.snapshot.params[' contractContactPhone'] : '';
        this.customerLegalSearch.taxCode = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['taxCode'] ?
                        this.activatedRoute.snapshot.params[' taxCode'] : '';
        this.customerLegalSearch.contractExpirationDate = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['contractExpirationDate'] ?
                        this.activatedRoute.snapshot.params[' contractExpirationDate'] : '';
        this.customerLegalSearch.companyId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['companyId'] ?
                         this.activatedRoute.snapshot.params['companyId'] : '';
        this.customerLegalSearch.provinceId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['provinceId'] ?
                         this.activatedRoute.snapshot.params['provinceId'] : '';
        this.customerLegalSearch.districtId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['districtId'] ?
                         this.activatedRoute.snapshot.params['districtId'] : '';
    }

    loadAll() {
         var obj = {
         page: this.page -1,
         size: this.itemsPerPage,
         sort: this.sort(),
         contractCustomerName : this.customerLegalSearch.contractCustomerName,
         contractAddress : this.customerLegalSearch.contractAddress,
         contractContactName : this.customerLegalSearch.contractContactName,
         contractContactPhone : this.customerLegalSearch.contractContactPhone,
         taxCode : this.customerLegalSearch.taxCode,
         contractExpirationDate : this.customerLegalSearch.contractExpirationDate,
           companyId : this.customerLegalSearch.companyId,
           provinceId : this.customerLegalSearch.provinceId,
           districtId : this.customerLegalSearch.districtId,
         };

        this.customerLegalService.searchExample(obj).subscribe(
          (res: HttpResponse<CustomerLegal[]>) => this.onSuccess(res.body, res.headers),
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
        this.router.navigate(['/customer-management/customer-legal'], {queryParams:
            {
                page: this.page,
                size: this.itemsPerPage,
                search: this.currentSearch,
                contractCustomerName : this.customerLegalSearch.contractCustomerName,
                contractAddress : this.customerLegalSearch.contractAddress,
                contractContactName : this.customerLegalSearch.contractContactName,
                contractContactPhone : this.customerLegalSearch.contractContactPhone,
                taxCode : this.customerLegalSearch.taxCode,
                contractExpirationDate : this.customerLegalSearch.contractExpirationDate,
                companyId : this.customerLegalSearch.companyId,
                provinceId : this.customerLegalSearch.provinceId,
                districtId : this.customerLegalSearch.districtId,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.currentSearch = '';
        this.router.navigate(['/customer-management/customer-legal', {
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
        this.router.navigate(['/customer-management/customer-legal', {
            search: this.currentSearch,
            page: this.page,
            sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
        }]);
        this.loadAll();
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInCustomerLegals();
        this.companyService.query().subscribe(
            (res: HttpResponse<Company[]>) => this.companies = res.body,
            (res: HttpErrorResponse) => this.onError(res.message)
        );

        this.provinceService.query().subscribe(
            (res: HttpResponse<Province[]>) => this.provinces = res.body,
            (res: HttpErrorResponse) => this.onError(res.message)
        );

        this.districtService.query().subscribe(
            (res: HttpResponse<District[]>) => this.districts = res.body,
            (res: HttpErrorResponse) => this.onError(res.message)
        );



    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: CustomerLegal) {
        return item.id;
    }
    registerChangeInCustomerLegals() {
        this.eventSubscriber = this.eventManager.subscribe('customerLegalListModification', (response) => this.loadAll());
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
        this.customerLegals = data;
    }
    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }

    public deleteItem(id:number){
        this.customerLegalPopupService
            .open(CustomerLegalDeleteDialogComponent as Component, id);
    }
}
