export interface IDistrict {
    id?: number;
    code?: string;
    name?: string;
    description?: string;
    provinceId?: number;
}

export class District implements IDistrict {
    constructor(public id?: number, public code?: string, public name?: string, public description?: string, public provinceId?: number) {}
}
