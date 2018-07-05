import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { UserPostOffice } from '../../shared/model/user-post-office.model';
import { UserPostOfficeService } from '../../shared/service/user-post-office.service';

@Component({
    selector: 'jhi-user-post-office-detail',
    templateUrl: './user-post-office-detail.component.html'
})
export class UserPostOfficeDetailComponent implements OnInit, OnDestroy {

    userPostOffice: UserPostOffice;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private userPostOfficeService: UserPostOfficeService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInUserPostOffices();
    }

    load(id) {
        this.userPostOfficeService.find(id)
            .subscribe((userPostOfficeResponse: HttpResponse<UserPostOffice>) => {
                this.userPostOffice = userPostOfficeResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInUserPostOffices() {
        this.eventSubscriber = this.eventManager.subscribe(
            'userPostOfficeListModification',
            (response) => this.load(this.userPostOffice.id)
        );
    }
}
