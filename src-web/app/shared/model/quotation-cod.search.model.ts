import { BaseEntity } from './../../shared';

export class QuotationCodSearch implements BaseEntity {
    constructor(
    public id?: number,
    public amountFrom?: number,
    public amountTo?: number,
    public feeAmountMin?: number,
    public feeAmount?: number,
    public companyId?: number,
    public districtTypeId?: number,
    public quotationParentId?: number,
) {
    }
}
