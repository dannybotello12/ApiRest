package com.ceiba.service;

import java.util.Calendar;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.entity.Registro;
import com.ceiba.entity.TipoVehiculo;
import com.ceiba.persistencia.IRegistro;
import com.ceiba.persistencia.ITipoVehiculo;
import com.ceiba.persistencia.RegistroDAO;
import com.ceiba.persistencia.TipoVehiculoDAO;

@Service
public class Salida implements ISalida {

	static long milisegundoshora = 3600000;
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

	@Override
	public TipoVehiculo valores(int tipovehiculo) {

	
			return tipoVehiculoDAO.getTipoVehiculoById(tipovehiculo);
		
	}

	@Override
	public int valortotal(Registro registro) {
		
			int valor = 0;

			Calendar calFechaInicial = Calendar.getInstance();
			Calendar calFechaFinal = Calendar.getInstance();

			TipoVehiculo tipovehiculo = valores(registro.getTipoVehiculo().getId());
			
		
				calFechaInicial.setTime(registro.getFechaIngreso());
				calFechaFinal.setTime(registro.getFechaSalida());

				long horasdiferencia = (diferenciaHorasDias(calFechaInicial, calFechaFinal));

				if (horasdiferencia != -1) {
					long horasbase = horasdiferencia;
					if(horasdiferencia==0)
					{
						return tipovehiculo.getValorhora();
					}
					
					while (horasbase > 0) {
						if (horasbase >= 9) {
							valor = valor + tipovehiculo.getValordia();
							horasbase = horasbase - 24;
						}

						else {
							valor = (int) (valor + tipovehiculo.getValorhora() * horasbase);
							horasbase = 0;
						}

					}

					if (registro.isRecargo()) {
						valor = valor + tipovehiculo.getRecargo();
					}

					return valor;

				}

				else {
					return -3; // mal calulo de horas
				}

			

			

		
	
	}

	@Override
	public String registrarsalida(Registro registro) {

		
			Calendar fechasalida = Calendar.getInstance();
			int valor = valortotal(registro);
			boolean existe = registroDAO.registroExists(registro.getPlaca());
			if ((valor >= 0) && (existe)) {
				registro.setFechaSalida(fechasalida.getTime());
				registro.setValor(valor);
				registroDAO.updateRegistro(registro);

				return "Salida registrada correctamente "+valor ;
			} else {
				return "Vehiculo No encontrado";
			}

		
	}

	public static long diferenciaHorasDias(Calendar fechaInicial, Calendar fechaFinal) {
		
			long diferenciaHoras = 0;
			diferenciaHoras = (fechaFinal.getTimeInMillis() - fechaInicial.getTimeInMillis()) / milisegundoshora;

			return diferenciaHoras;
		

	}

}