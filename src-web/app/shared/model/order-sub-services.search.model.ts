import { BaseEntity } from './../../shared';

export class OrderSubServicesSearch implements BaseEntity {
    constructor(
    public id?: number,
    public code?: string,
    public name?: string,
    public isActive?: boolean,
    public description?: string,
    public companyId?: number,
) {
        this.isActive = false;
    }
}
