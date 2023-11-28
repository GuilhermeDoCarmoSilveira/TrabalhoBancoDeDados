package application.controller;

import java.sql.SQLException;

import application.Principal;
import application.model.Cliente;
import application.model.Empresa;
import application.model.Usuario;
import application.persintence.AtualizaDadosDao;
import application.persintence.MeusDadosDao;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AtualizaEmpController implements IAtualizaController {
	private TextField tfEmpAtualizaRazao;
	private TextField tfEmpAtualizaTel;
	private TextField tfEmpAtualizaEmail;
	private PasswordField tfEmpAtualizaSenha;
	private PasswordField tfEmpAtualizaConfSenha;
	private Button btnEmpAtualizaDados;
	private TextField tfEmpAtualizaApelido;
	private Usuario u;

	public AtualizaEmpController(TextField tfEmpAtualizaRazao, TextField tfEmpAtualizaTel, TextField tfEmpAtualizaEmail,
			PasswordField tfEmpAtualizaSenha, PasswordField tfEmpAtualizaConfSenha, Button btnEmpAtualizaDados,
			TextField tfEmpAtualizaApelido, Usuario u) {
		super();
		this.tfEmpAtualizaRazao = tfEmpAtualizaRazao;
		this.tfEmpAtualizaTel = tfEmpAtualizaTel;
		this.tfEmpAtualizaEmail = tfEmpAtualizaEmail;
		this.tfEmpAtualizaSenha = tfEmpAtualizaSenha;
		this.tfEmpAtualizaConfSenha = tfEmpAtualizaConfSenha;
		this.btnEmpAtualizaDados = btnEmpAtualizaDados;
		this.tfEmpAtualizaApelido = tfEmpAtualizaApelido;
		this.u = u;
	}

	@Override
	public void atualizaDados() throws ClassNotFoundException, SQLException {
		AtualizaDadosDao aDao = new AtualizaDadosDao();
		MeusDadosDao mDao = new MeusDadosDao();
		Usuario uAntigo = new Usuario();
		uAntigo = u;
		if (!mDao.verificaTipoUsuario(u)) {
			Empresa emp = new Empresa();
			emp.setRazaoSocial(tfEmpAtualizaRazao.getText());
			emp.setApelido(tfEmpAtualizaApelido.getText());
			emp.setTelefone(tfEmpAtualizaTel.getText());

			Usuario uAtualiza = new Usuario();
			uAtualiza.setEmail(tfEmpAtualizaEmail.getText());
			uAtualiza.setSenha(tfEmpAtualizaSenha.getText());
			aDao.atualizaEmpresa(emp, uAtualiza, uAntigo);

			Alert alertaAlt = new Alert(AlertType.INFORMATION);
			alertaAlt.setContentText("Empresa atualizada com sucesso");
			alertaAlt.setTitle("Sucesso");
			alertaAlt.setHeaderText("Sucesso na Alterações");
			alertaAlt.show();
			Principal.enviaUsuario(uAtualiza);

		}

	}

	@Override
	public boolean verificaRestricoes() throws ClassNotFoundException, SQLException {
		// Verifica se existe campos vazio

		if (tfEmpAtualizaConfSenha.getText().isEmpty() || tfEmpAtualizaSenha.getText().isEmpty()
				|| tfEmpAtualizaEmail.getText().isEmpty() || tfEmpAtualizaTel.getText().isEmpty()
				|| tfEmpAtualizaRazao.getText().isEmpty() || tfEmpAtualizaApelido.getText().isEmpty()) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setContentText("Verifique se os campos estão preenchidos");
			alerta.setTitle("ERRO");
			alerta.show();
			return false;
		}

		// Verifica tamanho telefone
		int tamanhoTel = tfEmpAtualizaTel.getText().length();
		if (tamanhoTel != 11) {
			Alert alertaTel = new Alert(AlertType.ERROR);
			alertaTel.setContentText("Verifique o numero de digitos\nEx.\n11965432345\nApenas números");
			alertaTel.setTitle("ERRO");
			alertaTel.setHeaderText("Telefone Invalido");
			alertaTel.show();
			return false;
		}

		// Verifica se Telefone so possui valores numericos
		if (!tfEmpAtualizaTel.getText().matches("\\d*")) {
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
			if (tfEmpAtualizaEmail.getText().contains(vt[i])) {
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
		if (!tfEmpAtualizaSenha.getText().equals(tfEmpAtualizaConfSenha.getText())) {
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
	public void prencheeCampos() throws SQLException, ClassNotFoundException {
		MeusDadosDao mDao = new MeusDadosDao();
		Empresa ePrenchee = mDao.buscaDadosUsuario(u, false);
		tfEmpAtualizaRazao.setText(ePrenchee.getRazaoSocial());
		tfEmpAtualizaTel.setText(ePrenchee.getTelefone());
		tfEmpAtualizaApelido.setText(ePrenchee.getApelido());

		tfEmpAtualizaSenha.setText(u.getSenha());
		tfEmpAtualizaConfSenha.setText(u.getSenha());
		tfEmpAtualizaEmail.setText(u.getEmail());

	}

}
