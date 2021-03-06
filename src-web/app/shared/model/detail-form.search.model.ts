import { BaseEntity } from './../../shared';

export class DetailFormSearch implements BaseEntity {
    constructor(
    public id?: number,
    public description?: string,
    public createDate?: any,
    public uploadFileContentType?: string,
    public uploadFile?: any,
    public isActive?: boolean,
    public masterFormId?: number,
    public provinceId?: number,
    public districtId?: number,
) {
        this.isActive = false;
    }
}
