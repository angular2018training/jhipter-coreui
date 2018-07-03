import { BaseEntity } from './../../shared';

export class QuotationItem implements BaseEntity {
    constructor(
        public id?: number,
        public quotationFileContentType?: string,
        public quotationFile?: any,
        public createDate?: any,
        public quotationId?: number,
        public companyId?: number,
    ) {
    }
}
