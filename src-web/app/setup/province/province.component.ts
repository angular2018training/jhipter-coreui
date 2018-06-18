import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Province } from './province.model';
import { ProvinceService } from './province.service';
import { Principal } from '../../shared';
import {ProvincePopupService} from "./province-popup.service";
import {ProvinceDeleteDialogComponent} from "./province-delete-dialog.component";

@Component({
  selector: 'jhi-province',
  templateUrl: './province.component.html'
})
export class ProvinceComponent implements OnInit, OnDestroy {
  provinces: Province[];
  currentAccount: any;
  eventSubscriber: Subscription;
  currentSearch: string;

  constructor(
    private provinceService: ProvinceService,
    private jhiAlertService: JhiAlertService,
    private eventManager: JhiEventManager,
    private activatedRoute: ActivatedRoute,
    private principal: Principal,

    private provincePopupService: ProvincePopupService
  ) {
    this.currentSearch = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['search'] ?
      this.activatedRoute.snapshot.params['search'] : '';
  }

  loadAll() {
    if (this.currentSearch) {
      this.provinceService.search({
        query: this.currentSearch,
      }).subscribe(
        (res: HttpResponse<Province[]>) => this.provinces = res.body,
        (res: HttpErrorResponse) => this.onError(res.message)
      );
      return;
    }
    this.provinceService.query().subscribe(
      (res: HttpResponse<Province[]>) => {
        this.provinces = res.body;
        this.currentSearch = '';
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  search(query) {
    if (!query) {
      return this.clear();
    }
    this.currentSearch = query;
    this.loadAll();
  }

  clear() {
    this.currentSearch = '';
    this.loadAll();
  }
  ngOnInit() {
    this.loadAll();
    this.principal.identity().then((account) => {
      this.currentAccount = account;
    });
    this.registerChangeInProvinces();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: Province) {
    return item.id;
  }
  registerChangeInProvinces() {
    this.eventSubscriber = this.eventManager.subscribe('provinceListModification', (response) => this.loadAll());
  }

  private onError(error) {
    this.jhiAlertService.error(error.message, null, null);
  }

  public deleteItem(id:number){
    this.provincePopupService
      .open(ProvinceDeleteDialogComponent as Component, id);
  }
}
