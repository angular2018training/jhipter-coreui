import {Component, OnInit, OnDestroy} from '@angular/core';
import {HttpResponse, HttpErrorResponse} from '@angular/common/http';

import {Observable} from 'rxjs/Observable';
import {JhiEventManager} from 'ng-jhipster';

import {Province} from './province.model';
import {ProvinceService} from './province.service';
import {ActivatedRoute} from "@angular/router";
import {Subscription} from 'rxjs/Subscription';

@Component({
  selector: 'jhi-province-dialog',
  templateUrl: './province-dialog.component.html'
})
export class ProvinceDialogComponent implements OnInit, OnDestroy {

  province: Province;
  isSaving: boolean;
  private subscription: Subscription;

  constructor(private provinceService: ProvinceService,
              private eventManager: JhiEventManager,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.isSaving = false;
    this.province = new Province();
    this.subscription = this.route.params.subscribe((params) => {
      this.load(params['id']);
    });
  }


  previousState() {
    window.history.back();
  }

  load(id) {
    if (id != null) {
      this.provinceService.find(id).subscribe((response) => {
        this.province = response.body;
      });
    }

  }

  save() {
    this.isSaving = true;
    if (this.province.id !== undefined) {
      this.subscribeToSaveResponse(
        this.provinceService.update(this.province));
    } else {
      this.subscribeToSaveResponse(
        this.provinceService.create(this.province));
    }
  }

  private subscribeToSaveResponse(result: Observable<HttpResponse<Province>>) {
    result.subscribe((res: HttpResponse<Province>) =>
      this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
  }

  private onSaveSuccess(result: Province) {
    this.eventManager.broadcast({name: 'provinceListModification', content: 'OK'});
    this.isSaving = false;
  }

  private onSaveError() {
    this.isSaving = false;
  }

  ngOnDestroy() {
    this.subscription = null;

  }
}

