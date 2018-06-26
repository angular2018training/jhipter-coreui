import { BaseEntity } from './../../shared';

export class CustomerLegalSearch implements BaseEntity {
    constructor(
    public id?: number,
    public contractCustomerName?: string,
    public contractAddress?: string,
    public contractContactName?: string,
    public contractContactPhone?: string,
    public taxCode?: string,
    public contractExpirationDate?: string,
    public companyId?: number,
    public provinceId?: number,
    public districtId?: number,
) {
    }
}
