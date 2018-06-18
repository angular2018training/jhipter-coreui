import {Component, OnInit, OnDestroy} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {JhiEventManager} from 'ng-jhipster';

import {UserModalService} from './user-modal.service';
import {User, UserService} from '../../shared';
import {Subscription} from "rxjs/Rx";

@Component({
  selector: 'jhi-user-mgmt-edit',
  templateUrl: './user-management-edit.component.html'
})
export class UserMgmtEditComponent implements OnInit, OnDestroy {

  user: User;
  languages: any[];
  authorities: any[];
  isSaving: Boolean;
  private subscription: Subscription;

  constructor(//public activeModal: NgbActiveModal,
              private userService: UserService,
              private eventManager: JhiEventManager,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.isSaving = false;
    this.authorities = [];
    this.subscription = this.route.params.subscribe((params) => {
      this.load(params['login']);
    });
    /*this.userService.authorities().subscribe((authorities) => {
      this.authorities = authorities;
    });*/
  }

  load(login) {
    this.userService.find(login).subscribe((response) => {
      this.user = response.body;
      this.authorities = this.user.authorities;
    });
  }

  clear() {
    // this.activeModal.dismiss('cancel');

  }

  save() {
    this.isSaving = true;
    if (this.user.id !== null) {
      this.userService.update(this.user).subscribe((response) => this.onSaveSuccess(response), () => this.onSaveError());
    } else {
      this.user.langKey = 'en';
      this.userService.create(this.user).subscribe((response) => this.onSaveSuccess(response), () => this.onSaveError());
    }
  }

  private onSaveSuccess(result) {
    this.eventManager.broadcast({name: 'userListModification', content: 'OK'});
    this.isSaving = false;
    //this.activeModal.dismiss(result.body);
  }

  private onSaveError() {
    this.isSaving = false;
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}

