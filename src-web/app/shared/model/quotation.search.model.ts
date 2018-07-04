import { BaseEntity } from './../../shared';

export class QuotationSearch implements BaseEntity {
    constructor(
    public id?: number,
    public name?: string,
    public isActive?: boolean,
    public description?: string,
    public createDate?: any,
    public activeFrom?: any,
    public quotationItemDetailLists?: BaseEntity[],
    public companyId?: number,
) {
        this.isActive = false;
    }
}
