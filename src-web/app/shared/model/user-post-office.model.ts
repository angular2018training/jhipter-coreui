import { BaseEntity } from './../../shared';

export class UserPostOffice implements BaseEntity {
    constructor(
        public id?: number,
        public companyId?: number,
        public postOfficeId?: number,
        public userGroups?: BaseEntity[],
        public userExtraInfoParentId?: number,
    ) {
    }
}
