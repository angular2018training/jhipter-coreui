import { BaseEntity } from './../../shared';

export class QuotationSubServicesSearch implements BaseEntity {
    constructor(
    public id?: number,
    public amountFrom?: number,
    public amountTo?: number,
    public feeAmountMin?: number,
    public feeAmountMax?: number,
    public feeAmount?: number,
    public autoSelect?: boolean,
    public companyId?: number,
    public orderSubServicesTypeId?: number,
    public orderSubServicesId?: number,
    public quotationParentId?: number,
) {
        this.autoSelect = false;
    }
}
