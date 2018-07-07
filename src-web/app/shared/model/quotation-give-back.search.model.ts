import { BaseEntity } from './../../shared';

export class QuotationGiveBackSearch implements BaseEntity {
    constructor(
    public id?: number,
    public feePercent?: number,
    public companyId?: number,
    public districtTypeId?: number,
    public regionId?: number,
    public quotationParentId?: number,
) {
    }
}
