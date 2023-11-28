package application.controller;

import java.sql.SQLException;

import application.model.Usuario;
import application.persintence.EsqueceuSenhaDao;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class EsqueceuSenhaController implements IEsqueceuSenha {

	private TextField tfEsqSenhaEmail;
	private PasswordField tfEsqSenhaNovaSenha;
	private PasswordField tfEsqSenhaConfirmaSenha;
	private Button btnSalvarAlteracoes;

	public EsqueceuSenhaController(TextField tfEsqSenhaEmail, PasswordField tfEsqSenhaNovaSenha,
			PasswordField tfEsqSenhaConfirmaSenha, Button btnSalvarAlteracoes) {
		super();
		this.tfEsqSenhaEmail = tfEsqSenhaEmail;
		this.tfEsqSenhaNovaSenha = tfEsqSenhaNovaSenha;
		this.tfEsqSenhaConfirmaSenha = tfEsqSenhaConfirmaSenha;
		this.btnSalvarAlteracoes = btnSalvarAlteracoes;
	}

	@Override
	public void atualizarSenha(Usuario u) throws ClassNotFoundException, SQLException {
		EsqueceuSenhaDao esDao = new EsqueceuSenhaDao();
		if (esDao.atualizarSenha(u)) {
			Alert alertaSenha = new Alert(AlertType.INFORMATION);
			alertaSenha.setTitle("CONFIRMAÇÃO");
			alertaSenha.setHeaderText("Senha Atualizada com sucesso");
			alertaSenha.show();
		} else {
			Alert alertaSenha = new Alert(AlertType.ERROR);
			alertaSenha.setTitle("ERRO");
			alertaSenha.setHeaderText("Email Invalido");
			alertaSenha.show();
		}
	}

	@Override
	public boolean verificaRestricoes() throws ClassNotFoundException, SQLException {

		// Verifica se email de dominio é existente

		String vt[] = new String[5];

		vt[0] = "@gmail.com";
		vt[1] = "@icloud.com";
		vt[2] = "@outlook.com";
		vt[3] = "@hotmail.com";
		vt[4] = "@live.com";

		for (int i = 0; i < 5; i++) {
			if (tfEsqSenhaEmail.getText().contains(vt[i])) {
				break;
			}
			if (i == 4) {
				Alert alertaEmail = new Alert(AlertType.ERROR);
				alertaEmail.setContentText(
						"Verifique o dominio do email\nEx.\n@gmail.com\n@icloud.com\n@outlook.com\n@hotmail.com\n@live.com");
				alertaEmail.setTitle("ERRO");
				alertaEmail.setHeaderText("Dominio de email Invalido");
				alertaEmail.show();
				return false;
			}
		}

		// Verifica se senhas sao iguais
		if (!tfEsqSenhaNovaSenha.getText().equals(tfEsqSenhaConfirmaSenha.getText())) {
			Alert alertaSenha = new Alert(AlertType.ERROR);
			alertaSenha.setContentText("Verifique se elas sao identicas");
			alertaSenha.setTitle("ERRO");
			alertaSenha.setHeaderText("As senhas sao diferentes");
			alertaSenha.show();
			return false;
		}

		return true;
	}

	@Override
	public boolean verificaCamposVazios() throws ClassNotFoundException, SQLException {
		if(tfEsqSenhaEmail.getText().isEmpty() || tfEsqSenhaNovaSenha.getText().isEmpty()
				|| tfEsqSenhaConfirmaSenha.getText().isEmpty()) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setContentText("Verifique os Campos");
			alerta.setTitle("ERRO");
			alerta.show();
			return false;
		}else {
			return true;
		}
	}

	private void limpar() {
		tfEsqSenhaEmail.setText("");
		tfEsqSenhaNovaSenha.setText("");
	}

}
