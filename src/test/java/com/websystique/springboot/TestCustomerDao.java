package com.websystique.springboot;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.websystique.springboot.dao.CustomerDao;
import com.websystique.springboot.dao.CustomerDaoImpl;
import com.websystique.springboot.model.Customer;

@ExtendWith(SpringExtension.class)
@JdbcTest
@Import(CustomerDaoImpl.class)
public class TestCustomerDao {

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Test
	void verificarSueldoInsertado() {

		jdbcTemplate.query("SELECT id, first_name, last_name FROM customers WHERE first_name = ?",
				new Object[] { "Josh" },
				(rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name")))
				.forEach(customer -> System.out.print("TEST_CUST:" + customer.toString()));
		
	}
	

	@Test
	void obtenerSueldo() {
		customerDao.obtenerSueldo("Soles");
	}
}
