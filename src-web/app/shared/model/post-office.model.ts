import { BaseEntity } from './../../shared';

export class PostOffice implements BaseEntity {
    constructor(
        public id?: number,
        public code?: string,
        public name?: string,
        public address?: string,
        public sortOrder?: number,
        public phone?: string,
        public latitude?: number,
        public longitude?: number,
        public description?: string,
        public companyId?: number,
        public provinceId?: number,
        public districtId?: number,
    ) {
    }
}
