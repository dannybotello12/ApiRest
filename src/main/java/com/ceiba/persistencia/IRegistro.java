package com.ceiba.persistencia;

import java.util.List;

import com.ceiba.entity.Registro;

public interface IRegistro {
	
	    List<Registro> getAllRegistro();
	    Registro getRegistroById(int registroId);
	    Registro getRegistroByPlaca(String placa);
	    int getRegistroBytipoVehiculoActivo(int tipoVehiculoID);
	    void addRegistro(Registro registro);
	    String updateRegistro(Registro registro);
	    void deleteRegistro(int registroId);
	    boolean registroExists(String placa);

}
