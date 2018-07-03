import { BaseEntity } from './../../shared';

export class CustomerLegalFileUploadSearch implements BaseEntity {
    constructor(
    public id?: number,
    public companyId?: number,
    public fileUploadId?: number,
    public customerLegalParentId?: number,
) {
    }
}
