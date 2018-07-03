import { BaseEntity } from './../../shared';

export class OrderDeliverySearch implements BaseEntity {
    constructor(
    public id?: number,
    public receiver?: string,
    public note?: string,
    public receiveTime?: any,
    public createDate?: any,
    public companyId?: number,
    public createUserId?: number,
    public orderStatusId?: number,
) {
    }
}
