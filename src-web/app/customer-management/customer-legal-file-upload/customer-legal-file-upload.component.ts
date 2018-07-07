import { Component, OnInit, OnDestroy, ViewChild, Input } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';

import { AlertService } from '../../shared/alert/alert-service';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { CustomerLegalFileUpload } from '../../shared/model/customer-legal-file-upload.model';

import { Principal } from '../../shared';
import { CustomerLegalFileUploadService } from '../../shared/service/customer-legal-file-upload.service';
import { FileUploadPopupService } from './file-upload/file-upload-popup.service';
import { FileUpload } from '../../shared/model/file-upload.model';
import { FileUploadDialogComponent } from './file-upload/file-upload-dialog.component';

@Component({
    selector: 'jhi-customer-legal-file-upload',
    templateUrl: './customer-legal-file-upload.component.html',
    providers: [FileUploadPopupService]
})
export class CustomerLegalFileUploadComponent implements OnInit, OnDestroy {
    @Input() legalId;
    currentAccount: any;
    customerLegalFileUploads: CustomerLegalFileUpload[];
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

    fileUploads: FileUpload[];

    @ViewChild(NgForm) searchForm: NgForm;

    constructor(
        private customerLegalFileUploadService: CustomerLegalFileUploadService,
        private parseLinks: JhiParseLinks,
        private principal: Principal,
        private alertService: AlertService,
        private eventManager: JhiEventManager,
        private fileUploadPopupService: FileUploadPopupService
    ) { }

    ngOnInit() {
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });

        this.registerChangeInCustomerLegalFileUploads();

        // this.customerLegalFileUploadService.searchExample({
        //     'customerLegalParentId.equals': this.legalId,
        //     size: 10000
        // }).subscribe(
        //     (res: HttpResponse<CustomerLegalFileUpload[]>) => this.onSuccess(res.body, res.headers),
        //     (res: HttpErrorResponse) => this.onError(res.message)
        // );
    }

    ngOnDestroy() {
        // this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: CustomerLegalFileUpload) {
        return item.id;
    }

    registerChangeInCustomerLegalFileUploads() {
        // this.eventSubscriber = this.eventManager.subscribe('customerLegalFileUploadListModification', (response) => this.loadAll());
    }

    uploadAFile() {
        this.fileUploadPopupService.open(FileUploadDialogComponent as Component);
    }

    private onSuccess(data, headers) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = headers.get('X-Total-Count');
        this.queryCount = this.totalItems;
        // this.page = pagingParams.page;
        this.customerLegalFileUploads = data;
    }
    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    // public deleteItem(id: number) {
    //     this.customerLegalFileUploadPopupService
    //         .open(CustomerLegalFileUploadDeleteDialogComponent as Component, id);
    // }
}
