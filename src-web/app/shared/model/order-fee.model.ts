export interface IOrderFee {
    id?: number;
    deliveryFee?: number;
    pickupFee?: number;
    codFee?: number;
    insuranceFee?: number;
    otherFee?: number;
    discount?: number;
    totalFee?: number;
    quotationId?: number;
}

export class OrderFee implements IOrderFee {
    constructor(
        public id?: number,
        public deliveryFee?: number,
        public pickupFee?: number,
        public codFee?: number,
        public insuranceFee?: number,
        public otherFee?: number,
        public discount?: number,
        public totalFee?: number,
        public quotationId?: number
    ) {}
}
