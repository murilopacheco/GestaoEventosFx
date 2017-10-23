package application;

import java.awt.HeadlessException;
import java.text.ParseException;

import modelo.dominio.Usuario;
import visao.controle.EventoControle;

public class Teste {

	public static void main(String[] args) throws HeadlessException, ParseException {
		// TODO Auto-generated method stub
		testarEvento();
	}
	public static void testarEvento() throws HeadlessException, ParseException {
		EventoControle controle = new EventoControle();
		Usuario usuario = new Usuario();
		usuario.setLogin("login");
		usuario.setSenha("senha");
		controle.solicitarEvento(usuario);
	}
}