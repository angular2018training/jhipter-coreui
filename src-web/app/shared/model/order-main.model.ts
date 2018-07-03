import { BaseEntity } from './../../shared';

export class OrderMain implements BaseEntity {
    constructor(
        public id?: number,
        public orderNumber?: string,
        public createDate?: any,
        public sendDate?: any,
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
        public companyId?: number,
        public createUserId?: number,
        public orderStatusId?: number,
        public customerId?: number,
        public mainServiceId?: number,
        public createPostOfficeId?: number,
        public currentPostOfficeId?: number,
    ) {
    }
}
