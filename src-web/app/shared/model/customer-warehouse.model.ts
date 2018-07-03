import { BaseEntity } from './../../shared';

export class CustomerWarehouse implements BaseEntity {
    constructor(
        public id?: number,
        public companyId?: number,
        public warehouseId?: number,
        public customerParentId?: number,
    ) {
    }
}
