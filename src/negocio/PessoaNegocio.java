package negocio;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;


import modelo.dao.PessoaDao;
import modelo.dominio.PessoaFisica;
import modelo.dominio.PessoaJuridica;

public class PessoaNegocio {
	
	public String salvar(PessoaFisica pessoaFisica) throws ParseException, SQLException {
		String salvo = "";
		boolean valido = 
				validarIdade(pessoaFisica.getDataNascimento());
		if(valido) {
			PessoaDao pessoaDao = new PessoaDao();
			salvo = pessoaDao.salvar(pessoaFisica);
		}else {
			salvo =  "O usuário precisa ter mais de 18 anos!";
		}
		return salvo;
	}
	
	public String salvar(PessoaJuridica pessoaJuridica) {
		String salvo = "";
		String camposValidos = "";
		camposValidos = verificaCampos(pessoaJuridica);
		if(camposValidos.equals("")) {
			PessoaDao pessoaDao = new PessoaDao();
			salvo = pessoaDao.salvar(pessoaJuridica);
		}else {
			salvo = camposValidos;
		}
		return salvo;
	}
	
	public boolean validarIdade(LocalDate nascimento) throws ParseException {
		boolean valido = false;
		LocalDate dataAtual = LocalDate.now();
		Period idade = Period.between(nascimento, dataAtual); // Period calcula a idade completa.
		System.out.println(idade.getYears() + " anos " + idade.getMonths() + " meses e " + idade.getDays() + " dias" );
		if(idade.getYears() >= 18) {
		valido = true;
		}
		return valido;
		
	}
	
	public String verificaCampos(PessoaJuridica pessoaJuridica) {
		StringBuilder inconsistencias = new StringBuilder();
		if(pessoaJuridica.getNome().equals("") || pessoaJuridica.getNome() == null) {
			inconsistencias.append("O nome é obrigatório. \n");
		}
		if(pessoaJuridica.getRazaoSocial().equals("") || pessoaJuridica.getRazaoSocial() == null) {
			inconsistencias.append(" A razão social é obrigatório. \n");
		}
		if(pessoaJuridica.getInscricaoEstadual().equals("") || pessoaJuridica.getInscricaoEstadual() == null) {
			inconsistencias.append("A inscrição estadual é obrigatório. \n");
		}
		if(pessoaJuridica.getTelefone().equals("") || pessoaJuridica.getTelefone() == null) {
			inconsistencias.append("O telefone é obrigatório. \n");
		}
		if(pessoaJuridica.getResponsavel().equals("") || pessoaJuridica.getResponsavel() == null) {
		inconsistencias.append("O responsável é obrigatório. \n");
		}
		return inconsistencias.toString();
	}
	
}
