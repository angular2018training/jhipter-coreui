import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { PostOffice } from './post-office.model';
import { PostOfficeService } from './post-office.service';

@Component({
    selector: 'jhi-post-office-detail',
    templateUrl: './post-office-detail.component.html'
})
export class PostOfficeDetailComponent implements OnInit, OnDestroy {

    postOffice: PostOffice;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private postOfficeService: PostOfficeService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInPostOffices();
    }

    load(id) {
        this.postOfficeService.find(id)
            .subscribe((postOfficeResponse: HttpResponse<PostOffice>) => {
                this.postOffice = postOfficeResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInPostOffices() {
        this.eventSubscriber = this.eventManager.subscribe(
            'postOfficeListModification',
            (response) => this.load(this.postOffice.id)
        );
    }
}
