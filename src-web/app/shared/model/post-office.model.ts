export interface IPostOffice {
    id?: number;
    code?: string;
    name?: string;
    description?: string;
    provinceId?: number;
}

export class PostOffice implements IPostOffice {
    constructor(public id?: number, public code?: string, public name?: string, public description?: string, public provinceId?: number) {}
}
