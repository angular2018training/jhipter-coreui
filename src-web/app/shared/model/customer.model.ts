import { Moment } from 'moment';
import { ICustomerPostOffice } from 'app/shared/model//customer-post-office.model';

export const enum CustomerType {
    NORMAL = 'NORMAL',
    VIP = 'VIP',
    CLIENT = 'CLIENT',
    SUPPER_VIP = 'SUPPER_VIP'
}

export interface ICustomer {
    id?: number;
    code?: string;
    name?: string;
    address?: string;
    email?: string;
    phone?: string;
    password?: string;
    isActive?: boolean;
    customerType?: CustomerType;
    createDate?: Moment;
    legalId?: number;
    paymentId?: number;
    customerPostOffices?: ICustomerPostOffice[];
    manageUserId?: number;
    saleUserId?: number;
    debtUserId?: number;
    provinceId?: number;
    districtId?: number;
}

export class Customer implements ICustomer {
    constructor(
        public id?: number,
        public code?: string,
        public name?: string,
        public address?: string,
        public email?: string,
        public phone?: string,
        public password?: string,
        public isActive?: boolean,
        public customerType?: CustomerType,
        public createDate?: Moment,
        public legalId?: number,
        public paymentId?: number,
        public customerPostOffices?: ICustomerPostOffice[],
        public manageUserId?: number,
        public saleUserId?: number,
        public debtUserId?: number,
        public provinceId?: number,
        public districtId?: number
    ) {
        this.isActive = false;
    }
}
