import { BaseEntity } from './../../shared';

export class OrderUserRoute implements BaseEntity {
    constructor(
        public id?: number,
        public companyId?: number,
        public userId?: number,
        public provinceId?: number,
        public districtId?: number,
        public orderUserRouteTypeId?: number,
        public wardId?: number,
        public customerId?: number,
    ) {
    }
}
