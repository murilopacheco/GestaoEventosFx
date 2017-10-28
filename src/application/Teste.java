package application;

import java.awt.HeadlessException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import modelo.dominio.Evento;
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
		usuario.setId(1);
		controle.listarEventos();
		controle.solicitarEvento(usuario);
	}
}
