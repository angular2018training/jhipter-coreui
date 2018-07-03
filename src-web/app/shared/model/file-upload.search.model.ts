import { BaseEntity } from './../../shared';

export class FileUploadSearch implements BaseEntity {
    constructor(
    public id?: number,
    public name?: string,
    public contentContentType?: string,
    public content?: any,
    public uploadTime?: any,
    public contentType?: string,
    public companyId?: number,
) {
    }
}
