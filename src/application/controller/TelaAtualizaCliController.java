package application.controller;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import application.Principal;
import application.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class TelaAtualizaCliController {
	@FXML
	private TextField tfCliAtualizaNome;
	@FXML
	private TextField tfCliAtualizaTel;
	@FXML
	private TextField tfCliAtualizaEmail;
	@FXML
	private PasswordField tfCliAtualizaSenha;
	@FXML
	private PasswordField tfCliAtualizaConfSenha;
	@FXML
	private Button btnCliAtualizaDados;

	private Usuario u;

	// Event Listener on Button[#btnCliAtualizaDados].onAction
	@FXML
	public void acaoAtualizaCliente(ActionEvent event) {
		String cmd = event.getSource().toString();

		u = Principal.pegoUsuario();
		AtualizaCliController a = new AtualizaCliController(tfCliAtualizaNome, tfCliAtualizaTel, tfCliAtualizaEmail,
				tfCliAtualizaSenha, tfCliAtualizaConfSenha, u);
		
		if(cmd.contains("btnCliAtualizaDadosVoltar")) {
			Principal.trocaTela("MeuPerfil");
		}

		if (cmd.contains("Preencher Campos")) {
			try {
				a.prencheeCampos();
			
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}

		if (cmd.contains("Confirmar")) {
			try {
				if (a.verificaRestricoes()) {
					a.atualizaDados();
					Principal.trocaTela("MeuPerfil");
				}
			} catch (ClassNotFoundException | SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}
}
