import { BaseEntity } from './../../shared';

export class QuotationInsuranceSearch implements BaseEntity {
    constructor(
    public id?: number,
    public amountFrom?: number,
    public amountTo?: number,
    public feeAmountMin?: number,
    public feeAmountMax?: number,
    public feePercent?: number,
    public companyId?: number,
    public districtTypeId?: number,
    public quotationParentId?: number,
) {
    }
}
