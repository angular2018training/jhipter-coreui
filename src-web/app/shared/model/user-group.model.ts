import { IRole } from 'app/shared/model//role.model';

export interface IUserGroup {
    id?: number;
    code?: string;
    name?: string;
    description?: string;
    roles?: IRole[];
}

export class UserGroup implements IUserGroup {
    constructor(public id?: number, public code?: string, public name?: string, public description?: string, public roles?: IRole[]) {}
}
