import { BaseEntity } from './../../shared';

export class CustomerLegal implements BaseEntity {
    constructor(
        public id?: number,
        public contractCustomerName?: string,
        public contractAddress?: string,
        public contractContactName?: string,
        public contractContactPhone?: string,
        public taxCode?: string,
        public contractExpirationDate?: string,
        public customerLegalFileUploadDetailLists?: BaseEntity[],
        public companyId?: number,
        public provinceId?: number,
        public districtId?: number,
        public customerId?: number,
    ) {
        this.companyId = 1;
    }
}
