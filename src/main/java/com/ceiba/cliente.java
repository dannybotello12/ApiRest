package com.ceiba;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.ceiba.entity.Registro;
import com.ceiba.entity.TipoVehiculo;

public class cliente {
	
	public void getAllArticlesDemo() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		    RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/user/articles";
		    HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		    ResponseEntity<Registro[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Registro[].class);
		    Registro[] articles = responseEntity.getBody();
		    for(Registro article : articles) {
		          System.out.println("Id:"+article.getId()+", Title:"+article.getPlaca()
		                  +", Category: "+article.getTipoVehiculo().getDescripcion());
		    }
		    
		    
		    
		     	
		    }   
	
	
	 public void addregistro() {
	    	HttpHeaders headers = new HttpHeaders();
	    	headers.setContentType(MediaType.APPLICATION_JSON);
	        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/user/registro";
	    Registro objRegistro = new Registro();
	    TipoVehiculo objTipoVehiculo=new TipoVehiculo();
	    Date fecha = new Date();
	  
	    
	    objRegistro.setFechaIngreso(fecha);
	    objRegistro.setPlaca("adfxz21");
	    objRegistro.setValor(0);
	    objRegistro.setRecargo(false);
	    
	    objTipoVehiculo.setId(1);
	    objRegistro.setTipoVehiculo(objTipoVehiculo);
	        HttpEntity<Registro> requestEntity = new HttpEntity<Registro>(objRegistro, headers);
	        URI uri = restTemplate.postForLocation(url, requestEntity);
	       
	        
		    
		   
}
	 
	 
	 
	 
	 
	 public void updateRegistrosalida() {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
		    RestTemplate restTemplate = new RestTemplate();
		    String url = "http://localhost:8080/user/registro";
		    Registro objRegistro = new Registro();
		    TipoVehiculo objTipoVehiculo=new TipoVehiculo();
		    
		    DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = null;
			Date date2 = null;
			try {
				date = df.parse("28/09/2017 15:30:00");
				date2 = df.parse("28/09/2017 17:30:00");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    objRegistro.setFechaIngreso(date);
		    objRegistro.setPlaca("adfxz21");
		    objRegistro.setValor(0);
		    objRegistro.setRecargo(false);
		    objRegistro.setId(5);
		    objTipoVehiculo.setId(1);
		    objRegistro.setTipoVehiculo(objTipoVehiculo); 
		 
		    HttpEntity<Registro> requestEntity = new HttpEntity<Registro>(objRegistro, headers);
		    restTemplate.put(url, requestEntity);
		}
	
	 public static void main(String args[]) {
		 cliente util = new cliente();
			//util.addregistro();
			util.updateRegistrosalida();
	    	
		
			
		
			  //util.getArticleByIdDemo();
	    	//util.addArticleDemo();
	    	//util.getAllArticlesDemo();
	    	//util.updateArticleDemo();
	    	//util.deleteArticleDemo();
	    }  
	 
}

