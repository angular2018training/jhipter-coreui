import { BaseEntity } from './../../shared';

export class OrderServices implements BaseEntity {
    constructor(
        public id?: number,
        public code?: string,
        public name?: string,
        public description?: string,
        public companyId?: number,
        public orderServicesTypeId?: number,
        public quotations?: BaseEntity[],
    ) {
    }
}
