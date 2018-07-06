import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { UserGroup } from '../../models/user-group.model';
import { UserGroupPopupService } from '../../services/user-group-popup.service';
import { UserGroupService } from '../../services/user-group.service';
import { UserGroupDeleteDialogComponent } from '../../dialogs/user-group-delete-dialog/user-group-delete-dialog.component';
import { ITEMS_PER_PAGE, Principal } from '../../../../shared';
import { UserGroupSearch } from '../../models/user-group.search.model';

import { Company } from '../../../../setup/company/company.model';
import { CompanyService } from '../../../../setup/company/company.service';
@Component({
    selector: 'jhi-user-group',
    templateUrl: './user-group.component.html',
    styleUrls: ['./user-group.component.scss']
})
export class UserGroupComponent implements OnInit, OnDestroy {

    currentAccount: any;
    userGroups: UserGroup[];
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
    userGroupSearch: UserGroupSearch;
    filterQuery = '';
    companies: Company[];
    @ViewChild(NgForm) searchForm: NgForm;

    constructor(
        private userGroupService: UserGroupService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager,
        private companyService: CompanyService,

        private userGroupPopupService: UserGroupPopupService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;

        });
        this.companies = [];

        this.userGroupSearch = new UserGroupSearch();

        this.userGroupSearch.code = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['code'] ?
            this.activatedRoute.snapshot.params[' code'] : '';
        this.userGroupSearch.name = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['name'] ?
            this.activatedRoute.snapshot.params[' name'] : '';
        this.userGroupSearch.description = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['description'] ?
            this.activatedRoute.snapshot.params[' description'] : '';
        this.userGroupSearch.companyId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['companyId'] ?
            this.activatedRoute.snapshot.params['companyId'] : '';
    }

    loadAll() {
        let obj = {
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort(),
            code: this.userGroupSearch.code,
            name: this.userGroupSearch.name,
            description: this.userGroupSearch.description,
            companyId: this.userGroupSearch.companyId,
        };

        this.userGroupService.searchExample(obj).subscribe(
            (res: HttpResponse<UserGroup[]>) => this.onSuccess(res.body, res.headers),
            (res: HttpErrorResponse) => this.onError(res.message)
        );

    }
    searchInForm() {
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
        this.router.navigate(['/admin/user-group'], {
            queryParams:
            {
                page: this.page,
                size: this.itemsPerPage,
                search: this.currentSearch,
                code: this.userGroupSearch.code,
                name: this.userGroupSearch.name,
                description: this.userGroupSearch.description,
                companyId: this.userGroupSearch.companyId,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.currentSearch = '';
        this.router.navigate(['/admin/user-group', {
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
        this.router.navigate(['/admin/user-group', {
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
        this.registerChangeInUserGroups();
        this.companyService.query().subscribe(
            (res: HttpResponse<Company[]>) => this.companies = res.body,
            (res: HttpErrorResponse) => this.onError(res.message)
        );


    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: UserGroup) {
        return item.id;
    }
    registerChangeInUserGroups() {
        this.eventSubscriber = this.eventManager.subscribe('userGroupListModification', (response) => this.loadAll());
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
        this.userGroups = data;
    }
    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }

    public deleteItem(id: number) {
        this.userGroupPopupService
            .open(UserGroupDeleteDialogComponent as Component, id);
    }
}
