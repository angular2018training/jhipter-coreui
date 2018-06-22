import { IUserPosition } from 'app/shared/model//user-position.model';
import { IRole } from 'app/shared/model//role.model';
import { IUserGroup } from 'app/shared/model//user-group.model';

export interface IUserExtraInfo {
    id?: number;
    email?: string;
    phone?: string;
    address?: string;
    userPositions?: IUserPosition[];
    roles?: IRole[];
    userGroups?: IUserGroup[];
}

export class UserExtraInfo implements IUserExtraInfo {
    constructor(
        public id?: number,
        public email?: string,
        public phone?: string,
        public address?: string,
        public userPositions?: IUserPosition[],
        public roles?: IRole[],
        public userGroups?: IUserGroup[]
    ) {}
}
