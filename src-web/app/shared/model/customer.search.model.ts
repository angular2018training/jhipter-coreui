import { BaseEntity } from './../../shared';

export class CustomerSearch implements BaseEntity {
    constructor(
    public id?: number,
    public code?: string,
    public name?: string,
    public address?: string,
    public email?: string,
    public phone?: string,
    public password?: string,
    public isActive?: boolean,
    public createDate?: any,
    public lastLoginDate?: any,
    public apiToken?: string,
    public legalId?: number,
    public paymentId?: number,
    public customerPostOfficeDetailLists?: BaseEntity[],
    public customerWarehouseDetailLists?: BaseEntity[],
    public customerServicesDetailLists?: BaseEntity[],
    public companyId?: number,
    public manageUserId?: number,
    public saleUserId?: number,
    public provinceId?: number,
    public districtId?: number,
    public customerTypeId?: number,
    public customerSourceId?: number,
) {
        this.isActive = false;
    }
}
