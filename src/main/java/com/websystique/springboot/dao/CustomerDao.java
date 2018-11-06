package com.websystique.springboot.dao;

import com.websystique.springboot.model.Customer;

public interface CustomerDao {

	public Long obtenerSueldo(String tipoMoneda);

	public void actualizaSueldo(Customer customer);

	Customer obtenerClienteHombre();
}
