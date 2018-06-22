import { Moment } from 'moment';

export interface IOrderDelivery {
    id?: number;
    receiver?: string;
    note?: string;
    receiveTime?: Moment;
    createDate?: Moment;
    createUserId?: number;
    orderStatusId?: number;
}

export class OrderDelivery implements IOrderDelivery {
    constructor(
        public id?: number,
        public receiver?: string,
        public note?: string,
        public receiveTime?: Moment,
        public createDate?: Moment,
        public createUserId?: number,
        public orderStatusId?: number
    ) {}
}
