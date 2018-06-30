import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { DetailForm } from './detail-form.model';
import { DetailFormService } from './detail-form.service';

@Component({
    selector: 'jhi-detail-form-detail',
    templateUrl: './detail-form-detail.component.html'
})
export class DetailFormDetailComponent implements OnInit, OnDestroy {

    detailForm: DetailForm;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private detailFormService: DetailFormService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInDetailForms();
    }

    load(id) {
        this.detailFormService.find(id)
            .subscribe((detailFormResponse: HttpResponse<DetailForm>) => {
                this.detailForm = detailFormResponse.body;
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

    registerChangeInDetailForms() {
        this.eventSubscriber = this.eventManager.subscribe(
            'detailFormListModification',
            (response) => this.load(this.detailForm.id)
        );
    }
}
