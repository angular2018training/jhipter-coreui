export interface IOrderService {
    id?: number;
    code?: string;
    name?: string;
    description?: string;
}

export class OrderService implements IOrderService {
    constructor(public id?: number, public code?: string, public name?: string, public description?: string) { }
}
