package com.websystique.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websystique.springboot.dao.CustomerDao;
import com.websystique.springboot.model.Customer;

/**
 * CustomerServiceImpl. The implementation of CustomerService contains logic for
 * the customer
 *
 *
 * @param <T> the type to return.
 */

@Service
public class CustomerServiceImp implements CustomerService {

	@Autowired
	private CustomerDao customerdao;

	@Override
	public String valoraSueldo() {
		Long sueldo;
		String respuesta = "";
		String tipoMoneda = "Sol";

		sueldo = customerdao.obtenerSueldo(tipoMoneda);

		System.out.println(sueldo);
		if (sueldo > 1000) {
			respuesta = "buen sueldo";
		} else {
			respuesta = "mal sueldo";
		}
		return respuesta;
	}

	@Override
	public Customer obtenerClienteHombre() {
		Customer customer = null;
//		try {
		customer = customerdao.obtenerClienteHombre();
//		} catch (Exception e) {
//
//		}
		return customer;
	}

	@Override
	public void actualizarSueldo(Customer customer) {
		customerdao.actualizaSueldo(customer);
	}

}
