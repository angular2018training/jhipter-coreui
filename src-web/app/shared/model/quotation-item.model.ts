import { Moment } from 'moment';

export const enum QuotationItemType {
    ONE = 'ONE'
}

export interface IQuotationItem {
    id?: number;
    itemType?: QuotationItemType;
    quotationFileContentType?: string;
    quotationFile?: any;
    createDate?: Moment;
    quotationId?: number;
}

export class QuotationItem implements IQuotationItem {
    constructor(
        public id?: number,
        public itemType?: QuotationItemType,
        public quotationFileContentType?: string,
        public quotationFile?: any,
        public createDate?: Moment,
        public quotationId?: number
    ) {}
}
