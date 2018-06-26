import { Component, OnInit, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import {NgForm} from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { FileUpload } from './file-upload.model';
import { FileUploadPopupService } from './file-upload-popup.service';
import { FileUploadService } from './file-upload.service';
import { FileUploadDeleteDialogComponent } from './file-upload-delete-dialog.component';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {FileUploadSearch} from './file-upload.search.model';

import {Company} from '../company/company.model';
import {CompanyService} from "../company/company.service";
@Component({
    selector: 'jhi-file-upload',
    templateUrl: './file-upload.component.html'
})
export class FileUploadComponent implements OnInit, OnDestroy {

currentAccount: any;
    fileUploads: FileUpload[];
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
    fileUploadSearch : FileUploadSearch;

    companies : Company[];
    @ViewChild(NgForm) searchForm: NgForm;

    constructor(
        private fileUploadService: FileUploadService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private dataUtils: JhiDataUtils,
        private router: Router,
        private eventManager: JhiEventManager,
        private companyService: CompanyService,

        private fileUploadPopupService: FileUploadPopupService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;

        });
        this.companies =[];

        this.fileUploadSearch = new FileUploadSearch();

        this.fileUploadSearch.name = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['name'] ?
                        this.activatedRoute.snapshot.params[' name'] : '';
        this.fileUploadSearch.content = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['content'] ?
                        this.activatedRoute.snapshot.params[' content'] : '';
        this.fileUploadSearch.uploadTime = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['uploadTime'] ?
                        this.activatedRoute.snapshot.params[' uploadTime'] : '';
        this.fileUploadSearch.contentType = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['contentType'] ?
                        this.activatedRoute.snapshot.params[' contentType'] : '';
        this.fileUploadSearch.companyId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['companyId'] ?
                         this.activatedRoute.snapshot.params['companyId'] : '';
    }

    loadAll() {
         var obj = {
         page: this.page -1,
         size: this.itemsPerPage,
         sort: this.sort(),
         name : this.fileUploadSearch.name,
         content : this.fileUploadSearch.content,
         uploadTime : this.fileUploadSearch.uploadTime,
         contentType : this.fileUploadSearch.contentType,
           companyId : this.fileUploadSearch.companyId,
         };

        this.fileUploadService.searchExample(obj).subscribe(
          (res: HttpResponse<FileUpload[]>) => this.onSuccess(res.body, res.headers),
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
        this.router.navigate(['/setup/file-upload'], {queryParams:
            {
                page: this.page,
                size: this.itemsPerPage,
                search: this.currentSearch,
                name : this.fileUploadSearch.name,
                content : this.fileUploadSearch.content,
                uploadTime : this.fileUploadSearch.uploadTime,
                contentType : this.fileUploadSearch.contentType,
                companyId : this.fileUploadSearch.companyId,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.currentSearch = '';
        this.router.navigate(['/setup/file-upload', {
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
        this.router.navigate(['/setup/file-upload', {
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
        this.registerChangeInFileUploads();
        this.companyService.query().subscribe(
            (res: HttpResponse<Company[]>) => this.companies = res.body,
            (res: HttpErrorResponse) => this.onError(res.message)
        );


    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: FileUpload) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    registerChangeInFileUploads() {
        this.eventSubscriber = this.eventManager.subscribe('fileUploadListModification', (response) => this.loadAll());
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
        this.fileUploads = data;
    }
    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }

    public deleteItem(id:number){
        this.fileUploadPopupService
            .open(FileUploadDeleteDialogComponent as Component, id);
    }
}
