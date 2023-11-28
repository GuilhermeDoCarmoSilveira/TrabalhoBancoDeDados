package application;

import application.model.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Principal extends Application {

	private static Stage stage;

	private static Scene scnLogin;
	private static Scene scnCadCli;
	private static Scene scnCadEmp;
	private static Scene scnEsqSenha;
	private static Scene scnMeuPerfil;
	private static Scene scnAtualizaCliente;
	private static Scene scnAtualizaEmpresa;
	private static Scene scnPrincipal;
	private static Usuario user;
	private static String produtoCarrinho;

	@Override
	public void start(Stage primaryStage) throws Exception {

		stage = primaryStage;

		Pane paneLogin = (Pane) FXMLLoader.load(this.getClass().getResource("TelaLogin.fxml"));
		scnLogin = new Scene(paneLogin);
		

		Pane paneCadCli = (Pane) FXMLLoader.load(this.getClass().getResource("TelaCadCliente.fxml"));
		scnCadCli = new Scene(paneCadCli);

		Pane paneCadEmp = (Pane) FXMLLoader.load(this.getClass().getResource("TelaCadEmpresa.fxml"));
		scnCadEmp = new Scene(paneCadEmp);

		Pane paneEsqSenha = (Pane) FXMLLoader.load(this.getClass().getResource("TelaEsqueceuSenha.fxml"));
		scnEsqSenha = new Scene(paneEsqSenha);

		TabPane paneMeuPerfil = (TabPane) FXMLLoader.load(this.getClass().getResource("TelaMeuPerfil.fxml"));
		scnMeuPerfil = new Scene(paneMeuPerfil);

		AnchorPane paneAtualizaCliente = (AnchorPane) FXMLLoader.load(this.getClass().getResource("TelaAtualizaCliente.fxml"));
		scnAtualizaCliente = new Scene(paneAtualizaCliente);
		
		AnchorPane paneAtualizaEmpresa = (AnchorPane) FXMLLoader.load(this.getClass().getResource("TelaAtualizaEmpresa.fxml"));
		scnAtualizaEmpresa = new Scene(paneAtualizaEmpresa);
		
		TabPane panePrincipal = (TabPane) FXMLLoader.load(this.getClass().getResource("TelaPrincipal.fxml"));
		scnPrincipal = new Scene(panePrincipal);


		primaryStage.setScene(scnLogin);
		primaryStage.setTitle("Troca Fácil - O Seu Destino para Negociações Descomplicadas 💰");
		primaryStage.getIcons().add(new Image("./images/logotf.png"));
		primaryStage.show();

	}

	public static void trocaTela(String scr) {
		switch(scr) {
		case "Login":
			stage.setScene(scnLogin);
			break;
		case "cadCliente":
			stage.setScene(scnCadCli);
			break;
		case "cadEmpresa":
			stage.setScene(scnCadEmp);
			break;
		case "EsqSenha":
			stage.setScene(scnEsqSenha);
			break;
		case "MeuPerfil":
			stage.setScene(scnMeuPerfil);
			break;
		case "AtualizaCli":
			stage.setScene(scnAtualizaCliente);
			break;
		case "AtualizaEmp":
			stage.setScene(scnAtualizaEmpresa);
			break;
		case "Principal":
			stage.setScene(scnPrincipal);
		}
			
	}

	public static void enviaUsuario(Usuario u) {
		user = u;
	}

	public static Usuario pegoUsuario() {
		return user;
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public static void enviaCarrinho(String pedido) {
		produtoCarrinho = pedido;
	}

}
