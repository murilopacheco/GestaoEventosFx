package visao.controle;

import java.awt.HeadlessException;
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
		SimpleDateFormat sp = new SimpleDateFormat("dd/mm/yyyy");
		evento.setDataEvento(
				sp.parse((
						JOptionPane.
						showInputDialog("digite a data do "
								+ "evento(Ex: 22/10/2017)"))));
		evento.setLocal(JOptionPane.
						showInputDialog("digite o local do evento"));
		EventoNegocio eventoNegocio =  new EventoNegocio();
		String criado = eventoNegocio.solicitarEvento(evento, usuario);
		System.out.println(criado);
		}
		System.out.println("Por favor faça login ou "
				+ "se cadastre para solicitar eventos");
		
		}
		
}

