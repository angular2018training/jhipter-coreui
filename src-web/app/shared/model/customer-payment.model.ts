import { Moment } from 'moment';

export const enum PaymentType {
    ONE_PER_WEEK = 'ONE_PER_WEEK',
    TWO_PER_WEEK = 'TWO_PER_WEEK',
    THREE_PER_WEEK = 'THREE_PER_WEEK',
    DAILY = 'DAILY'
}

export interface ICustomerPayment {
    id?: number;
    branchName?: string;
    accountNumber?: string;
    accountName?: string;
    cardNumber?: string;
    isVerify?: boolean;
    dateVerify?: Moment;
    paymentType?: PaymentType;
    bankId?: number;
    userVerifyId?: number;
}

export class CustomerPayment implements ICustomerPayment {
    constructor(
        public id?: number,
        public branchName?: string,
        public accountNumber?: string,
        public accountName?: string,
        public cardNumber?: string,
        public isVerify?: boolean,
        public dateVerify?: Moment,
        public paymentType?: PaymentType,
        public bankId?: number,
        public userVerifyId?: number
    ) {
        this.isVerify = false;
    }
}
