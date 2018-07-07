import { BaseEntity } from './../../shared';

export class DistrictSearch implements BaseEntity {
    constructor(
    public id?: number,
    public code?: string,
    public name?: string,
    public pickupActive?: boolean,
    public deliveryActive?: boolean,
    public description?: string,
    public companyId?: number,
    public provinceId?: number,
    public districtTypeId?: number,
) {
        this.pickupActive = false;
        this.deliveryActive = false;
    }
}
