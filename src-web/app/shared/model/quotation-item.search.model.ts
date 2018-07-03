import { BaseEntity } from './../../shared';

export class QuotationItemSearch implements BaseEntity {
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
