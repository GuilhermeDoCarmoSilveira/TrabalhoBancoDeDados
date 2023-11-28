package application.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import application.Principal;
import application.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class TelaLoginController {
	@FXML
	private TextField tfLoginEmail;
	@FXML
	private PasswordField tfLoginSenha;
	@FXML
	private Button btnLoginEntrar;
	@FXML
	private Hyperlink hlLoginCadCliente;
	@FXML
	private Hyperlink hlLoginCadEmpresa;
	@FXML
	private Hyperlink hlLoginEsqueceuSenha;

	// Event Listener on Button[#btnLoginEntrar].onAction
	@FXML
	public void acaoLogin(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {

		String cmd = event.getSource().toString();
		

		
		UsuarioController uc = new UsuarioController(tfLoginEmail, tfLoginSenha);

		if (cmd.contains("Entrar")) {
			boolean resultado = uc.verificaUsuario();
			if (resultado == true) {
				Usuario u = new Usuario();
				u.setEmail(tfLoginEmail.getText());
				u.setSenha(tfLoginSenha.getText());
				try {
					boolean resulLogin = uc.buscarUsuario(u);
					if (resulLogin) {
						Principal.enviaUsuario(u);
						Principal.trocaTela("Principal");
					}
				} catch (ClassNotFoundException | SQLException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		}

		if (cmd.contains("Cadastre-se Cliente")) {
			Principal.trocaTela("cadCliente");
		}

		if (cmd.contains("Cadastre-se Empresa")) {
			Principal.trocaTela("cadEmpresa");
		}

		if (cmd.contains("Esqueceu a senha ?")) {
			Principal.trocaTela("EsqSenha");
		}
	}
}
