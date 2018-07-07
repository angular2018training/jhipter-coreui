import { BaseEntity } from './../../shared';

export class QuotationDomesticDeliverySearch implements BaseEntity {
    constructor(
    public id?: number,
    public amount?: number,
    public companyId?: number,
    public districtTypeId?: number,
    public regionId?: number,
    public weightId?: number,
    public quotationParentId?: number,
) {
    }
}
