package com.websystique.springboot.service;

import com.websystique.springboot.model.Customer;

public interface CustomerService {
	
	public String valoraSueldo();
	
	public Customer obtenerClienteHombre();

	void actualizarSueldo(Customer customer);
}
