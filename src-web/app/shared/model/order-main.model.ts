import { Moment } from 'moment';

export interface IOrderMain {
    id?: number;
    orderNumber?: string;
    createDate?: Moment;
    sendDate?: Moment;
    weight?: number;
    width?: number;
    height?: number;
    depth?: number;
    quantityItem?: number;
    orderPrice?: number;
    finalWeight?: number;
    customerOrderNumber?: string;
    codAmount?: number;
    orderPickupId?: number;
    orderConsigneeId?: number;
    orderFeeId?: number;
    orderDeliveryId?: number;
    createUserId?: number;
    orderStatusId?: number;
    customerId?: number;
    mainServiceId?: number;
    createPostOfficeId?: number;
    currentPostOfficeId?: number;
}

export class OrderMain implements IOrderMain {
    constructor(
        public id?: number,
        public orderNumber?: string,
        public createDate?: Moment,
        public sendDate?: Moment,
        public weight?: number,
        public width?: number,
        public height?: number,
        public depth?: number,
        public quantityItem?: number,
        public orderPrice?: number,
        public finalWeight?: number,
        public customerOrderNumber?: string,
        public codAmount?: number,
        public orderPickupId?: number,
        public orderConsigneeId?: number,
        public orderFeeId?: number,
        public orderDeliveryId?: number,
        public createUserId?: number,
        public orderStatusId?: number,
        public customerId?: number,
        public mainServiceId?: number,
        public createPostOfficeId?: number,
        public currentPostOfficeId?: number
    ) {}
}
