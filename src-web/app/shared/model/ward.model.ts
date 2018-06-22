export interface IWard {
    id?: number;
    code?: string;
    name?: string;
    description?: string;
    districtId?: number;
}

export class Ward implements IWard {
    constructor(public id?: number, public code?: string, public name?: string, public description?: string, public districtId?: number) {}
}
