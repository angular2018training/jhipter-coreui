import { BaseEntity } from './../../shared';

export class WardSearch implements BaseEntity {
    constructor(
    public id?: number,
    public code?: string,
    public name?: string,
    public description?: string,
    public companyId?: number,
    public districtId?: number,
) {
    }
}
