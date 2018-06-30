import { Component, OnInit, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import {NgForm} from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { DetailForm } from './detail-form.model';
import { DetailFormPopupService } from './detail-form-popup.service';
import { DetailFormService } from './detail-form.service';
import { DetailFormDeleteDialogComponent } from './detail-form-delete-dialog.component';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {DetailFormSearch} from './detail-form.search.model';
    import { MasterForm, MasterFormService } from '../master-form';
    import { Province, ProvinceService } from '../province';
    import { District, DistrictService } from '../district';


@Component({
    selector: 'jhi-detail-form',
    templateUrl: './detail-form.component.html'
})
export class DetailFormComponent implements OnInit, OnDestroy {

currentAccount: any;
    detailForms: DetailForm[];
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
    detailFormSearch : DetailFormSearch;

    masterForms : MasterForm[];

    provinces : Province[];

    districts : District[];
    @ViewChild(NgForm) searchForm: NgForm;

    constructor(
        private detailFormService: DetailFormService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private dataUtils: JhiDataUtils,
        private router: Router,
        private eventManager: JhiEventManager,
        private masterFormService: MasterFormService,
        private provinceService: ProvinceService,
        private districtService: DistrictService,

        private detailFormPopupService: DetailFormPopupService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;

        });
        this.masterForms =[];
        this.provinces =[];
        this.districts =[];

        this.detailFormSearch = new DetailFormSearch();

        this.detailFormSearch.description = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['description'] ?
                        this.activatedRoute.snapshot.params[' description'] : '';
        this.detailFormSearch.createDate = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['createDate'] ?
                        this.activatedRoute.snapshot.params[' createDate'] : '';
        this.detailFormSearch.uploadFile = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['uploadFile'] ?
                        this.activatedRoute.snapshot.params[' uploadFile'] : '';
        this.detailFormSearch.isActive = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['isActive'] ?
                        this.activatedRoute.snapshot.params[' isActive'] : '';
        this.detailFormSearch.masterFormId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['masterFormId'] ?
                         this.activatedRoute.snapshot.params['masterFormId'] : '';
        this.detailFormSearch.provinceId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['provinceId'] ?
                         this.activatedRoute.snapshot.params['provinceId'] : '';
        this.detailFormSearch.districtId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['districtId'] ?
                         this.activatedRoute.snapshot.params['districtId'] : '';
    }

    loadAll() {
         var obj = {
         page: this.page -1,
         size: this.itemsPerPage,
         sort: this.sort(),
         description : this.detailFormSearch.description,
         createDate : this.detailFormSearch.createDate,
         uploadFile : this.detailFormSearch.uploadFile,
         isActive : this.detailFormSearch.isActive,
           masterFormId : this.detailFormSearch.masterFormId,
           provinceId : this.detailFormSearch.provinceId,
           districtId : this.detailFormSearch.districtId,
         };

        this.detailFormService.searchExample(obj).subscribe(
          (res: HttpResponse<DetailForm[]>) => this.onSuccess(res.body, res.headers),
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
        this.router.navigate(['/setup/detail-form'], {queryParams:
            {
                page: this.page,
                size: this.itemsPerPage,
                search: this.currentSearch,
                description : this.detailFormSearch.description,
                createDate : this.detailFormSearch.createDate,
                uploadFile : this.detailFormSearch.uploadFile,
                isActive : this.detailFormSearch.isActive,
                masterFormId : this.detailFormSearch.masterFormId,
                provinceId : this.detailFormSearch.provinceId,
                districtId : this.detailFormSearch.districtId,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.currentSearch = '';
        this.router.navigate(['/setup/detail-form', {
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
        this.router.navigate(['/setup/detail-form', {
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
        this.registerChangeInDetailForms();
        this.masterFormService.query().subscribe(
            (res: HttpResponse<MasterForm[]>) => this.masterForms = res.body,
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

    trackId(index: number, item: DetailForm) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    registerChangeInDetailForms() {
        this.eventSubscriber = this.eventManager.subscribe('detailFormListModification', (response) => this.loadAll());
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
        this.detailForms = data;
    }
    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }

    public deleteItem(id:number){
        this.detailFormPopupService
            .open(DetailFormDeleteDialogComponent as Component, id);
    }
}
