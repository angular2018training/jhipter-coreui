import { Moment } from 'moment';

export interface IFileUpload {
    id?: number;
    name?: string;
    contentContentType?: string;
    content?: any;
    uploadTime?: Moment;
    contentType?: string;
}

export class FileUpload implements IFileUpload {
    constructor(
        public id?: number,
        public name?: string,
        public contentContentType?: string,
        public content?: any,
        public uploadTime?: Moment,
        public contentType?: string
    ) {}
}
