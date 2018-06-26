import { BaseEntity } from './../../shared';

export class CustomerPostOffice implements BaseEntity {
    constructor(
        public id?: number,
        public code?: string,
        public isActive?: boolean,
        public createDate?: any,
        public customerId?: number,
        public companyId?: number,
        public postOfficeId?: number,
    ) {
        this.isActive = false;
    }
}
