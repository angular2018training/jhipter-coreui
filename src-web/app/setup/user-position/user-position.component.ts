import { Component, OnInit, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import {NgForm} from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { UserPosition } from './user-position.model';
import { UserPositionPopupService } from './user-position-popup.service';
import { UserPositionService } from './user-position.service';
import { UserPositionDeleteDialogComponent } from './user-position-delete-dialog.component';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {UserPositionSearch} from './user-position.search.model';

import {Company} from '../company/company.model';
import {CompanyService} from "../company/company.service";

import {PostOffice} from '../postOffice/postOffice.model';
import {PostOfficeService} from "../postOffice/postOffice.service";

import {Position} from '../position/position.model';
import {PositionService} from "../position/position.service";

import {UserGroup} from '../userGroup/userGroup.model';
import {UserGroupService} from "../userGroup/userGroup.service";

import {UserExtraInfo} from '../userExtraInfo/userExtraInfo.model';
import {UserExtraInfoService} from "../userExtraInfo/userExtraInfo.service";
@Component({
    selector: 'jhi-user-position',
    templateUrl: './user-position.component.html'
})
export class UserPositionComponent implements OnInit, OnDestroy {

currentAccount: any;
    userPositions: UserPosition[];
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
    userPositionSearch : UserPositionSearch;

    companies : Company[];

    postOffices : PostOffice[];

    positions : Position[];

    userGroups : UserGroup[];

    userExtraInfos : UserExtraInfo[];
    @ViewChild(NgForm) searchForm: NgForm;

    constructor(
        private userPositionService: UserPositionService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager,
        private companyService: CompanyService,
        private postOfficeService: PostOfficeService,
        private positionService: PositionService,
        private userGroupService: UserGroupService,
        private userExtraInfoService: UserExtraInfoService,

        private userPositionPopupService: UserPositionPopupService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;

        });
        this.companies =[];
        this.postOffices =[];
        this.positions =[];
        this.userGroups =[];
        this.userExtraInfos =[];

        this.userPositionSearch = new UserPositionSearch();

        this.userPositionSearch.companyId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['companyId'] ?
                         this.activatedRoute.snapshot.params['companyId'] : '';
        this.userPositionSearch.postOfficeId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['postOfficeId'] ?
                         this.activatedRoute.snapshot.params['postOfficeId'] : '';
        this.userPositionSearch.positionId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['positionId'] ?
                         this.activatedRoute.snapshot.params['positionId'] : '';
        this.userPositionSearch.userGroupId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['userGroupId'] ?
                         this.activatedRoute.snapshot.params['userGroupId'] : '';
        this.userPositionSearch.userExtraInfoId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['userExtraInfoId'] ?
                         this.activatedRoute.snapshot.params['userExtraInfoId'] : '';
    }

    loadAll() {
         var obj = {
         page: this.page -1,
         size: this.itemsPerPage,
         sort: this.sort(),
           companyId : this.userPositionSearch.companyId,
           postOfficeId : this.userPositionSearch.postOfficeId,
           positionId : this.userPositionSearch.positionId,
           userGroupId : this.userPositionSearch.userGroupId,
           userExtraInfoId : this.userPositionSearch.userExtraInfoId,
         };

        this.userPositionService.searchExample(obj).subscribe(
          (res: HttpResponse<UserPosition[]>) => this.onSuccess(res.body, res.headers),
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
        this.router.navigate(['/setup/user-position'], {queryParams:
            {
                page: this.page,
                size: this.itemsPerPage,
                search: this.currentSearch,
                companyId : this.userPositionSearch.companyId,
                postOfficeId : this.userPositionSearch.postOfficeId,
                positionId : this.userPositionSearch.positionId,
                userGroupId : this.userPositionSearch.userGroupId,
                userExtraInfoId : this.userPositionSearch.userExtraInfoId,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.currentSearch = '';
        this.router.navigate(['/setup/user-position', {
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
        this.router.navigate(['/setup/user-position', {
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
        this.registerChangeInUserPositions();
        this.companyService.query().subscribe(
            (res: HttpResponse<Company[]>) => this.companies = res.body,
            (res: HttpErrorResponse) => this.onError(res.message)
        );

        this.postOfficeService.query().subscribe(
            (res: HttpResponse<PostOffice[]>) => this.postOffices = res.body,
            (res: HttpErrorResponse) => this.onError(res.message)
        );

        this.positionService.query().subscribe(
            (res: HttpResponse<Position[]>) => this.positions = res.body,
            (res: HttpErrorResponse) => this.onError(res.message)
        );

        this.userGroupService.query().subscribe(
            (res: HttpResponse<UserGroup[]>) => this.userGroups = res.body,
            (res: HttpErrorResponse) => this.onError(res.message)
        );

        this.userExtraInfoService.query().subscribe(
            (res: HttpResponse<UserExtraInfo[]>) => this.userExtraInfos = res.body,
            (res: HttpErrorResponse) => this.onError(res.message)
        );


    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: UserPosition) {
        return item.id;
    }
    registerChangeInUserPositions() {
        this.eventSubscriber = this.eventManager.subscribe('userPositionListModification', (response) => this.loadAll());
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
        this.userPositions = data;
    }
    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }

    public deleteItem(id:number){
        this.userPositionPopupService
            .open(UserPositionDeleteDialogComponent as Component, id);
    }
}
