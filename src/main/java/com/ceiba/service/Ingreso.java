package com.ceiba.service;

import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.entity.Registro;
import com.ceiba.entity.Restriccion;
import com.ceiba.entity.TipoVehiculo;
import com.ceiba.persistencia.IRegistro;
import com.ceiba.persistencia.IRestriccion;
import com.ceiba.persistencia.ITipoVehiculo;
import com.ceiba.persistencia.RegistroDAO;
import com.ceiba.persistencia.RestriccionDAO;
import com.ceiba.persistencia.TipoVehiculoDAO;

@Service
public class Ingreso implements IIngreso {

	static Logger LOGGER = Logger.getLogger(Salida.class.getName());

	private ITipoVehiculo tipoVehiculoDAO;

	@Autowired
	public void setTipoVehiculoDAO(TipoVehiculoDAO tipoVehiculoDAO) {
		this.tipoVehiculoDAO = tipoVehiculoDAO;
	}

	private IRegistro registroDAO;

	@Autowired
	public void setRegistroDAO(RegistroDAO registroDAO) {
		this.registroDAO = registroDAO;
	}

	private IRestriccion restriccionDAO;

	@Autowired
	public void setrestriccionDAO(RestriccionDAO restriccionDAO) {
		this.restriccionDAO = restriccionDAO;
	}

	@Override
	public int diaSemana(Date fecha) {

		
			Calendar cal = Calendar.getInstance();
			cal.setTime(fecha);
			return cal.get(Calendar.DAY_OF_WEEK);
		
	}

	@Override
	public String letraPlaca(String placa) {
		return placa.substring(0, 1);
	}

	@Override
	public String diasrestriccionplaca(String placa) {

		

			Restriccion restricion = restriccionDAO.getRestriccionByletra(letraPlaca(placa));
			if (restricion == null) {
				return "";
			} else {
				return restricion.getDias();
			}
		

		

	}

	@Override
	public boolean accesovalido(Registro registro) {
       
	
		boolean acceso = true;

		if ((registro.getPlaca() != null) && (diasrestriccionplaca(registro.getPlaca()) != "")) {
			String[] palabras = diasrestriccionplaca(registro.getPlaca()).split(",");

			String cadenaDondeBuscar = String.valueOf(diaSemana(registro.getFechaIngreso()));

			for (String palabra : palabras) {
				if (cadenaDondeBuscar.contains(palabra)) {

					acceso = false;
					return acceso;

				}

			}

		}
		return acceso;
	
	}

	@Override
	public boolean espaciodisponible(int tipoVehiculo) {

	

			TipoVehiculo tipovehiculo = tipoVehiculoDAO.getTipoVehiculoById(tipoVehiculo);
			int espaciosocupados = registroDAO.getRegistroBytipoVehiculoActivo(tipoVehiculo);
			return (tipovehiculo.getStock() - espaciosocupados > 0) ? true : false;

		
	}

	@Override
	public String registroEntrada(Registro registro) {
     
		if (espaciodisponible(registro.getTipoVehiculo().getId())) {
			if (registroDAO.registroExists(registro.getPlaca()))

			{
				return "Vehiculo ya ingreso";
			}

			if (!accesovalido(registro)) {
				return "Vehiculo con restricion de dia";
			}

			registroDAO.addRegistro(registro);

			return "Vehiculo ingresado correctamente";
		}

		else {
			return "Sin espacio para el vehiculo";
		}

	
		
	

	
	}
}
