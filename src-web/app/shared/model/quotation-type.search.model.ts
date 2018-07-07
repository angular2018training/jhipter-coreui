import { BaseEntity } from './../../shared';

export class QuotationTypeSearch implements BaseEntity {
    constructor(
    public id?: number,
    public code?: string,
    public name?: string,
    public description?: string,
) {
    }
}
