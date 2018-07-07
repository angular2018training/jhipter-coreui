import { BaseEntity } from './../../shared';

export class CustomerServices implements BaseEntity {
    constructor(
        public id?: number,
        public discountPercent?: number,
        public increasePercent?: number,
        public decreasePercent?: number,
        public companyId?: number,
        public orderServicesId?: number,
        public quotationId?: number,
        public customerParentId?: number,
    ) {
        this.companyId = 1;
    }
}
