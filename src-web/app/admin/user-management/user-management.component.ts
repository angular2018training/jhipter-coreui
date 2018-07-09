import { Component, OnInit, OnDestroy } from '@angular/core';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { ITEMS_PER_PAGE, Principal, User, UserService } from '../../shared';
import {UserMgmtDeleteDialogComponent} from "./user-management-delete-dialog.component";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {UserSearch} from "../../shared/user/user-search.model";
import {DATE_FORMAT, DATE_TIME_FORMAT} from "../../shared/constants/input.constants";
import * as moment from 'moment';
import {PostOfficeService} from "../../shared/service/post-office.service";
import {UserGroupService} from "../../shared/service/user-group.service";
import {PostOffice} from "../../shared/model/post-office.model";
import {UserGroup} from "../../shared/model/user-group.model";
@Component({
    selector: 'jhi-user-mgmt',
    templateUrl: './user-management.component.html'
})
export class UserMgmtComponent implements OnInit, OnDestroy {

    currentAccount: any;
    users: User[];
    userSearch : UserSearch;
    postOffices : PostOffice[];
    userGroups : UserGroup[];
    error: any;
    success: any;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;
    createdDate : any = null;
    createdDateConfig :any ={
      maxDate : new Date(),
      dateInputFormat: DATE_FORMAT
    }
    constructor(
        private userService: UserService,
        private alertService: JhiAlertService,
        private principal: Principal,
        private parseLinks: JhiParseLinks,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager,
        private modalService: NgbModal,

        private postOfficeService: PostOfficeService,
        private userGroupService: UserGroupService,
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;

        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data['pagingParams'].page;
            this.previousPage = data['pagingParams'].page;
            this.reverse = data['pagingParams'].ascending;
            this.predicate = data['pagingParams'].predicate;
        });



    }

    ngOnInit() {
      this.userSearch = new UserSearch();
      this.userSearch.login = this.activatedRoute.snapshot && this.activatedRoute.snapshot.queryParams['login'] ?
        this.activatedRoute.snapshot.queryParams['login'] : '';
      this.userSearch.name = this.activatedRoute.snapshot && this.activatedRoute.snapshot.queryParams['name'] ?
        this.activatedRoute.snapshot.queryParams['name'] : '';
      this.userSearch.email = this.activatedRoute.snapshot && this.activatedRoute.snapshot.queryParams['email'] ?
        this.activatedRoute.snapshot.queryParams['email'] : '';
      this.userSearch.phone = this.activatedRoute.snapshot && this.activatedRoute.snapshot.queryParams['phone'] ?
        this.activatedRoute.snapshot.queryParams['phone'] : '';
      this.userSearch.activated = this.activatedRoute.snapshot && this.activatedRoute.snapshot.queryParams['activated'] ?
        this.activatedRoute.snapshot.queryParams['activated'] : null;

      this.userSearch.createdDateFrom = this.activatedRoute.snapshot && this.activatedRoute.snapshot.queryParams['createdDateFrom'] ?
        moment(this.activatedRoute.snapshot.queryParams['createdDateFrom'], DATE_TIME_FORMAT).toDate(): null;
      this.userSearch.createdDateTo = this.activatedRoute.snapshot && this.activatedRoute.snapshot.queryParams['createdDateTo'] ?
        moment(this.activatedRoute.snapshot.queryParams['createdDateTo'], DATE_TIME_FORMAT).toDate() : null;

      this.userSearch.postOfficeId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.queryParams['postOfficeId'] ?
        this.activatedRoute.snapshot.queryParams['postOfficeId'] : null;
      if(this.userSearch.createdDateFrom == null || this.userSearch.createdDateTo ) {
        this.createdDate = null;
      }else {
        this.createdDate[0] = this.userSearch.createdDateFrom;
        this.createdDate[1] = this.userSearch.createdDateTo;
      }
        this.principal.identity().then((account) => {
            this.currentAccount = account;
            this.postOfficeService.query({"companyId.equals":this.currentAccount.companyId})
              .subscribe((res: HttpResponse<PostOffice[]>) => {
              this.postOffices = res.body;

              }, (res: HttpErrorResponse) => this.onError(res.message));
            this.userGroupService.query({"companyId.equals":this.currentAccount.companyId})
              .subscribe((res: HttpResponse<UserGroup[]>) => {
              this.userGroups = res.body;
              this.userSearch.userGroupId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.queryParams['userGroupId'] ?
                this.activatedRoute.snapshot.queryParams['userGroupId'] : null;
              }, (res: HttpErrorResponse) => this.onError(res.message));
            this.loadAll();
            this.registerChangeInUsers();
        });

    }

    transition() {
      this.router.navigate(['/admin/user-management'], {
        queryParams:
          {
            page: this.page,
            size: this.itemsPerPage,
            login: this.userSearch.login,
            name: this.userSearch.name,
            email: this.userSearch.email,
            phone: this.userSearch.phone,
            activated :this.userSearch.activated,
            createdDateFrom:  this.userSearch.createdDateFrom?moment(this.userSearch.createdDateFrom).format(DATE_TIME_FORMAT):'',
            createdDateTo: this.userSearch.createdDateTo!=null? moment(this.userSearch.createdDateTo).format(DATE_TIME_FORMAT):'',
            postOfficeId : this.userSearch.postOfficeId,
            userGroupId : this.userSearch.userGroupId,
            sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
          }
      });
      this.loadAll();
    }


  searchInForm(){
      this.page = 1;
      if(this.createdDate){
        this.userSearch.createdDateFrom = this.createdDate[0];
        this.userSearch.createdDateTo = this.createdDate[1];
      }

      this.transition();
    }
  clear(){

  }
    ngOnDestroy() {
        this.routeData.unsubscribe();
    }

    registerChangeInUsers() {
        this.eventManager.subscribe('userListModification', (response) => this.loadAll());
    }

    setActive(user, isActivated) {
        user.activated = isActivated;

        this.userService.update(user).subscribe(
            (response) => {
                if (response.status === 200) {
                    this.error = null;
                    this.success = 'OK';
                    this.loadAll();
                } else {
                    this.success = null;
                    this.error = 'ERROR';
                }
            });
    }

    loadAll() {
        this.userService.query({
            page: this.page - 1,
            size: this.itemsPerPage,
            "login.contains": this.userSearch.login,
            "name.contains": this.userSearch.name,
            "email.contains": this.userSearch.email,
            "phone.contains": this.userSearch.phone,
            "activated.equals" :this.userSearch.activated,
            "createdDate.greaterOrEqualThan":  this.userSearch.createdDateFrom?moment(this.userSearch.createdDateFrom).toISOString():null,
            "createdDate.lessOrEqualThan": this.userSearch.createdDateTo?moment(this.userSearch.createdDateTo).toISOString():null,
            "companyId.equals":this.currentAccount.companyId,
            "postOfficeId.equals":this.userSearch.postOfficeId,
            "userGroupId.equals":this.userSearch.userGroupId,
            sort: this.sort()}).subscribe(
                (res: HttpResponse<User[]>) => this.onSuccess(res.body, res.headers),
                (res: HttpResponse<any>) => this.onError(res.body)
        );
    }

    trackIdentity(index, item: User) {
        return item.id;
    }
    trackByPostOfficeId(index, item: PostOffice) {
      return item.id;
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }


    private onSuccess(data, headers) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = headers.get('X-Total-Count');
        this.queryCount = this.totalItems;
        this.users = data;
    }

    private onError(error) {
        this.alertService.error(error.error, error.message, null);
    }

  trackPostOfficeById(index: number, item: PostOffice) {
    return item.id;
  }

  trackUserGroupById(index: number, item: UserGroup) {
    return item.id;
  }

  deleteUser(user: User) {
    const modalRef = this.modalService.open(UserMgmtDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.user = user;
    modalRef.result.then(
      result => {
        // Left blank intentionally, nothing to do here
      },
      reason => {
        // Left blank intentionally, nothing to do here
      }
    );
  }
}
