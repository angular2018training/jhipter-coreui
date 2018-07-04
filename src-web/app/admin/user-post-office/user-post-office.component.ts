import { Component, OnInit, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import {NgForm} from '@angular/forms';

import {AlertService} from '../../shared/alert/alert-service';
import {ITEMS_QUERY_ALL} from '../../shared/';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { UserPostOffice } from '../../shared/model/user-post-office.model';
import { UserPostOfficePopupService } from './user-post-office-popup.service';
import { UserPostOfficeService } from '../../shared/service/user-post-office.service';
import { UserPostOfficeDeleteDialogComponent } from './user-post-office-delete-dialog.component';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {UserPostOfficeSearch} from '../../shared/model/user-post-office.search.model';
    import { Company } from '../../shared/model/company.model';
    import { CompanyService } from '../../shared/service/company.service';

    import { PostOffice } from '../../shared/model/post-office.model';
    import { PostOfficeService } from '../../shared/service/post-office.service';

    import { UserGroup } from '../../shared/model/user-group.model';
    import { UserGroupService } from '../../shared/service/user-group.service';

    import { UserExtraInfo } from '../../shared/model/user-extra-info.model';
    import { UserExtraInfoService } from '../../shared/service/user-extra-info.service';



@Component({
    selector: 'jhi-user-post-office',
    templateUrl: './user-post-office.component.html'
})
export class UserPostOfficeComponent implements OnInit, OnDestroy {

currentAccount: any;
    userPostOffices: UserPostOffice[];
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
    userPostOfficeSearch : UserPostOfficeSearch;

    postOffices : PostOffice[];

    userGroups : UserGroup[];

    userExtraInfos : UserExtraInfo[];
    @ViewChild(NgForm) searchForm: NgForm;

    constructor(
        private userPostOfficeService: UserPostOfficeService,
        private parseLinks: JhiParseLinks,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private alertService :AlertService,
        private router: Router,
        private eventManager: JhiEventManager,
        private postOfficeService: PostOfficeService,
        private userExtraInfoService: UserExtraInfoService,

        private userPostOfficePopupService: UserPostOfficePopupService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;

        });
        this.postOffices =[];
        this.userExtraInfos =[];

        this.userPostOfficeSearch = new UserPostOfficeSearch();

        this.userPostOfficeSearch.postOfficeId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['postOfficeId'] ?
                         this.activatedRoute.snapshot.params['postOfficeId'] : '';
        this.userPostOfficeSearch.userExtraInfoParentId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['userExtraInfoParentId'] ?
                         this.activatedRoute.snapshot.params['userExtraInfoParentId'] : '';
    }

    loadAll() {
         var obj = {
         page: this.page -1,
         size: this.itemsPerPage,
         sort: this.sort(),
         companyId :this.currentAccount.companyId,
         postOfficeId :this.userPostOfficeSearch.postOfficeId,
         userExtraInfoParentId :this.userPostOfficeSearch.userExtraInfoParentId,
         };

        this.userPostOfficeService.searchExample(obj).subscribe(
          (res: HttpResponse<UserPostOffice[]>) => this.onSuccess(res.body, res.headers),
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
        this.router.navigate(['/setup/user-post-office'], {queryParams:
            {
                page: this.page,
                size: this.itemsPerPage,
                search: this.currentSearch,
                companyId :this.currentAccount.companyId,
                postOfficeId :this.userPostOfficeSearch.postOfficeId,
                userExtraInfoParentId :this.userPostOfficeSearch.userExtraInfoParentId,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.currentSearch = '';
        this.router.navigate(['/setup/user-post-office', {
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
        this.router.navigate(['/setup/user-post-office', {
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
            this.postOfficeService.query({"companyId.equals": this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL }).subscribe(
                (res: HttpResponse<PostOffice[]>) => this.postOffices = res.body,
                (res: HttpErrorResponse) => this.onError(res.message)
            );
            this.userExtraInfoService.query({"companyId.equals": this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL }).subscribe(
                (res: HttpResponse<UserExtraInfo[]>) => this.userExtraInfos = res.body,
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        });
        this.registerChangeInUserPostOffices();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: UserPostOffice) {
        return item.id;
    }
    registerChangeInUserPostOffices() {
        this.eventSubscriber = this.eventManager.subscribe('userPostOfficeListModification', (response) => this.loadAll());
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
        this.userPostOffices = data;
    }
    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    public deleteItem(id:number){
        this.userPostOfficePopupService
            .open(UserPostOfficeDeleteDialogComponent as Component, id);
    }
}
