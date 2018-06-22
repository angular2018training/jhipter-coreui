export interface IRole {
    id?: number;
    code?: string;
    name?: string;
    description?: string;
}

export class Role implements IRole {
    constructor(public id?: number, public code?: string, public name?: string, public description?: string) {}
}
