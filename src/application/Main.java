package application;
	
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
    public void start(Stage principal) throws Exception{
            VBox raiz = new VBox(10); // 1
            raiz.setAlignment(Pos.CENTER); // 2
        URL arquivoFXML;
        arquivoFXML = getClass().getResource("/Visao/telas/Principal.fxml");
        Parent fxmlParent =(Parent) FXMLLoader.load(arquivoFXML);
            Scene cena = new Scene(fxmlParent, 800, 600);
             principal.setTitle("Janela Principal");
             principal.setScene(cena);
             principal.setResizable(true);
            principal.show();


    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
