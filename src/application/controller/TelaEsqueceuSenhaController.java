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

public class TelaEsqueceuSenhaController {
	@FXML
	private TextField tfEsqSenhaEmail;
	@FXML
	private PasswordField tfEsqSenhaNovaSenha;
	@FXML
	private PasswordField tfEsqSenhaConfirmaSenha;
	@FXML
	private Button btnSalvarAlteracoes;

	@FXML
	public void acaoEsqueceuSenha(ActionEvent event) throws ClassNotFoundException, SQLException {
		
		String cmd = event.getSource().toString();
		
		EsqueceuSenhaController esc = new EsqueceuSenhaController(tfEsqSenhaEmail, tfEsqSenhaNovaSenha, 
				tfEsqSenhaConfirmaSenha, btnSalvarAlteracoes);
		
		if(cmd.contains("btnEsqSenhaVoltar")) {
			Principal.trocaTela("Login");
		}
		
		if(cmd.contains("Salvar Alterações")) {
			boolean resultado = esc.verificaCamposVazios();
			if(resultado == true) {
				Usuario u = new Usuario();
				u.setEmail(tfEsqSenhaEmail.getText());
				u.setSenha(tfEsqSenhaNovaSenha.getText());
				try {
					if(esc.verificaRestricoes()) {
						esc.atualizarSenha(u);
						Principal.trocaTela("Login");
					}
				}catch (ClassNotFoundException | SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
		}
		
	}
	
	
}
