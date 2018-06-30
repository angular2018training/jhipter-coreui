import {Component, OnInit, OnDestroy, ViewChild} from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { District } from './district.model';
import { DistrictService } from './district.service';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {Province} from "../province/province.model";
import {ProvinceService} from "../province/province.service";
import {DistrictSearch} from "./district.search.model";
import {NgForm} from "@angular/forms";
import {DistrictDeleteDialogComponent} from "./district-delete-dialog.component";
import {DistrictPopupService} from "./district-popup.service";

@Component({
  selector: 'jhi-district',
  templateUrl: './district.component.html'
})
export class DistrictComponent implements OnInit, OnDestroy {

  districts: District[];
  currentAccount: any;
  eventSubscriber: Subscription;
  itemsPerPage: number;
  links: any;
  page: any;
  predicate: any;
  queryCount: any;
  reverse: any;
  totalItems: number;
  currentSearch: string;

  districtSearch : DistrictSearch;
  provinces : Province[];
  @ViewChild(NgForm) searchForm: NgForm;
  constructor(
    private districtService: DistrictService,
    private jhiAlertService: JhiAlertService,
    private eventManager: JhiEventManager,
    private parseLinks: JhiParseLinks,
    private activatedRoute: ActivatedRoute,
    private principal: Principal,
    private provinceService : ProvinceService,
    private districtPopupService: DistrictPopupService,
    private router : Router
  ) {
    this.districts = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 1;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.reverse = true;
    this.currentSearch = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['search'] ?
      this.activatedRoute.snapshot.params['search'] : '';
    this.provinces = [];

    this.districtSearch = new DistrictSearch();
  }

  loadAll() {
    /*if (this.currentSearch) {
      this.districtService.searchExample(this.districtSearch,{
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      }).subscribe(
        (res: HttpResponse<District[]>) => this.onSuccess(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
      return;
    }*/
    var obj = {page: this.page -1,size: this.itemsPerPage,  sort: this.sort(),code:this.districtSearch.code,name:this.districtSearch.name,description:this.districtSearch.description,provinceId:this.districtSearch.provinceId};

    this.districtService.searchExample(obj).subscribe(
      (res: HttpResponse<District[]>) => this.onSuccess(res.body, res.headers),
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }
  searchInForm(){
    this.page = 0;
    this.transition();

  }
  transition() {
    this.router.navigate(['/setup/district', {
      page: this.page,
      size: this.itemsPerPage,
      code : this.districtSearch.code!=null?this.districtSearch.code:'',
      name : this.districtSearch.name!=null?this.districtSearch.name:'',
      description : this.districtSearch.description!=null?this.districtSearch.description:'',
      provinceId : this.districtSearch.provinceId!=null?this.districtSearch.provinceId:'',
      sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
    }]);
    this.loadAll();
  }

  reset() {
    this.page = 1;
    this.districts = [];
    this.loadAll();
  }

  loadPage(page) {
    this.page = page;
    this.loadAll();
  }

  clear() {
    this.districts = [];
    this.links = {
      last: 0
    };
    this.page = 1;
    this.predicate = 'id';
    this.reverse = true;
    this.currentSearch = '';
    this.loadAll();
  }

  search(query) {
    if (!query) {
      return this.clear();
    }
    this.districts = [];
    this.links = {
      last: 0
    };
    this.page = 1;
    this.predicate = '_score';
    this.reverse = false;
    this.currentSearch = query;
    this.loadAll();
  }
  ngOnInit() {
    this.loadAll();
    this.principal.identity().then((account) => {
      this.currentAccount = account;
    });
    this.registerChangeInDistricts();
    this.provinceService.query().subscribe(
      (res: HttpResponse<Province[]>) => this.provinces = res.body,
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
    this.provinces = null;
  }

  trackId(index: number, item: District) {
    return item.id;
  }
  registerChangeInDistricts() {
    this.eventSubscriber = this.eventManager.subscribe('districtListModification', (response) => this.reset());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  private onSuccess(data, headers) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = headers.get('X-Total-Count');
    this.districts = data;
    /*for (let i = 0; i < data.length; i++) {
      this.districts.push(data[i]);
    }*/
  }

  private onError(error) {
    this.jhiAlertService.error(error.message, null, null);
  }

  public deleteItem(id:number){
    this.districtPopupService
      .open(DistrictDeleteDialogComponent as Component, id);
  }
}
