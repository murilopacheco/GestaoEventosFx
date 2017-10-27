package visao.controle;

import java.awt.HeadlessException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.dominio.Evento;
import modelo.dominio.Usuario;
import negocio.EventoNegocio;

public class EventoControle {
	
	private Evento evento;
	private List<Evento> eventos;
	Usuario usuario;
	
	public void solicitarEvento(Usuario usuario) throws HeadlessException, ParseException {
		if(usuario != null) {
		String salvo = "";
		evento = new Evento();
		evento.setNome(JOptionPane.showInputDialog
		("Digite o nome do evento"));
		evento.setVagas(Integer.parseInt((JOptionPane.showInputDialog
				("Digite o numero de vagas"))));
		evento.setCategoria(JOptionPane.showInputDialog
		("Digite a categoria do evento(Ex:show, palestra...)"));
		evento.setValorIngresso(Double.parseDouble((JOptionPane.showInputDialog
				("Digite o valor do ingresso"))));
		evento.setStatus("cadastrado");
		String x=(JOptionPane.showInputDialog("Digite a data do evento"
				+ "no formato dd/mm/aaaa")); //pegando dados de um formulário WEB
		SimpleDateFormat sdf1= new 
				SimpleDateFormat("dd/MM/yyyy"); //você pode usar outras máscaras
			
		evento.setDataEvento(sdf1.parse(x));
		evento.setLocal(JOptionPane.
						showInputDialog("digite o local do evento"));
		EventoNegocio eventoNegocio =  new EventoNegocio();
		try {
			salvo = eventoNegocio.solicitarEvento(evento, usuario);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(salvo);
		}else {
		System.out.println("Por favor faça login ou "
				+ "se cadastre para solicitar eventos");
		}
		}
		
}

