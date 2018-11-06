package com.websystique.springboot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.websystique.springboot.dao.CustomerDaoImpl;
import com.websystique.springboot.model.Customer;
import com.websystique.springboot.service.CustomerServiceImp;

@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, classes = SpringBootRestApiApp.class)
public class TestCustomerCaseRet {

	@Mock
	private CustomerDaoImpl customerDaoDummy;

	@InjectMocks
	private CustomerServiceImp customerServiceImp;

	@Test
	public void laValoracionEsBuenSueldo() {

//		Mockito.when(customerDaoDummy.obtenerSueldo(Mockito.any(String.class))).thenReturn((long) 1001.0);
//		Mockito.when(customerDaoDummy.obtenerSueldo(Mockito.any(String.class))).thenAnswer(new Answer<Long>() {
//			@Override
//			public Long answer(InvocationOnMock invocation) throws Throwable {
//				Object[] arguments = invocation.getArguments();
//				String tipomoneda = (String) arguments[0];
//				System.out.println("tipomoneda");
//				System.out.println(tipomoneda);
//				return (long) 1002.0;
//			}
//		});

		Mockito.when(customerDaoDummy.obtenerSueldo(Mockito.any(String.class))).thenAnswer((invocation) -> {
			Object[] arguments = invocation.getArguments();
			String tipomoneda = (String) arguments[0];
			System.out.println("tipomoneda");
			System.out.println(tipomoneda);
			return (long) 1002.0;
		});

		String valoracionSueldo = customerServiceImp.valoraSueldo();
		Assertions.assertEquals("buen sueldo", valoracionSueldo);

	}

	@Test
	public void elGeneroEsHombreOMujer() {
		String genero = null;
		Customer customerDummy = new Customer(1, "Juan", "Melano", "Hombre", 200);
		Mockito.when(customerDaoDummy.obtenerClienteHombre()).thenReturn(customerDummy);

		Customer customer = customerServiceImp.obtenerClienteHombre();
		genero = customer.getGenero();
		Assertions.assertTrue(genero.equals("Hombre") || genero.equals("Mujer"));
	}

	@Test
	public void siObtenerClienteHombreDaoLanzaExcepcionEntoncesServiceLanzaExcepcion() {
		Mockito.when(customerDaoDummy.obtenerClienteHombre()).thenThrow(RuntimeException.class);

		Assertions.assertThrows(RuntimeException.class, () -> {
			Customer customer = customerServiceImp.obtenerClienteHombre();
		});
	}

	@Test
	public void actualizaSueldosServiceSinErrores() {
		Customer customer = new Customer(1, "Juanito", "Perez", "Hombre", 1999.0);
//		Mockito.doAnswer(invocation -> {
//			Object[] arguments = invocation.getArguments();
//			if (arguments != null && arguments.length > 1 && arguments[0] != null && arguments[1] != null) {
//
//				Customer customerInt = (Customer) arguments[0];
//				customerInt.setMontoPago(sueldoNuevo);
//
//			}
//			return null;
//		}).when(customerDaoDummy).actualizaSueldo(customer);

		Mockito.doNothing().when(customerDaoDummy).actualizaSueldo(customer);
        
		customerServiceImp.actualizarSueldo(customer);
	    Mockito.verify(customerDaoDummy, Mockito.times(1)).actualizaSueldo(customer);
	    
//		Mockito.verify(customerServiceImp.actualizarSueldo(customer));
//		Assertions.assertNotEquals(2000.0,customer.getMontoPago());
	}
}