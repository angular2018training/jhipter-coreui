export interface IBank {
    id?: number;
    code?: string;
    name?: string;
    description?: string;
}

export class Bank implements IBank {
    constructor(public id?: number, public code?: string, public name?: string, public description?: string) {}
}
