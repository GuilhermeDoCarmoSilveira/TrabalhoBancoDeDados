package application.controller;

import java.sql.SQLException;

import application.model.Empresa;
import application.model.Endereco;
import application.persintence.EmpresaDao;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EmpresaController implements IEmpresaController {

	private TextField tfCadEmpCnpj;
	private TextField tfCadEmpRazaoSocial;
	private TextField tfCadEmpApelido;
	private TextField tfCadEmpEmail;
	private PasswordField tfCadEmpSenha;
	private PasswordField tfCadEmpConfSenha;
	private Button btnCadEmpFinalizar;
	private TextField tfCadEmpTitulo;
	private TextField tfCadEmpCep;
	private TextField tfCadEmpLogradouro;
	private TextField tfCadEmpNumero;
	private TextField tfCadEmpComplemento;
	private TextField tfCadEmpTelefone;
	private TextArea taCadEmpInfo;

	public EmpresaController(TextField tfCadEmpCnpj, TextField tfCadEmpRazaoSocial, TextField tfCadEmpApelido,
			TextField tfCadEmpEmail, PasswordField tfCadEmpSenha, PasswordField tfCadEmpConfSenha,
			Button btnCadEmpFinalizar, TextField tfCadEmpTitulo, TextField tfCadEmpCep, TextField tfCadEmpLogradouro,
			TextField tfCadEmpNumero, TextField tfCadEmpComplemento, TextField tfCadEmpTelefone,
			TextArea taCadEmpInfo) {
		super();
		this.tfCadEmpCnpj = tfCadEmpCnpj;
		this.tfCadEmpRazaoSocial = tfCadEmpRazaoSocial;
		this.tfCadEmpApelido = tfCadEmpApelido;
		this.tfCadEmpEmail = tfCadEmpEmail;
		this.tfCadEmpSenha = tfCadEmpSenha;
		this.tfCadEmpConfSenha = tfCadEmpConfSenha;
		this.btnCadEmpFinalizar = btnCadEmpFinalizar;
		this.tfCadEmpTitulo = tfCadEmpTitulo;
		this.tfCadEmpCep = tfCadEmpCep;
		this.tfCadEmpLogradouro = tfCadEmpLogradouro;
		this.tfCadEmpNumero = tfCadEmpNumero;
		this.tfCadEmpComplemento = tfCadEmpComplemento;
		this.tfCadEmpTelefone = tfCadEmpTelefone;
		this.taCadEmpInfo = taCadEmpInfo;
	}

	@Override
	public void insereEmpresa(Empresa emp, Endereco e) throws ClassNotFoundException, SQLException {
		EmpresaDao eDao = new EmpresaDao();
		if (!eDao.verificaCnpj(emp)) {
			eDao.insereUsuario(emp);
			eDao.insereEmpresa(emp);
			;
			if (tfCadEmpComplemento.getText().isEmpty() && taCadEmpInfo.getText().isEmpty()) {
				eDao.insereEnderecoSemInfoCompl(e, emp);
			} else if (taCadEmpInfo.getText().isEmpty() && !tfCadEmpComplemento.getText().isEmpty()) {
				eDao.insereEnderecoSemInfo(e, emp);
			} else if (tfCadEmpComplemento.getText().isEmpty() && !taCadEmpInfo.getText().isEmpty()) {
				eDao.insereEnderecoSemCompl(e, emp);
			} else {
				eDao.insereEndereco(e, emp);
			}

			Alert alertaCnpj = new Alert(AlertType.INFORMATION);
			alertaCnpj.setTitle("CONFIRMAÇÃO");
			alertaCnpj.setHeaderText("Empresa Cadastrado com sucesso");
			alertaCnpj.show();
			limpacampos();
		} else {
			Alert alertaCnpj = new Alert(AlertType.ERROR);
			alertaCnpj.setContentText("Este Cnpj já possui uma conta, faça o Login");
			alertaCnpj.setTitle("ERRO");
			alertaCnpj.setHeaderText("Cnpj já cadastrado");
			alertaCnpj.show();
		}
	}

	@Override
	public boolean verificaRestricoes() throws ClassNotFoundException, SQLException {

		// Verifica se Cnpj possui 14 digitos
		int tamanhoCnpj = tfCadEmpCnpj.getText().length();
		if (tamanhoCnpj != 14) {
			Alert alertaCnpj = new Alert(AlertType.ERROR);
			alertaCnpj.setContentText("Por favor, verifique o número de dígitos");
			alertaCnpj.setTitle("ERRO");
			alertaCnpj.setHeaderText("Cnpj Inválido");
			alertaCnpj.show();
			return false;
		}

		// Verifica se Cep so possui valores numericos
		if (!tfCadEmpCnpj.getText().matches("\\d*")) {
			Alert alertaCep = new Alert(AlertType.ERROR);
			alertaCep.setContentText("Por favor, Insira apenas valores númericos");
			alertaCep.setTitle("ERRO");
			alertaCep.setHeaderText("Cnpj Inválido");
			alertaCep.show();
			return false;
		}

		// Verifica se Telefone possui 11 digitos
		int tamanhoTel = tfCadEmpTelefone.getText().length();
		if (tamanhoTel != 11) {
			Alert alertaTel = new Alert(AlertType.ERROR);
			alertaTel.setContentText("Por favor, verifique o número de dígitos\nEx.\n11965432345\nApenas números");
			alertaTel.setTitle("ERRO");
			alertaTel.setHeaderText("Telefone Inválido");
			alertaTel.show();
			return false;
		}

		// Verifica se Telefone so possui valores numericos
		if (!tfCadEmpTelefone.getText().matches("\\d*")) {
			Alert alertaTel = new Alert(AlertType.ERROR);
			alertaTel.setContentText("Por favor, digite apenas dígitos\nEx.\n11965432345\nApenas números");
			alertaTel.setTitle("ERRO");
			alertaTel.setHeaderText("Telefone Inválido");
			alertaTel.show();
			return false;
		}

		// Verifica se email de dominio é existente
		String vt[] = new String[5];

		vt[0] = "@gmail.com";
		vt[1] = "@icloud.com";
		vt[2] = "@outlook.com";
		vt[3] = "@hotmail.com";
		vt[4] = "@live.com";

		for (int i = 0; i < 5; i++) {
			if (tfCadEmpEmail.getText().contains(vt[i])) {
				break;
			}
			if (i == 4) {
				Alert alertaEmail = new Alert(AlertType.ERROR);
				alertaEmail.setContentText("Por favor, verifique o dominio do email\nEx.\n@gmail.com\n@icloud.com\n@outlook.com\n@hotmail.com\n@live.com");
				alertaEmail.setTitle("ERRO");
				alertaEmail.setHeaderText("Domínio de email Inválido");
				alertaEmail.show();
				return false;
			}
		}

		// Verifica se senhas sao iguais
		if (!tfCadEmpSenha.getText().equals(tfCadEmpConfSenha.getText())) {
			Alert alertaSenha = new Alert(AlertType.ERROR);
			alertaSenha.setContentText("Por favor, verifique se elas sao idênticas");
			alertaSenha.setTitle("ERRO");
			alertaSenha.setHeaderText("As senhas são diferentes");
			alertaSenha.show();
			return false;
		}

		// Verifica se cep tem 8 digitos
		int tamanhoCep = tfCadEmpCep.getText().length();
		if (tamanhoCep != 8) {
			Alert alertaCep = new Alert(AlertType.ERROR);
			alertaCep.setContentText("Por favor, verifique o numero de dígitos");
			alertaCep.setTitle("ERRO");
			alertaCep.setHeaderText("Cep Invalido");
			alertaCep.show();
			return false;
		}

		// Verifica se Cep so possui valores numericos
		if (!tfCadEmpCep.getText().matches("\\d*")) {
			Alert alertaCep = new Alert(AlertType.ERROR);
			alertaCep.setContentText("Por favor, Insira apenas valores númericos");
			alertaCep.setTitle("ERRO");
			alertaCep.setHeaderText("Cep Inválido");
			alertaCep.show();
			return false;
		}

		return true;
	}

	@Override
	public boolean verificaCamposVazios() throws ClassNotFoundException, SQLException {
		if (tfCadEmpCnpj.getText().isEmpty() || tfCadEmpRazaoSocial.getText().isEmpty()
				|| tfCadEmpApelido.getText().isEmpty() || tfCadEmpTelefone.getText().isEmpty()
				|| tfCadEmpEmail.getText().isEmpty() || tfCadEmpSenha.getText().isEmpty()
				|| tfCadEmpConfSenha.getText().isEmpty() || tfCadEmpTitulo.getText().isEmpty()
				|| tfCadEmpCep.getText().isEmpty() || tfCadEmpLogradouro.getText().isEmpty()
				|| tfCadEmpNumero.getText().isEmpty()) {

			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setContentText("Verifique os Campos");
			alerta.setTitle("ERRO");
			alerta.show();
			return false;
		} else {
			return true;
		}
	}

	private void limpacampos() {
		tfCadEmpCnpj.setText("");
		tfCadEmpRazaoSocial.setText("");
		tfCadEmpApelido.setText("");
		tfCadEmpTelefone.setText("");
		tfCadEmpEmail.setText("");
		tfCadEmpSenha.setText("");
		tfCadEmpConfSenha.setText("");
		tfCadEmpTitulo.setText("");
		tfCadEmpCep.setText("");
		tfCadEmpLogradouro.setText("");
		tfCadEmpNumero.setText("");
		tfCadEmpComplemento.setText("");
		taCadEmpInfo.setText("");
	}

}
