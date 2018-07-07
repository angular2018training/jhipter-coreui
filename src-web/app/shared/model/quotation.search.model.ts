import { BaseEntity } from './../../shared';

export class QuotationSearch implements BaseEntity {
    constructor(
    public id?: number,
    public name?: string,
    public isActive?: boolean,
    public description?: string,
    public createDate?: any,
    public activeFrom?: any,
    public quotationPickupDetailLists?: BaseEntity[],
    public quotationDomesticDeliveryDetailLists?: BaseEntity[],
    public quotationReturnDetailLists?: BaseEntity[],
    public quotationGiveBackDetailLists?: BaseEntity[],
    public quotationInsuranceDetailLists?: BaseEntity[],
    public quotationCodDetailLists?: BaseEntity[],
    public quotationSubServicesDetailLists?: BaseEntity[],
    public companyId?: number,
) {
        this.isActive = false;
    }
}
