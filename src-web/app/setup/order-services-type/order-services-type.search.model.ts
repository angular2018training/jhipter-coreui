import { BaseEntity } from './../../shared';

export class OrderServicesTypeSearch implements BaseEntity {
    constructor(
    public id?: number,
    public code?: string,
    public name?: string,
    public description?: string,
) {
    }
}
