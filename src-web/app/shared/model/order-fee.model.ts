import { BaseEntity } from './../../shared';

export class OrderFee implements BaseEntity {
    constructor(
        public id?: number,
        public deliveryFee?: number,
        public pickupFee?: number,
        public codFee?: number,
        public insuranceFee?: number,
        public otherFee?: number,
        public discount?: number,
        public totalFee?: number,
        public companyId?: number,
        public quotationId?: number,
    ) {
    }
}
