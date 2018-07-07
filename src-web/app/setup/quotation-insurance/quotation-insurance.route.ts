import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
    import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { QuotationInsurance } from '../../shared/model/quotation-insurance.model';
import { QuotationInsuranceService } from '../../shared/service/quotation-insurance.service';
import { QuotationInsuranceComponent } from './quotation-insurance.component';
import { QuotationInsuranceDetailComponent } from './quotation-insurance-detail.component';
import { QuotationInsuranceUpdateComponent } from './quotation-insurance-update.component';
    import { QuotationInsuranceDeletePopupComponent } from './quotation-insurance-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class QuotationInsuranceResolve implements Resolve<QuotationInsurance> {

        constructor(private service: QuotationInsuranceService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((quotationInsurance: HttpResponse<QuotationInsurance>) => quotationInsurance.body);
    }
    return Observable.of(new QuotationInsurance());
}
}


export const quotationInsuranceRoute: Routes = [
    {
        path: 'quotation-insurance',
        component: QuotationInsuranceComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.quotationInsurance.home.title',
    title : 'nextlogixApp.quotationInsurance.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'quotation-insurance/:id/view',
        component: QuotationInsuranceDetailComponent,
        resolve: {
        quotationInsurance: QuotationInsuranceResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.quotationInsurance.home.title',
        title: 'nextlogixApp.quotationInsurance.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'quotation-insurance/new',
        component: QuotationInsuranceUpdateComponent,
    resolve: {
    quotationInsurance: QuotationInsuranceResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.quotationInsurance.home.title',
        title: 'nextlogixApp.quotationInsurance.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'quotation-insurance/:id/edit',
        component: QuotationInsuranceUpdateComponent,
    resolve: {
    quotationInsurance: QuotationInsuranceResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.quotationInsurance.home.title',
        title: 'nextlogixApp.quotationInsurance.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const quotationInsurancePopupRoute: Routes = [
    {
        path: 'quotation-insurance/:id/delete',
        component: QuotationInsuranceDeletePopupComponent,
        resolve: {
        quotationInsurance: QuotationInsuranceResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.quotationInsurance.home.title',
    title: 'nextlogixApp.quotationInsurance.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
