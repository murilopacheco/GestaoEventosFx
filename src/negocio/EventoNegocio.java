package negocio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import modelo.dao.EventoDao;
import modelo.dao.ParticipantesDao;
import modelo.dominio.Evento;
import modelo.dominio.Participantes;
import modelo.dominio.Usuario;

public class EventoNegocio {
	
	public String solicitarEvento(Evento evento, Usuario usuario) throws SQLException {
		String salvo = "";
		boolean validar = false;
		validar = validarData(evento.getDataEvento());
		if(validar == true) {
			EventoDao eventoDao = new EventoDao();
			salvo = eventoDao.solicitarEvento(usuario, evento);
		}
		return salvo;
	}
	
	public List<Evento> listarEventos(){
		EventoDao dao = new EventoDao();
		try {
			return dao.listarEventos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
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
	public String participarEvento(Usuario usuario, int idEvento) {
		boolean valido = false;
		StringBuilder sb = new StringBuilder();
		valido = validarCodigoDigitado(idEvento);
		if(!valido) {
			sb.append("escolha um evento válido na lista apresentada");
		}else {
			ParticipantesDao participantesDao = new ParticipantesDao();
			Participantes participantes = new Participantes();
			participantes.setIdUsuario(usuario.getId());
			participantes.setIdEvento(idEvento);
			participantes.setQuantidadeIngressos(1);
			participantes.setDataCompra(new Date());
			
			try {
				
				sb.append(participantesDao.salvarParticipacao(participantes));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return sb.toString();
	}
	
	public boolean validarCodigoDigitado(int idEvento) {
		boolean valido = false;	
		List<Evento> eventos = listarEventos();
			for (Iterator iterator = eventos.iterator(); iterator.hasNext();) {
				Evento evento = (Evento) iterator.next();
				if(idEvento == evento.getId()) {
					valido = true;
				}else {
					valido = false;
				}
			}
			return valido;
	}
	
	}



