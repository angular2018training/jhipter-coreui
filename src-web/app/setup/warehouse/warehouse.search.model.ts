import { BaseEntity } from './../../shared';

export class WarehouseSearch implements BaseEntity {
    constructor(
    public id?: number,
    public name?: string,
    public contactName?: string,
    public contactPhone?: string,
    public address?: string,
    public testStr?: string,
    public createDate?: any,
    public companyId?: number,
    public provinceId?: number,
    public districtId?: number,
    public wardId?: number,
) {
    }
}
