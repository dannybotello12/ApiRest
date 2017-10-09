package com.ceiba.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ceiba.entity.Registro;
import com.ceiba.entity.TipoVehiculo;
import com.ceiba.persistencia.RegistroDAO;
import com.ceiba.persistencia.TipoVehiculoDAO;

public class ConsultaTests {
	
	
	private Consulta consulta;
	private RegistroDAO registroDAO; // we will be mocking this class
	private TipoVehiculoDAO tipoVehiculoDAO;

	@Before
	public void setup() {
		registroDAO = mock(RegistroDAO.class); 	
		tipoVehiculoDAO = mock(TipoVehiculoDAO.class); 	
		consulta = new Consulta();
		consulta.setRegistroDAO(registroDAO);
		consulta.setTipoVehiculoDAO(tipoVehiculoDAO);

	}

	
	

	@Test
	 public void Allregistrostest() throws Throwable {
	 List<Registro> all = new ArrayList<>();
	 
	 DateFormat df = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
	
			Date	date = df.parse("28/09/2017 15:30:00");
		

		Registro registro = new Registro();
		registro.setPlaca("ajy56b");
		registro.setRecargo(false);
		registro.setTipoVehiculo(new TipoVehiculo(1, "", 20, 0, 500, 8000));
		registro.setValor(0);
		registro.setFechaIngreso(date);
	 
	 
	 all.add(registro);

	 
	 //MOCK ALERT: return mocked result set on find
	 when(registroDAO.getAllRegistro()).thenReturn(all); 
	 
	 //call the main method you want to test
	 consulta.allRegistro();
	 
	 //MOCK ALERT: verify the method was called
	 verify(registroDAO).getAllRegistro(); 
	 }
	@Test
	public void consultarRegistroPlacatest() throws Throwable {
		
		 DateFormat df = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
			
				Date	date = df.parse("28/09/2017 15:30:00");
		

			Registro registro = new Registro();
			registro.setPlaca("ajy56b");
			registro.setRecargo(false);
			registro.setTipoVehiculo(new TipoVehiculo(1, "", 20, 0, 500, 8000));
			registro.setValor(0);
			registro.setFechaIngreso(date);
		
		 when(registroDAO.getRegistroByPlaca(registro.getPlaca())).thenReturn(registro);
		 
		 
		 Registro registro2= consulta.consultarregistroplaca("ajy56b");
		 
		 
		 
		 assertEquals(registro, registro2);
	}
	
	
	@Test
	 public void Allvehiculotest() throws Throwable {
	 List<TipoVehiculo> all = new ArrayList<>();
	 
	TipoVehiculo vehiculo= new TipoVehiculo(1, "moto", 20, 0, 500, 8000);
		
	 
	 
	 all.add(vehiculo);

	 
	
	 when(tipoVehiculoDAO.getAllTipoVehiculo()).thenReturn(all); 
	 
	 
	 consulta.allVehiculos();
	 
	 //MOCK ALERT: verify the method was called
	 verify(tipoVehiculoDAO).getAllTipoVehiculo(); 
	 }
	

}
