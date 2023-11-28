package application.controller;

import java.sql.SQLException;

import application.Principal;
import application.model.Cliente;
import application.model.Usuario;
import application.persintence.AtualizaDadosDao;
import application.persintence.MeusDadosDao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AtualizaCliController implements IAtualizaController {

	private TextField tfCliAtualizaNome;
	private TextField tfCliAtualizaTel;
	private TextField tfCliAtualizaEmail;
	private PasswordField tfCliAtualizaSenha;
	private PasswordField tfCliAtualizaConfSenha;

	private Usuario u;

	public AtualizaCliController(TextField tfCliAtualizaNome, TextField tfCliAtualizaTel, TextField tfCliAtualizaEmail,
			PasswordField tfCliAtualizaSenha, PasswordField tfCliAtualizaConfSenha, Usuario u) {
		super();
		this.tfCliAtualizaNome = tfCliAtualizaNome;
		this.tfCliAtualizaTel = tfCliAtualizaTel;
		this.tfCliAtualizaEmail = tfCliAtualizaEmail;
		this.tfCliAtualizaSenha = tfCliAtualizaSenha;
		this.tfCliAtualizaConfSenha = tfCliAtualizaConfSenha;
		this.u = u;
	}

	@Override
	public void atualizaDados() throws ClassNotFoundException, SQLException {
		AtualizaDadosDao aDao = new AtualizaDadosDao();
		MeusDadosDao mDao = new MeusDadosDao();
		Usuario uAntigo = new Usuario();
		uAntigo = u;
		if (mDao.verificaTipoUsuario(u)) {
			Cliente cl = new Cliente();
			cl.setNome(tfCliAtualizaNome.getText());
			cl.setTelefone(tfCliAtualizaTel.getText());

			Usuario uAtualiza = new Usuario();
			uAtualiza.setEmail(tfCliAtualizaEmail.getText());
			uAtualiza.setSenha(tfCliAtualizaSenha.getText());
			aDao.atualizaCLiente(cl, uAtualiza, uAntigo);

			Alert alertaAlt = new Alert(AlertType.INFORMATION);
			alertaAlt.setContentText("Cliente atualizado com sucesso");
			alertaAlt.setTitle("Sucesso");
			alertaAlt.setHeaderText("Sucesso na Alterações");
			alertaAlt.show();
			Principal.enviaUsuario(uAtualiza);
		}
	}

	@Override
	public boolean verificaRestricoes() throws ClassNotFoundException, SQLException {

		// Verifica se existe campos vazio

		if (tfCliAtualizaConfSenha.getText().isEmpty() || tfCliAtualizaSenha.getText().isEmpty()
				|| tfCliAtualizaEmail.getText().isEmpty() || tfCliAtualizaTel.getText().isEmpty()
				|| tfCliAtualizaNome.getText().isEmpty()) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setContentText("Verifique se os campos estão preenchidos");
			alerta.setTitle("ERRO");
			alerta.show();
			return false;
		}

		// Verifica tamanho telefone
		int tamanhoTel = tfCliAtualizaTel.getText().length();
		if (tamanhoTel != 11) {
			Alert alertaTel = new Alert(AlertType.ERROR);
			alertaTel.setContentText("Verifique o numero de digitos\nEx.\n11965432345\nApenas números");
			alertaTel.setTitle("ERRO");
			alertaTel.setHeaderText("Telefone Invalido");
			alertaTel.show();
			return false;
		}

		// Verifica se Telefone so possui valores numericos
		if (!tfCliAtualizaTel.getText().matches("\\d*")) {
			Alert alertaTel = new Alert(AlertType.ERROR);
			alertaTel.setContentText("Por favor, digite apenas dígitos\nEx.\n11965432345\nApenas números");
			alertaTel.setTitle("ERRO");
			alertaTel.setHeaderText("Telefone Inválido");
			alertaTel.show();
			return false;
		}

		// Verifica dominio email
		String vt[] = new String[5];

		vt[0] = "@gmail.com";
		vt[1] = "@icloud.com";
		vt[2] = "@outlook.com";
		vt[3] = "@hotmail.com";
		vt[4] = "@live.com";

		for (int i = 0; i < 5; i++) {
			if (tfCliAtualizaEmail.getText().contains(vt[i])) {
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

		// Verifica se senhas que vai atualizar sao iguais
		if (!tfCliAtualizaSenha.getText().equals(tfCliAtualizaConfSenha.getText())) {
			Alert alertaSenha = new Alert(AlertType.ERROR);
			alertaSenha.setContentText("Verifique se elas sao identicas");
			alertaSenha.setTitle("ERRO");
			alertaSenha.setHeaderText("As senhas sao diferentes");
			alertaSenha.show();
			return false;
		}

		return true;
	}

	public void prencheeCampos() throws SQLException, ClassNotFoundException {
		MeusDadosDao mDao = new MeusDadosDao();
		Cliente cPrenchee = mDao.buscaDadosUsuario(u, true);
		tfCliAtualizaNome.setText(cPrenchee.getNome());
		tfCliAtualizaTel.setText(cPrenchee.getTelefone());
		tfCliAtualizaSenha.setText(u.getSenha());
		tfCliAtualizaConfSenha.setText(u.getSenha());
		tfCliAtualizaEmail.setText(u.getEmail());
	}

}
