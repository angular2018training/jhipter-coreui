import { BaseEntity } from './../../shared';

export class CompanySearch implements BaseEntity {
    constructor(
    public id?: number,
    public code?: string,
    public name?: string,
    public address?: string,
    public phone?: string,
    public email?: string,
    public website?: string,
    public description?: string,
) {
    }
}
