import { Component, OnInit, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import {NgForm} from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { CustomerPayment } from './customer-payment.model';
import { CustomerPaymentPopupService } from './customer-payment-popup.service';
import { CustomerPaymentService } from './customer-payment.service';
import { CustomerPaymentDeleteDialogComponent } from './customer-payment-delete-dialog.component';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {CustomerPaymentSearch} from './customer-payment.search.model';

import {Company} from '../../setup/company/company.model';
import {CompanyService} from "../../setup/company/company.service";

import {Bank} from '../../setup/bank/bank.model';
import {BankService} from "../../setup/bank/bank.service";

import {UserExtraInfo} from '../../setup/user-extra-info/user-extra-info.model';
import {UserExtraInfoService} from "../../setup/user-extra-info/user-extra-info.service";

import {PaymentType} from '../../setup/payment-type/payment-type.model';
import {PaymentTypeService} from "../../setup/payment-type/payment-type.service";
@Component({
    selector: 'jhi-customer-payment',
    templateUrl: './customer-payment.component.html'
})
export class CustomerPaymentComponent implements OnInit, OnDestroy {

currentAccount: any;
    customerPayments: CustomerPayment[];
    error: any;
    success: any;
    eventSubscriber: Subscription;
    currentSearch: string;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;
    customerPaymentSearch : CustomerPaymentSearch;

    companies : Company[];

    banks : Bank[];

    userExtraInfos : UserExtraInfo[];

    paymentTypes : PaymentType[];
    @ViewChild(NgForm) searchForm: NgForm;

    constructor(
        private customerPaymentService: CustomerPaymentService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private dataUtils: JhiDataUtils,
        private router: Router,
        private eventManager: JhiEventManager,
        private companyService: CompanyService,
        private bankService: BankService,
        private userExtraInfoService: UserExtraInfoService,
        private paymentTypeService: PaymentTypeService,

        private customerPaymentPopupService: CustomerPaymentPopupService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;

        });
        this.companies =[];
        this.banks =[];
        this.userExtraInfos =[];
        this.paymentTypes =[];

        this.customerPaymentSearch = new CustomerPaymentSearch();

        this.customerPaymentSearch.branchName = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['branchName'] ?
                        this.activatedRoute.snapshot.params[' branchName'] : '';
        this.customerPaymentSearch.accountNumber = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['accountNumber'] ?
                        this.activatedRoute.snapshot.params[' accountNumber'] : '';
        this.customerPaymentSearch.accountName = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['accountName'] ?
                        this.activatedRoute.snapshot.params[' accountName'] : '';
        this.customerPaymentSearch.cardNumber = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['cardNumber'] ?
                        this.activatedRoute.snapshot.params[' cardNumber'] : '';
        this.customerPaymentSearch.paymentAmountMoney = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['paymentAmountMoney'] ?
                        this.activatedRoute.snapshot.params[' paymentAmountMoney'] : '';
        this.customerPaymentSearch.idImage = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['idImage'] ?
                        this.activatedRoute.snapshot.params[' idImage'] : '';
        this.customerPaymentSearch.isVerify = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['isVerify'] ?
                        this.activatedRoute.snapshot.params[' isVerify'] : '';
        this.customerPaymentSearch.dateVerify = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['dateVerify'] ?
                        this.activatedRoute.snapshot.params[' dateVerify'] : '';
        this.customerPaymentSearch.companyId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['companyId'] ?
                         this.activatedRoute.snapshot.params['companyId'] : '';
        this.customerPaymentSearch.bankId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['bankId'] ?
                         this.activatedRoute.snapshot.params['bankId'] : '';
        this.customerPaymentSearch.userVerifyId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['userVerifyId'] ?
                         this.activatedRoute.snapshot.params['userVerifyId'] : '';
        this.customerPaymentSearch.paymentTypeId = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['paymentTypeId'] ?
                         this.activatedRoute.snapshot.params['paymentTypeId'] : '';
    }

    loadAll() {
         var obj = {
         page: this.page -1,
         size: this.itemsPerPage,
         sort: this.sort(),
         branchName : this.customerPaymentSearch.branchName,
         accountNumber : this.customerPaymentSearch.accountNumber,
         accountName : this.customerPaymentSearch.accountName,
         cardNumber : this.customerPaymentSearch.cardNumber,
         paymentAmountMoney : this.customerPaymentSearch.paymentAmountMoney,
         idImage : this.customerPaymentSearch.idImage,
         isVerify : this.customerPaymentSearch.isVerify,
         dateVerify : this.customerPaymentSearch.dateVerify,
           companyId : this.customerPaymentSearch.companyId,
           bankId : this.customerPaymentSearch.bankId,
           userVerifyId : this.customerPaymentSearch.userVerifyId,
           paymentTypeId : this.customerPaymentSearch.paymentTypeId,
         };

        this.customerPaymentService.searchExample(obj).subscribe(
          (res: HttpResponse<CustomerPayment[]>) => this.onSuccess(res.body, res.headers),
          (res: HttpErrorResponse) => this.onError(res.message)
        );

    }
    searchInForm(){
          this.page = 0;
          this.transition();

    }
    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }
    transition() {
        this.router.navigate(['/customer-management/customer-payment'], {queryParams:
            {
                page: this.page,
                size: this.itemsPerPage,
                search: this.currentSearch,
                branchName : this.customerPaymentSearch.branchName,
                accountNumber : this.customerPaymentSearch.accountNumber,
                accountName : this.customerPaymentSearch.accountName,
                cardNumber : this.customerPaymentSearch.cardNumber,
                paymentAmountMoney : this.customerPaymentSearch.paymentAmountMoney,
                idImage : this.customerPaymentSearch.idImage,
                isVerify : this.customerPaymentSearch.isVerify,
                dateVerify : this.customerPaymentSearch.dateVerify,
                companyId : this.customerPaymentSearch.companyId,
                bankId : this.customerPaymentSearch.bankId,
                userVerifyId : this.customerPaymentSearch.userVerifyId,
                paymentTypeId : this.customerPaymentSearch.paymentTypeId,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.currentSearch = '';
        this.router.navigate(['/customer-management/customer-payment', {
            page: this.page,
            sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
        }]);
        this.loadAll();
    }
    search(query) {
        if (!query) {
            return this.clear();
        }
        this.page = 0;
        this.currentSearch = query;
        this.router.navigate(['/customer-management/customer-payment', {
            search: this.currentSearch,
            page: this.page,
            sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
        }]);
        this.loadAll();
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInCustomerPayments();
        this.companyService.query().subscribe(
            (res: HttpResponse<Company[]>) => this.companies = res.body,
            (res: HttpErrorResponse) => this.onError(res.message)
        );

        this.bankService.query().subscribe(
            (res: HttpResponse<Bank[]>) => this.banks = res.body,
            (res: HttpErrorResponse) => this.onError(res.message)
        );

        this.userExtraInfoService.query().subscribe(
            (res: HttpResponse<UserExtraInfo[]>) => this.userExtraInfos = res.body,
            (res: HttpErrorResponse) => this.onError(res.message)
        );

        this.paymentTypeService.query().subscribe(
            (res: HttpResponse<PaymentType[]>) => this.paymentTypes = res.body,
            (res: HttpErrorResponse) => this.onError(res.message)
        );


    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: CustomerPayment) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    registerChangeInCustomerPayments() {
        this.eventSubscriber = this.eventManager.subscribe('customerPaymentListModification', (response) => this.loadAll());
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
        this.queryCount = this.totalItems;
        // this.page = pagingParams.page;
        this.customerPayments = data;
    }
    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }

    public deleteItem(id:number){
        this.customerPaymentPopupService
            .open(CustomerPaymentDeleteDialogComponent as Component, id);
    }
}
