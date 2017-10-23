package visao.controle;


import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import modelo.dominio.PessoaFisica;
import modelo.dominio.PessoaJuridica;
import modelo.dominio.Usuario;
import negocio.PessoaNegocio;


public class PessoaControle implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static PessoaFisica pessoaFisica;
	static PessoaJuridica pessoaJuridica;
	@FXML
	private Label lbNome, lbCPF, lbRG, lbDataNascimento, 
	lbEndereco, lbTelefone, lbCelular, lbEmail, lbIE, lbSexo;
	@FXML
	private TextField txtNome;
	@FXML
	private TextField  txtCPF, txtRG, txtEndereco,
	txtTelefone, txtCelular, txtEmail, txtIE;
	@FXML
	private DatePicker dtDataNascimento;
	@FXML
	private Button btnSalvar, btnCancelar;
	@FXML
	private RadioButton rbSexoMasculino, rbSexoFeminino
	, rbTipoFisica, rbTipoJuridico;
	@FXML
	private ToggleGroup RbSexo, rbTipo;
	@FXML
	private TableView<PessoaJuridica> tbPessoaJuridica;
	@FXML
	private TableColumn<PessoaJuridica, Integer> clIdJuridico;
	@FXML
	private TableColumn<PessoaJuridica, String> clNomeFantasia;
	@FXML
	private TableColumn<PessoaJuridica, String> clCNPJ;
	@FXML
	private TableColumn<PessoaJuridica, String> clRazao;
	@FXML
	private TableView<PessoaFisica> tbPessoas;
	@FXML
	private TableColumn<PessoaFisica, Integer> clId;
	@FXML
	private TableColumn<PessoaFisica, String> clNome;
	@FXML
	private TableColumn<PessoaFisica, String> clCPF;
	@FXML
	private TableColumn<PessoaFisica, LocalDate> clDataNascimento;
	@FXML
	private HBox hboxTipo, hboxSexo;
	
	
	public void setarDadosPessoa(String tipo) {
		
		if(tipo.equals("f")) {
			pessoaFisica = new PessoaFisica();
			pessoaFisica.setNome(txtNome.getText());
			pessoaFisica.setCPF(txtCPF.getText());
			pessoaFisica.setRG(txtRG.getText());
			pessoaFisica.setEndereco(txtEndereco.getText());
			RadioButton sexo = new RadioButton();
					sexo = (RadioButton) RbSexo.getSelectedToggle();
			pessoaFisica.setSexo(sexo.getText());
			pessoaFisica.setTelefone(txtTelefone.getText());
			pessoaFisica.setCelular(txtCelular.getText());
			pessoaFisica.setTipo("");
			pessoaFisica.setDataNascimento(dtDataNascimento.getValue());
			pessoaFisica.setEmail(txtEmail.getText());
			Usuario usuario = new Usuario();
			usuario.setLogin(pessoaFisica.getEmail());
			usuario.setSenha("senha");
		}else {
			pessoaJuridica = new PessoaJuridica();
			pessoaJuridica.setNome("empresa x");
			pessoaJuridica.setCNPJ("11.111.111/0001-11");
			pessoaJuridica.setInscricaoEstadual("123");
			pessoaJuridica.setEndereco("rua 1 numero 1 centro");
			pessoaJuridica.setRazaoSocial("empresa s/a");
			pessoaJuridica.setResponsavel("fulano");
			pessoaJuridica.setTelefone("(61) 22222-9898");
			pessoaJuridica.setCelular("(61) 98888-9898");
			pessoaJuridica.setTipo("juridica");
			pessoaJuridica.setEmail("empresa@empresa.com");
			Usuario usuario = new Usuario();
			usuario.setLogin(pessoaJuridica.getEmail());
			usuario.setSenha("senha");
		}
	}
	
	
	public void salvar() throws ParseException, SQLException {
		setarDadosPessoa("f");
		PessoaNegocio pessoaNegocio = new PessoaNegocio();
		String salvo = pessoaNegocio.salvar(this.pessoaFisica);
		System.out.println(salvo);
	}
	
	public void salvar(PessoaJuridica pessoaJuridica) {
		setarDadosPessoa("j");
		PessoaNegocio pessoaNegocio = new PessoaNegocio();
		String salvo = pessoaNegocio.salvar(this.pessoaJuridica);
		System.out.println(salvo);
	}
	@FXML
	public void selecionaPessoaFísica() {
		txtIE.setVisible(false);
		lbIE.setVisible(false);
		lbCPF.setText("CPF:*");
		lbSexo.setVisible(true);
		hboxSexo.setVisible(true);
		
	}
	
	public void SelecionaPessoaJuridica() {
		txtIE.setVisible(true);
		lbIE.setVisible(true);
		lbCPF.setText("CNPJ:*");
		lbSexo.setVisible(false);
		hboxSexo.setVisible(false);
	}
		
}
