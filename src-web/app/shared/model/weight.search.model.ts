import { BaseEntity } from './../../shared';

export class WeightSearch implements BaseEntity {
    constructor(
    public id?: number,
    public minAmount?: number,
    public maxAmount?: number,
    public name?: string,
    public description?: string,
) {
    }
}
