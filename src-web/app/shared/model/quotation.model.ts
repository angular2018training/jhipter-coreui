import { Moment } from 'moment';
import { IQuotationItem } from 'app/shared/model//quotation-item.model';

export interface IQuotation {
    id?: number;
    name?: string;
    isActive?: boolean;
    createDate?: Moment;
    quotationItems?: IQuotationItem[];
}

export class Quotation implements IQuotation {
    constructor(
        public id?: number,
        public name?: string,
        public isActive?: boolean,
        public createDate?: Moment,
        public quotationItems?: IQuotationItem[]
    ) {
        this.isActive = false;
    }
}
