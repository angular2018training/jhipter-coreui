import { BaseEntity } from './../../shared';

export class UserPositionSearch implements BaseEntity {
    constructor(
    public id?: number,
    public companyId?: number,
    public postOfficeId?: number,
    public positionId?: number,
    public userGroups?: BaseEntity[],
    public userExtraInfoId?: number,
) {
    }
}
