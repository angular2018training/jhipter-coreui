import { Moment } from 'moment';

export interface IOrderPickup {
    id?: number;
    address?: string;
    contactPhone?: string;
    contactName?: string;
    assignDate?: Moment;
    pickupDate?: Moment;
    wardId?: number;
    districtId?: number;
    provinceId?: number;
    pickupUserId?: number;
}

export class OrderPickup implements IOrderPickup {
    constructor(
        public id?: number,
        public address?: string,
        public contactPhone?: string,
        public contactName?: string,
        public assignDate?: Moment,
        public pickupDate?: Moment,
        public wardId?: number,
        public districtId?: number,
        public provinceId?: number,
        public pickupUserId?: number
    ) {}
}
