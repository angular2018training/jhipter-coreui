import { Component, OnInit, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import {NgForm} from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { MasterForm } from './master-form.model';
import { MasterFormPopupService } from './master-form-popup.service';
import { MasterFormService } from './master-form.service';
import { MasterFormDeleteDialogComponent } from './master-form-delete-dialog.component';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {MasterFormSearch} from './master-form.search.model';
    import { DetailForm, DetailFormService } from '../detail-form';
    import { Province, ProvinceService } from '../province';
    import { District, DistrictService } from '../district';


@Component({
    selector: 'jhi-master-form',
    templateUrl: './master-form.component.html'
})
export class MasterFormComponent implements OnInit, OnDestroy {

currentAccount: any;
    masterForms: MasterForm[];
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
    masterFormSearch : MasterFormSearch;

    detailForms : DetailForm[];

    provinces : Province[];

    districts : District[];
    @ViewChild(NgForm) searchForm: NgForm;

    constructor(
        private masterFormService: MasterFormService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private dataUtils: JhiDataUtils,
        private router: Router,
        private eventManager: JhiEventManager,
        private provinceService: ProvinceService,
        private districtService: DistrictService,

        private masterFormPopupService: MasterFormPopupService
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

        this.masterFormSearch = new MasterFormSearch();

        this.masterFormSearch.code = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['code'] ?
                        this.activatedRoute.snapshot.params[' code'] : '';
        this.masterFormSearch.note = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['note'] ?
                        this.activatedRoute.snapshot.params[' note'] : '';
        this.masterFormSearch.receiveTime = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['receiveTime'] ?
                        this.activatedRoute.snapshot.params[' receiveTime'] : '';
        this.masterFormSearch.imageFile = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['imageFile'] ?
                        this.activatedRoute.snapshot.params[' imageFile'] : '';
        this.masterFormSearch.isActive = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['isActive'] ?
                        this.activatedRoute.snapshot.params[' isActive'] : '';
        this.masterFormSearch.provinceId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['provinceId'] ?
                         this.activatedRoute.snapshot.params['provinceId'] : '';
        this.masterFormSearch.districtId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['districtId'] ?
                         this.activatedRoute.snapshot.params['districtId'] : '';
    }

    loadAll() {
         var obj = {
         page: this.page -1,
         size: this.itemsPerPage,
         sort: this.sort(),
         code : this.masterFormSearch.code,
         note : this.masterFormSearch.note,
         receiveTime : this.masterFormSearch.receiveTime,
         imageFile : this.masterFormSearch.imageFile,
         isActive : this.masterFormSearch.isActive,
           provinceId : this.masterFormSearch.provinceId,
           districtId : this.masterFormSearch.districtId,
         };

        this.masterFormService.searchExample(obj).subscribe(
          (res: HttpResponse<MasterForm[]>) => this.onSuccess(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );

    }
    searchInForm(){
          this.page = 0;
          this.principal.identity().then((account) => {
            this.currentAccount = account;
            console.log(this.currentAccount)
          });
          this.transition();

    }
    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }
    transition() {
        this.router.navigate(['/setup/master-form'], {queryParams:
            {
                page: this.page,
                size: this.itemsPerPage,
                search: this.currentSearch,
                code : this.masterFormSearch.code,
                note : this.masterFormSearch.note,
                receiveTime : this.masterFormSearch.receiveTime,
                imageFile : this.masterFormSearch.imageFile,
                isActive : this.masterFormSearch.isActive,
                provinceId : this.masterFormSearch.provinceId,
                districtId : this.masterFormSearch.districtId,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.currentSearch = '';
        this.router.navigate(['/setup/master-form', {
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
        this.router.navigate(['/setup/master-form', {
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
        this.registerChangeInMasterForms();
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

    trackId(index: number, item: MasterForm) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    registerChangeInMasterForms() {
        this.eventSubscriber = this.eventManager.subscribe('masterFormListModification', (response) => this.loadAll());
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
        this.masterForms = data;
    }
    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }

    public deleteItem(id:number){
        this.masterFormPopupService
            .open(MasterFormDeleteDialogComponent as Component, id);
    }
}
