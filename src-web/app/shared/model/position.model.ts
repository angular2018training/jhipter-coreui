export interface IPosition {
    id?: number;
    code?: string;
    name?: string;
    description?: string;
}

export class Position implements IPosition {
    constructor(public id?: number, public code?: string, public name?: string, public description?: string) {}
}
