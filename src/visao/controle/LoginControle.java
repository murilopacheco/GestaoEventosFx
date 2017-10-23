package visao.controle;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class LoginControle {
	
	@FXML
	private Label lbLogin, lbSenha, lbMsg;
	
	@FXML
	private Pane paneCentral;
	
	@FXML
	private TextField txtLogin;
	
	@FXML
	private PasswordField pswSenha;
	
	@FXML
	private Button btnLogin;
	
	public void logar() throws IOException {
		String login;
		String senha;
		login = txtLogin.getText();
		senha = pswSenha.getText();
		if(login.equals("usuario") && senha.equals("12345")) {
			URL arquivoFxml;
			arquivoFxml = getClass().getResource("/Visao/telas/Principal.fxml");
			Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFxml);
			paneCentral.getChildren().clear();
			paneCentral.getChildren().add(fxmlParent);
 		}else {
			lbMsg.setText("Dados incorretos!");
			lbMsg.setVisible(true);
		}
	}
	
	public void limpar() {
		txtLogin.setText("");
		pswSenha.setText("");
		lbMsg.setVisible(false);
	}

}
