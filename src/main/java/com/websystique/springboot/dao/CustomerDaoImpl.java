package com.websystique.springboot.dao;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.websystique.springboot.model.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Long obtenerSueldo(String tipoMoneda) {
		// TODO Auto-generated method stub

		System.out.print("Creating tables");

		jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE customers(" + "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

		// Split up the array of whole names into an array of first/last names
		List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
				.map(name -> name.split(" ")).collect(Collectors.toList());

		// Use a Java 8 stream to print out each tuple of the list
		splitUpNames.forEach(
				name -> System.out.print(String.format("Inserting customer record for %s %s", name[0], name[1])));

		// Uses JdbcTemplate's batchUpdate operation to bulk load data
		jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);

//		System.out.print("Querying for customer records where first_name = 'Josh':");
//		jdbcTemplate.query("SELECT id, first_name, last_name FROM customers WHERE first_name = ?",
//				new Object[] { "Josh" },
//				(rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name")))
//				.forEach(customer -> System.out.print(customer.toString()));
		return (long) 999.0;
	}
	
	@Override
	public Customer obtenerClienteHombre() {
		return null;
	}

	@Override
	public void actualizaSueldo(Customer customer) {
		System.out.println("entro al Dao CustomerDaoImp actualizaSueldo");
	}
}
