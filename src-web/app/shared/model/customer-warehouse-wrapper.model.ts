import { CustomerWarehouse } from './customer-warehouse.model';
import { Warehouse } from './warehouse.model';

export class CustomerWarehouseWrapperModel {
    constructor(
        public customerWarehouseDTO?: CustomerWarehouse,
        public warehouseDTO?: Warehouse,
    ) { }
}
