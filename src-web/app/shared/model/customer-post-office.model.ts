import { Moment } from 'moment';

export interface ICustomerPostOffice {
    id?: number;
    code?: string;
    createDate?: Moment;
    customerId?: number;
    postOfficeId?: number;
}

export class CustomerPostOffice implements ICustomerPostOffice {
    constructor(
        public id?: number,
        public code?: string,
        public createDate?: Moment,
        public customerId?: number,
        public postOfficeId?: number
    ) {}
}
