export interface IProvince {
    id?: number;
    code?: string;
    name?: string;
    description?: string;
}

export class Province implements IProvince {
    constructor(public id?: number, public code?: string, public name?: string, public description?: string) {}
}
