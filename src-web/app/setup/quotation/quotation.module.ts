import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { NextlogixSharedModule } from '../../shared';
    import {NextlogixQuotationPickupDetailModule} from '../quotation-pickup-detail/quotation-pickup-detail.module';
    import {NextlogixQuotationDomesticDeliveryDetailModule} from '../quotation-domestic-delivery-detail/quotation-domestic-delivery-detail.module';
    import {NextlogixQuotationReturnDetailModule} from '../quotation-return-detail/quotation-return-detail.module';
    import {NextlogixQuotationGiveBackDetailModule} from '../quotation-give-back-detail/quotation-give-back-detail.module';
    import {NextlogixQuotationInsuranceDetailModule} from '../quotation-insurance-detail/quotation-insurance-detail.module';
    import {NextlogixQuotationCodDetailModule} from '../quotation-cod-detail/quotation-cod-detail.module';
    import {NextlogixQuotationSubServicesDetailModule} from '../quotation-sub-services-detail/quotation-sub-services-detail.module';
import {
QuotationComponent,
    QuotationDetailComponent,
    QuotationUpdateComponent,
    QuotationDeletePopupComponent,
    QuotationDeleteDialogComponent,
    quotationRoute,
    quotationPopupRoute,
    QuotationPopupService
} from './';

const ENTITY_STATES = [
    ...quotationRoute,
    ...quotationPopupRoute,
];

@NgModule({
    imports: [
        NextlogixSharedModule,
    NextlogixQuotationPickupDetailModule,
    NextlogixQuotationDomesticDeliveryDetailModule,
    NextlogixQuotationReturnDetailModule,
    NextlogixQuotationGiveBackDetailModule,
    NextlogixQuotationInsuranceDetailModule,
    NextlogixQuotationCodDetailModule,
    NextlogixQuotationSubServicesDetailModule,
RouterModule.forChild(ENTITY_STATES)
],
declarations: [
    QuotationComponent,
    QuotationDetailComponent,
    QuotationUpdateComponent,
    QuotationDeleteDialogComponent,
    QuotationDeletePopupComponent,
],
    entryComponents: [
    QuotationComponent,
    QuotationUpdateComponent,
    QuotationDeleteDialogComponent,
    QuotationDeletePopupComponent,
],
providers: [
    QuotationPopupService,
],

schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixQuotationModule {}
