export interface IOrderStatus {
    id?: number;
    code?: string;
    name?: string;
    description?: string;
}

export class OrderStatus implements IOrderStatus {
    constructor(public id?: number, public code?: string, public name?: string, public description?: string) {}
}
