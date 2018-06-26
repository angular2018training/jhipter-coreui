import { Component, OnInit, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import {NgForm} from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { UserExtraInfo } from './user-extra-info.model';
import { UserExtraInfoPopupService } from './user-extra-info-popup.service';
import { UserExtraInfoService } from './user-extra-info.service';
import { UserExtraInfoDeleteDialogComponent } from './user-extra-info-delete-dialog.component';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {UserExtraInfoSearch} from './user-extra-info.search.model';

import {UserPosition} from '../user-position/user-position.model';
import {UserPositionService} from "../user-position/user-position.service";

import {Company} from '../company/company.model';
import {CompanyService} from "../company/company.service";
@Component({
    selector: 'jhi-user-extra-info',
    templateUrl: './user-extra-info.component.html'
})
export class UserExtraInfoComponent implements OnInit, OnDestroy {

currentAccount: any;
    userExtraInfos: UserExtraInfo[];
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
    userExtraInfoSearch : UserExtraInfoSearch;

    userPositions : UserPosition[];

    companies : Company[];
    @ViewChild(NgForm) searchForm: NgForm;

    constructor(
        private userExtraInfoService: UserExtraInfoService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private dataUtils: JhiDataUtils,
        private router: Router,
        private eventManager: JhiEventManager,
        private userPositionService: UserPositionService,
        private companyService: CompanyService,

        private userExtraInfoPopupService: UserExtraInfoPopupService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;

        });
        this.userPositions =[];
        this.companies =[];

        this.userExtraInfoSearch = new UserExtraInfoSearch();

        this.userExtraInfoSearch.email = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['email'] ?
                        this.activatedRoute.snapshot.params[' email'] : '';
        this.userExtraInfoSearch.phone = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['phone'] ?
                        this.activatedRoute.snapshot.params[' phone'] : '';
        this.userExtraInfoSearch.address = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['address'] ?
                        this.activatedRoute.snapshot.params[' address'] : '';
        this.userExtraInfoSearch.validDate = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['validDate'] ?
                        this.activatedRoute.snapshot.params[' validDate'] : '';
        this.userExtraInfoSearch.lastLoginDate = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['lastLoginDate'] ?
                        this.activatedRoute.snapshot.params[' lastLoginDate'] : '';
        this.userExtraInfoSearch.contractFile = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['contractFile'] ?
                        this.activatedRoute.snapshot.params[' contractFile'] : '';
        this.userExtraInfoSearch.contractExpirationDate = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['contractExpirationDate'] ?
                        this.activatedRoute.snapshot.params[' contractExpirationDate'] : '';
        this.userExtraInfoSearch.companyId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['companyId'] ?
                         this.activatedRoute.snapshot.params['companyId'] : '';
    }

    loadAll() {
         var obj = {
         page: this.page -1,
         size: this.itemsPerPage,
         sort: this.sort(),
         email : this.userExtraInfoSearch.email,
         phone : this.userExtraInfoSearch.phone,
         address : this.userExtraInfoSearch.address,
         validDate : this.userExtraInfoSearch.validDate,
         lastLoginDate : this.userExtraInfoSearch.lastLoginDate,
         contractFile : this.userExtraInfoSearch.contractFile,
         contractExpirationDate : this.userExtraInfoSearch.contractExpirationDate,
           companyId : this.userExtraInfoSearch.companyId,
         };

        this.userExtraInfoService.searchExample(obj).subscribe(
          (res: HttpResponse<UserExtraInfo[]>) => this.onSuccess(res.body, res.headers),
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
        this.router.navigate(['/setup/user-extra-info'], {queryParams:
            {
                page: this.page,
                size: this.itemsPerPage,
                search: this.currentSearch,
                email : this.userExtraInfoSearch.email,
                phone : this.userExtraInfoSearch.phone,
                address : this.userExtraInfoSearch.address,
                validDate : this.userExtraInfoSearch.validDate,
                lastLoginDate : this.userExtraInfoSearch.lastLoginDate,
                contractFile : this.userExtraInfoSearch.contractFile,
                contractExpirationDate : this.userExtraInfoSearch.contractExpirationDate,
                companyId : this.userExtraInfoSearch.companyId,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.currentSearch = '';
        this.router.navigate(['/setup/user-extra-info', {
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
        this.router.navigate(['/setup/user-extra-info', {
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
        this.registerChangeInUserExtraInfos();
        this.userPositionService.query().subscribe(
            (res: HttpResponse<UserPosition[]>) => this.userPositions = res.body,
            (res: HttpErrorResponse) => this.onError(res.message)
        );

        this.companyService.query().subscribe(
            (res: HttpResponse<Company[]>) => this.companies = res.body,
            (res: HttpErrorResponse) => this.onError(res.message)
        );


    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: UserExtraInfo) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    registerChangeInUserExtraInfos() {
        this.eventSubscriber = this.eventManager.subscribe('userExtraInfoListModification', (response) => this.loadAll());
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
        this.userExtraInfos = data;
    }
    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }

    public deleteItem(id:number){
        this.userExtraInfoPopupService
            .open(UserExtraInfoDeleteDialogComponent as Component, id);
    }
}
