import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {User} from "../../shared/user/user.model";
import {JhiLanguageHelper} from "../../shared/language/language.helper";
import {UserService} from "../../shared/user/user.service";
import {UserExtraInfo} from "../../shared/model/user-extra-info.model";
import {DATE_PICKEK_CONFIG, DATE_TIME_FORMAT} from "../../shared/constants/input.constants";
import * as moment from 'moment';
import {JhiDataUtils} from "ng-jhipster";
import {Principal} from "../../shared/auth/principal.service";

@Component({
    selector: 'jhi-user-mgmt-update',
    templateUrl: './user-management-update.component.html'
})
export class UserMgmtUpdateComponent implements OnInit {
    user: User;
    languages: any[];
    authorities: any[];
    isSaving: boolean;
    validDateDp: any;
    lastLoginDate: string;
    bsConfig:any =DATE_PICKEK_CONFIG;
    isNew :boolean;
    currentAccount:any;
    constructor(
        private languageHelper: JhiLanguageHelper,
        private userService: UserService,
        private route: ActivatedRoute,
        private router: Router,
        private dataUtils: JhiDataUtils,
        private principal: Principal
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({ user }) => {
            this.user = user.body ? user.body : user;
            if(!user.id) this.isNew = true;
            if(!this.user.userExtraInfo) {
              this.user.userExtraInfo = new UserExtraInfo();
              this.principal.identity().then((account) => {
                this.currentAccount = account;
                if(!this.user.userExtraInfo.companyId) {
                  this.user.userExtraInfo.companyId = this.currentAccount.companyId;
                }
              });

            }
        });
        this.authorities = [];
        this.userService.authoritiesAll().subscribe(authorities => {
            this.authorities = authorities;
        });
        this.languageHelper.getAll().then(languages => {
            this.languages = languages;
        });
    }

    previousState() {
        this.router.navigate(['/admin/user-management']);
    }

    setFileData(event, entity, field, isImage) {
      this.dataUtils.setFileData(event, entity, field, isImage);
    }

    byteSize(field) {
      return this.dataUtils.byteSize(field);
    }

    save() {
        this.isSaving = true;
        if (this.user.id !== null) {
            this.userService.update(this.user).subscribe(response => this.onSaveSuccess(response), () => this.onSaveError());
        } else {
            this.userService.create(this.user).subscribe(response => this.onSaveSuccess(response), () => this.onSaveError());
        }
    }

    private onSaveSuccess(result) {
        this.isSaving = false;
        if(this.isNew){
          let url :string = '/admin/user-management/'+this.user.login+'/edit';
          this.router.navigate([url]);
        }
        //this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    openFile(contentType, field) {
      return this.dataUtils.openFile(contentType, field);
    }
}
