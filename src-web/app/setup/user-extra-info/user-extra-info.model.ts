import { BaseEntity } from './../../shared';

export class UserExtraInfo implements BaseEntity {
    constructor(
        public id?: number,
        public email?: string,
        public phone?: string,
        public address?: string,
        public validDate?: any,
        public lastLoginDate?: any,
        public contractFileContentType?: string,
        public contractFile?: any,
        public contractExpirationDate?: any,
        public userPositions?: BaseEntity[],
        public companyId?: number,
    ) {
    }
}
