import { BaseEntity } from './../../shared';

export class CustomerPostOffice implements BaseEntity {
    constructor(
        public id?: number,
        public code?: string,
        public isActive?: boolean,
        public createDate?: any,
        public companyId?: number,
        public postOfficeId?: number,
        public customerParentId?: number,
        public postOfficeDTO?: any
    ) {
        this.isActive = false;
        this.companyId = 1;
    }
}
