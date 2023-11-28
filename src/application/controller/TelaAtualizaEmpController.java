package application.controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import application.Principal;
import application.model.Usuario;
import javafx.event.ActionEvent;

import javafx.scene.control.PasswordField;

public class TelaAtualizaEmpController {
	@FXML
	private TextField tfEmpAtualizaRazao;
	@FXML
	private TextField tfEmpAtualizaTel;
	@FXML
	private TextField tfEmpAtualizaEmail;
	@FXML
	private PasswordField tfEmpAtualizaSenha;
	@FXML
	private PasswordField tfEmpAtualizaConfSenha;
	@FXML
	private Button btnEmpAtualizaDados;
	@FXML
	private TextField tfEmpAtualizaApelido;
	private Usuario u;

	// Event Listener on Button[#btnEmpAtualizaDados].onAction
	@FXML
	public void acaoAtualizaCliente(ActionEvent event) {
		String cmd = event.getSource().toString();

		u = Principal.pegoUsuario();

		AtualizaEmpController a = new AtualizaEmpController(tfEmpAtualizaRazao, tfEmpAtualizaTel, tfEmpAtualizaEmail,
				tfEmpAtualizaSenha, tfEmpAtualizaConfSenha, btnEmpAtualizaDados, tfEmpAtualizaApelido, u);
		
		if(cmd.contains("btnEmpAtualizaDadosVoltar")) {
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
