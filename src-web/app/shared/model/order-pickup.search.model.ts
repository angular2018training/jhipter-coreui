import { BaseEntity } from './../../shared';

export class OrderPickupSearch implements BaseEntity {
    constructor(
    public id?: number,
    public address?: string,
    public contactPhone?: string,
    public contactName?: string,
    public assignDate?: any,
    public pickupDate?: any,
    public companyId?: number,
    public wardId?: number,
    public districtId?: number,
    public provinceId?: number,
    public pickupUserId?: number,
) {
    }
}
