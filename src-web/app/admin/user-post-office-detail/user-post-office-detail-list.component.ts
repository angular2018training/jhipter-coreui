import { Component, OnInit,Input, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
    import { ActivatedRoute, Router } from '@angular/router';
    import {NgForm} from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { UserPostOffice } from '../../shared/model/user-post-office.model';
import { UserPostOfficeDetailPopupService } from './user-post-office-detail-popup.service';
import { UserPostOfficeService } from '../../shared/service/user-post-office.service';
import { UserPostOfficeDetailUpdateComponent} from './user-post-office-detail-update.component';
import { UserPostOfficeDetailDeleteDialogComponent } from './user-post-office-detail-delete-dialog.component';


    import { Company } from '../../shared/model/company.model';
    import { CompanyService } from '../../shared/service/company.service';
    import { PostOffice } from '../../shared/model/post-office.model';
    import { PostOfficeService } from '../../shared/service/post-office.service';
    import { UserGroup } from '../../shared/model/user-group.model';
    import { UserGroupService } from '../../shared/service/user-group.service';


@Component({
    selector: 'jhi-user-post-office-detail-list',
    templateUrl: './user-post-office-detail-list.component.html'
})
export class UserPostOfficeDetailListComponent implements OnInit, OnDestroy {
    
    currentAccount: any;
    userPostOffices: UserPostOffice[];
    eventDeleteItemSubscriber: Subscription;
    eventUpdateItemSubscriber: Subscription;

    @Input() userExtraInfoParentId: number;
    constructor(
        private eventManager : JhiEventManager,
        private userPostOfficeDetailPopupService :UserPostOfficeDetailPopupService,
        private userPostOfficeService: UserPostOfficeService,
        private alertService :AlertService,

    ) {


    }

    createDetailForm(){
        this.userPostOfficeDetailPopupService.open(UserPostOfficeDetailUpdateComponent as Component,null,this.userExtraInfoParentId)
    }
    editDetailForm(userPostOfficeId:number){
        this.userPostOfficeDetailPopupService.open(UserPostOfficeDetailUpdateComponent as Component,userPostOfficeId,this.userExtraInfoParentId)
    }

    deleteDetailForm(userPostOfficeId:number){
        this.userPostOfficeDetailPopupService
          .open(UserPostOfficeDetailDeleteDialogComponent as Component, userPostOfficeId);
    }



    ngOnInit() {
        if(this.userExtraInfoParentId){
            this.userPostOfficeService.query({"userExtraInfoParentId.equals":this.userExtraInfoParentId, size: 10000}).subscribe((res: HttpResponse<UserPostOffice[]>) => { this.userPostOffices = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        }

        this.eventDeleteItemSubscriber = this.eventManager.subscribe('user-post-office-detail-delete-item',()=>{
            if(this.userExtraInfoParentId){
                this.userPostOfficeService.query({"userExtraInfoParentId.equals":this.userExtraInfoParentId, size: 10000}).subscribe((res: HttpResponse<UserPostOffice[]>) => { this.userPostOffices  = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
            }
        });
        this.eventUpdateItemSubscriber = this.eventManager.subscribe('user-post-office-detail.save.success',()=>{
            if(this.userExtraInfoParentId){
                this.userPostOfficeService.query({"userExtraInfoParentId.equals":this.userExtraInfoParentId, size: 10000}).subscribe((res: HttpResponse<UserPostOffice[]>) => { this.userPostOffices = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
            }
        });
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventDeleteItemSubscriber);
        this.eventManager.destroy(this.eventUpdateItemSubscriber);
    }


    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

}
