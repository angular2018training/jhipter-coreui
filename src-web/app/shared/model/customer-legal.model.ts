import { IFileUpload } from 'app/shared/model//file-upload.model';

export interface ICustomerLegal {
    id?: number;
    contractCustomerName?: string;
    contractContactName?: string;
    contractContactPhone?: string;
    taxCode?: string;
    contractExpirationDate?: string;
    fileUploads?: IFileUpload[];
}

export class CustomerLegal implements ICustomerLegal {
    constructor(
        public id?: number,
        public contractCustomerName?: string,
        public contractContactName?: string,
        public contractContactPhone?: string,
        public taxCode?: string,
        public contractExpirationDate?: string,
        public fileUploads?: IFileUpload[]
    ) {}
}
