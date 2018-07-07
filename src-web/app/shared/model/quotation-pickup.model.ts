import { BaseEntity } from './../../shared';

export class QuotationPickup implements BaseEntity {
    constructor(
        public id?: number,
        public amount?: number,
        public companyId?: number,
        public provinceId?: number,
        public districtTypeId?: number,
        public quotationParentId?: number,
    ) {
    }
}
