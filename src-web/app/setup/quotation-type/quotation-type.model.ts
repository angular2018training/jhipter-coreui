import { BaseEntity } from './../../shared';

export class QuotationType implements BaseEntity {
    constructor(
        public id?: number,
        public code?: string,
        public name?: string,
        public description?: string,
    ) {
    }
}
