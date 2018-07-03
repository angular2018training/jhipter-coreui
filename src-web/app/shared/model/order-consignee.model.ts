import { BaseEntity } from './../../shared';

export class OrderConsignee implements BaseEntity {
    constructor(
        public id?: number,
        public address?: string,
        public consigneePhone?: string,
        public consigneeName?: string,
        public companyId?: number,
        public districtId?: number,
        public provinceId?: number,
    ) {
    }
}
