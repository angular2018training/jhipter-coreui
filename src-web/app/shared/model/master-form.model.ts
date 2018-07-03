import { BaseEntity } from './../../shared';

export class MasterForm implements BaseEntity {
    constructor(
        public id?: number,
        public code?: string,
        public note?: string,
        public receiveTime?: any,
        public imageFileContentType?: string,
        public imageFile?: any,
        public isActive?: boolean,
        public detailFormDetailLists?: BaseEntity[],
        public provinceId?: number,
        public districtId?: number,
    ) {
        this.isActive = false;
    }
}
