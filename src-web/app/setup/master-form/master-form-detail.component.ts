import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { MasterForm } from './master-form.model';
import { MasterFormService } from './master-form.service';

@Component({
    selector: 'jhi-master-form-detail',
    templateUrl: './master-form-detail.component.html',

})
export class MasterFormDetailComponent implements OnInit, OnDestroy {

    masterForm: MasterForm;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private masterFormService: MasterFormService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInMasterForms();
    }

    load(id) {
        this.masterFormService.find(id)
            .subscribe((masterFormResponse: HttpResponse<MasterForm>) => {
                this.masterForm = masterFormResponse.body;
            });
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInMasterForms() {
        this.eventSubscriber = this.eventManager.subscribe(
            'masterFormListModification',
            (response) => this.load(this.masterForm.id)
        );
    }
}
