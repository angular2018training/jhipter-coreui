import { BaseEntity } from './../../shared';

export class CustomerPayment implements BaseEntity {
    constructor(
        public id?: number,
        public branchName?: string,
        public accountNumber?: string,
        public accountName?: string,
        public cardNumber?: string,
        public paymentAmountMoney?: number,
        public idImageContentType?: string,
        public idImage?: any,
        public isVerify?: boolean,
        public dateVerify?: any,
        public companyId?: number,
        public bankId?: number,
        public userVerifyId?: number,
        public paymentTypeId?: number,
    ) {
        this.isVerify = false;
        this.companyId = 1;
    }
}
