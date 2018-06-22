export interface IUserPosition {
    id?: number;
    postOfficeId?: number;
    positionId?: number;
    userExtraInfoId?: number;
}

export class UserPosition implements IUserPosition {
    constructor(public id?: number, public postOfficeId?: number, public positionId?: number, public userExtraInfoId?: number) {}
}
