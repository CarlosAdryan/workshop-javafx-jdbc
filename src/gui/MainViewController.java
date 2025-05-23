package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable{

	@FXML
	private MenuItem menuItemSeller;
	
	@FXML
	private MenuItem menuItemDepartment;
	
	@FXML
	private MenuItem menuItemAbout;
	
	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSellerAction");
	}
	
	@FXML
	public void onMenuItemDepartmentAction() {
		loadView("/gui/DepartmentList.fxml");
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml");
	}
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {	
	}
	
	private void loadView(String absoluteName) {
	    try {
	        URL fxmlUrl = getClass().getResource(absoluteName);
	        if (fxmlUrl == null) {
	            System.out.println("⚠ ERRO: Arquivo FXML não encontrado no caminho: " + absoluteName);
	            Alerts.showAlert("Erro de Caminho", "Arquivo FXML não encontrado", 
	                             "Verifique se o caminho '" + absoluteName + "' está correto.", 
	                             AlertType.ERROR);
	            return;
	        }

	        FXMLLoader loader = new FXMLLoader(fxmlUrl);
	        VBox newVBox = loader.load();

	        Scene mainScene = Main.getMainScene();
	        VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

	        Node mainMenu = mainVBox.getChildren().get(0);
	        mainVBox.getChildren().clear();
	        mainVBox.getChildren().add(mainMenu);
	        mainVBox.getChildren().addAll(newVBox.getChildren());

	    } catch (IOException e) {
	        Alerts.showAlert("IO Exception", "Erro ao carregar a view", e.getMessage(), AlertType.ERROR);
	    }
	}

}
