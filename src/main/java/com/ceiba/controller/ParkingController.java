package com.ceiba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.ceiba.entity.Registro;
import com.ceiba.response.RespuestaRest;
import com.ceiba.service.IConsulta;
import com.ceiba.service.IIngreso;
import com.ceiba.service.ISalida;
@Controller
@RequestMapping("user")

public class ParkingController {
	
	
	private IIngreso ingreso;
	@Autowired
	public void setingreso(IIngreso ingreso) {
		this.ingreso = ingreso;
	}
	
	private ISalida salida;
	@Autowired
	public void setsalida(ISalida salida) {
		this.salida = salida;
	}
	
	private IConsulta consulta;
	@Autowired
	public void setconsulta(IConsulta consulta) {
		this.consulta = consulta;
	}
		
	    @GetMapping("registro")
		public ResponseEntity<Registro> getRegistroByPlaca(@RequestParam ("placa") String placa) {
			Registro registro = consulta.consultarregistroplaca(placa);
			return new ResponseEntity<>(registro, HttpStatus.OK);
		}
		
	    @GetMapping("consulta")
		public ResponseEntity<List<Registro>> getAllregistros() {
			List<Registro> list = consulta.allRegistro();
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
		
		
		
		@PostMapping("registro")
		public ResponseEntity<RespuestaRest> addRegistro(@RequestBody Registro registro) {
	                String flag = ingreso.registroEntrada(registro);
	                RespuestaRest respuesta=new RespuestaRest();
	                if (flag!="Vehiculo ingresado correctamente") {
	                	respuesta.setNumero(1);
	    				respuesta.setMensaje(flag);
	        	    return new ResponseEntity<>(respuesta,HttpStatus.CONFLICT);
	                }
	                respuesta.setNumero(0);
	    			respuesta.setMensaje(flag);
	                return new ResponseEntity<>(respuesta,HttpStatus.OK);
		}
		
		@PutMapping("registro")
		public ResponseEntity<RespuestaRest> updateregistro(@RequestBody Registro registro) {
			String flag = salida.registrarsalida(registro);
			RespuestaRest respuesta=new RespuestaRest();
			
			if(flag!="Salida registrada correctamente")
			{
				respuesta.setNumero(1);
				respuesta.setMensaje(flag);
				return new ResponseEntity<>(respuesta,HttpStatus.CONFLICT);
			}
			respuesta.setNumero(0);
			respuesta.setMensaje(flag);
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		}
	

}
