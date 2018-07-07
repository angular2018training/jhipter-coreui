package vn.nextlogix.service.dto;

import java.io.Serializable;

public class CustomerWarehouseWrapperDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private CustomerWarehouseDTO customerWarehouseDTO;
	private WarehouseDTO warehouseDTO;

	public CustomerWarehouseDTO getCustomerWarehouseDTO() {
		return customerWarehouseDTO;
	}

	public void setCustomerWarehouseDTO(CustomerWarehouseDTO customerWarehouseDTO) {
		this.customerWarehouseDTO = customerWarehouseDTO;
	}

	public WarehouseDTO getWarehouseDTO() {
		return warehouseDTO;
	}

	public void setWarehouseDTO(WarehouseDTO warehouseDTO) {
		this.warehouseDTO = warehouseDTO;
	}

}
