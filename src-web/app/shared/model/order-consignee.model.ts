export interface IOrderConsignee {
    id?: number;
    address?: string;
    consigneePhone?: string;
    consigneeName?: string;
    districtId?: number;
    provinceId?: number;
}

export class OrderConsignee implements IOrderConsignee {
    constructor(
        public id?: number,
        public address?: string,
        public consigneePhone?: string,
        public consigneeName?: string,
        public districtId?: number,
        public provinceId?: number
    ) {}
}
