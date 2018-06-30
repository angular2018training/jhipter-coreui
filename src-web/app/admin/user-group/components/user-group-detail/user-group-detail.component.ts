import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { UserGroup } from '../../models/user-group.model';
import { UserGroupService } from '../../services/user-group.service';

@Component({
    selector: 'jhi-user-group-detail',
    templateUrl: './user-group-detail.component.html'
})
export class UserGroupDetailComponent implements OnInit, OnDestroy {

    userGroup: UserGroup;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private userGroupService: UserGroupService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInUserGroups();
    }

    load(id) {
        this.userGroupService.find(id)
            .subscribe((userGroupResponse: HttpResponse<UserGroup>) => {
                this.userGroup = userGroupResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInUserGroups() {
        this.eventSubscriber = this.eventManager.subscribe(
            'userGroupListModification',
            (response) => this.load(this.userGroup.id)
        );
    }
}
