package visao.controle;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;

public class PrincipalControle {
	
	@FXML
	private MenuBar menuPrincipal;
	
	@FXML
	private Menu menuCadastros, menuRelatorios, menuConfiguracoes;
	
	@FXML
	Pane panePrincipal;
	
	public void abrirCadastroPessoa() throws IOException {
		abrirJanela("/Visao/telas/Pessoa.fxml");
	}
	
	
	
	public void abrirJanela(String caminho) throws IOException {
		URL arquivoFxml;
		arquivoFxml = getClass().getResource(caminho);
		Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFxml);
		panePrincipal.getChildren().clear();
		panePrincipal.getChildren().add(fxmlParent);
	}

}
