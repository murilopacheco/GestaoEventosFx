package negocio;

import java.util.Calendar;
import java.util.Date;

import modelo.dominio.Evento;
import modelo.dominio.Usuario;

public class EventoNegocio {
	
	public String solicitarEvento(Evento evento, Usuario usuario) {
		boolean validar = false;
		validar = validarData(evento.getDataEvento());
		if(validar == true) {
			System.out.println("funciona!!!!!!");
		}
		return"";
	}
	
	public boolean validarData(Date data) {
		   Calendar cal = Calendar.getInstance();  
	        cal.add(Calendar.DAY_OF_MONTH, 30);  
	        cal.set(Calendar.HOUR_OF_DAY, 23);  
	        cal.set(Calendar.MINUTE, 59);  
	        cal.set(Calendar.SECOND, 59);  
	        cal.set(Calendar.MILLISECOND, 999);  
	        Date fim = cal.getTime();  
	          
	        return data.after(fim);  
	    }  
	}

