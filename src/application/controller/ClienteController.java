package application.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import application.model.Cliente;
import application.model.Endereco;
import application.persintence.ClienteDao;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ClienteController implements IClienteController {

	private TextField tfCadCliCpf;
	private TextField tfCadCliNome;
	private TextField tfCadCliTel;
	private TextField tfCadCliEmail;
	private ComboBox cmbCadCliSexo;
	private TextField tfCadCliData;
	private PasswordField tfCadCliSenha;
	private PasswordField tfCadCliConfSenha;
	private Button btnCadCliFinalizar;
	private TextField tfCadCliTitulo;
	private TextField tfCadCliCep;
	private TextField tfCadCliLogradouro;
	private TextField tfCadCliNumero;
	private TextField tfCadCliComplemento;
	private TextArea taCadCliInfo;

	public ClienteController(TextField tfCadCliCpf, TextField tfCadCliNome, TextField tfCadCliTel,
			TextField tfCadCliEmail, ComboBox cmbCadCliSexo, TextField tfCadCliData, PasswordField tfCadCliSenha,
			PasswordField tfCadCliConfSenha, Button btnCadCliFinalizar, TextField tfCadCliTitulo, TextField tfCadCliCep,
			TextField tfCadCliLogradouro, TextField tfCadCliNumero, TextField tfCadCliComplemento,
			TextArea taCadCliInfo) {
		super();
		this.tfCadCliCpf = tfCadCliCpf;
		this.tfCadCliNome = tfCadCliNome;
		this.tfCadCliTel = tfCadCliTel;
		this.tfCadCliEmail = tfCadCliEmail;
		this.cmbCadCliSexo = cmbCadCliSexo;
		this.tfCadCliData = tfCadCliData;
		this.tfCadCliSenha = tfCadCliSenha;
		this.tfCadCliConfSenha = tfCadCliConfSenha;
		this.btnCadCliFinalizar = btnCadCliFinalizar;
		this.tfCadCliTitulo = tfCadCliTitulo;
		this.tfCadCliCep = tfCadCliCep;
		this.tfCadCliLogradouro = tfCadCliLogradouro;
		this.tfCadCliNumero = tfCadCliNumero;
		this.tfCadCliComplemento = tfCadCliComplemento;
		this.taCadCliInfo = taCadCliInfo;
	}

	@Override
	public void insereCliente(Cliente c, Endereco e) throws ClassNotFoundException, SQLException {
		ClienteDao cDao = new ClienteDao();
		if(!cDao.verificaCpf(c)) {
			cDao.insereUsuario(c);
			cDao.insereCliente(c);
			if(tfCadCliComplemento.getText().isEmpty() && taCadCliInfo.getText().isEmpty()) {
				cDao.insereEnderecoSemInfoCompl(e, c);
			}else if(taCadCliInfo.getText().isEmpty() && !tfCadCliComplemento.getText().isEmpty() ){
				cDao.insereEnderecoSemInfo(e, c);
			}else if(tfCadCliComplemento.getText().isEmpty() && !taCadCliInfo.getText().isEmpty()) {
				cDao.insereEnderecoSemCompl(e, c);
			}else {
				cDao.insereEndereco(e, c);
			}
				
			Alert alertaCpf = new Alert(AlertType.INFORMATION);
			alertaCpf.setTitle("CONFIRMAÇÃO");
			alertaCpf.setHeaderText("Cliente Cadastrado com sucesso");
			alertaCpf.show();
			limpacampos();
		}else {
			Alert alertaCpf = new Alert(AlertType.ERROR);
			alertaCpf.setContentText("Este Cpf ja possui uma conta, efetue o Login");
			alertaCpf.setTitle("ERRO");
			alertaCpf.setHeaderText("Cpf Já cadastrado");
			alertaCpf.show();
		}
	}



	@Override
	public boolean verificaRestricoes() throws ClassNotFoundException, SQLException {

		// Verifica se Cpf possui 11 digitos
		int tamanhoCpf = tfCadCliCpf.getText().length();
		if (tamanhoCpf != 11) {
			Alert alertaCpf = new Alert(AlertType.ERROR);
			alertaCpf.setContentText("Por favor, verifique o número de dígitos");
			alertaCpf.setTitle("ERRO");
			alertaCpf.setHeaderText("Cpf Inválido");
			alertaCpf.show();
			return false;
		}
		
		//Verifica se Cpf so possui valores numericos
		if(!tfCadCliCpf.getText().matches("\\d*")) {
			Alert alertaCep = new Alert(AlertType.ERROR);
			alertaCep.setContentText("Por favor, Insira apenas valores númericos");
			alertaCep.setTitle("ERRO");
			alertaCep.setHeaderText("Cpf Inválido");
			alertaCep.show();
			return false;
		}
		

		// Verifica se Telefone possui 11 digitos
		int tamanhoTel = tfCadCliTel.getText().length();
		if (tamanhoTel != 11) {
			Alert alertaTel = new Alert(AlertType.ERROR);
			alertaTel.setContentText("Por favor, verifique o número de dígitos\nEx.\n11965432345\nApenas números");
			alertaTel.setTitle("ERRO");
			alertaTel.setHeaderText("Telefone Inválido");
			alertaTel.show();
			return false;
		}
		
		//Verifica se Telefone so possui valores numericos
		if(!tfCadCliTel.getText().matches("\\d*")) {
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
			if (tfCadCliEmail.getText().contains(vt[i])) {
				break;
			}
			if (i == 4) {
				Alert alertaEmail = new Alert(AlertType.ERROR);
				alertaEmail.setContentText("Por favor, verifique o domínio do email\nEx.\n@gmail.com\n@icloud.com\n@outlook.com\n@hotmail.com\n@live.com");
				alertaEmail.setTitle("ERRO");
				alertaEmail.setHeaderText("Domínio de email Inválido");
				alertaEmail.show();
				return false;
			}
		}

		// Verifica se senhas sao iguais
		if (!tfCadCliSenha.getText().equals(tfCadCliConfSenha.getText())) {
			Alert alertaSenha = new Alert(AlertType.ERROR);
			alertaSenha.setContentText("Por favor, verifique se elas são idênticas");
			alertaSenha.setTitle("ERRO");
			alertaSenha.setHeaderText("As senhas são diferentes");
			alertaSenha.show();
			return false;
		}

		// Verifica se cep tem 8 digitos
		int tamanhoCep = tfCadCliCep.getText().length();
		if (tamanhoCep != 8) {
			Alert alertaCep = new Alert(AlertType.ERROR);
			alertaCep.setContentText("Por favor, verifique o número de dígitos");
			alertaCep.setTitle("ERRO");
			alertaCep.setHeaderText("Cep Inválido");
			alertaCep.show();
			return false;
		}
		
		//Verifica se Cep so possui valores numericos
		if(!tfCadCliCep.getText().matches("\\d*")) {
			Alert alertaCep = new Alert(AlertType.ERROR);
			alertaCep.setContentText("Por favor, Insira apenas valores númericos");
			alertaCep.setTitle("ERRO");
			alertaCep.setHeaderText("Cep Inválido");
			alertaCep.show();
			return false;
		}
		
		return true;
		
	}

	public boolean verificaData(Cliente c) throws ClassNotFoundException {
		// Verifica se data esta formatada
		String data = tfCadCliData.getText();
		int tamanhoData = data.length();

		if (tamanhoData != 8 && tamanhoData != 10) {
			Alert alertaData = new Alert(AlertType.ERROR);
			alertaData.setContentText("Por favor, verifique o formato aceito ou número de dígitos\nEx.\n01/01/2004\n01012004");
			alertaData.setTitle("ERRO");
			alertaData.setHeaderText("Data Inválida");
			alertaData.show();
			return false;
		}

		String vtdata[] = data.split("");
		if (tamanhoData == 10) {
			if (!vtdata[2].equals("/") || !vtdata[5].equals("/")) {
				Alert alertaData2 = new Alert(AlertType.ERROR);
				alertaData2
						.setContentText("Por favor, verifique o formato aceito ou numero de dígitos\nEx.\n01/01/2004\n01012004");
				alertaData2.setTitle("ERRO");
				alertaData2.setHeaderText("Data Inválida");
				alertaData2.show();
				return false;
			}
		}
		
		DateTimeFormatter formatter;
		if (vtdata[2].equals("/") || vtdata[5].equals("/")) {
			formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		}else {
			formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
		}

		LocalDate dtNasc = LocalDate.parse(tfCadCliData.getText(), formatter);
		LocalDate dataAtual = LocalDate.now();
	    Period periodo = Period.between(dtNasc, dataAtual);
	    
	    if (periodo.getYears() < 18) {
	    	Alert alertaData2 = new Alert(AlertType.ERROR);
			alertaData2.setContentText("O usuário não possui mais de 18 anos");
			alertaData2.setTitle("ERRO");
			alertaData2.setHeaderText("Data Inválida");
			alertaData2.show();
            return false;
        } 

		return true;
	}

	@Override
	public boolean verificaCamposVazios() throws ClassNotFoundException, SQLException {
		if (tfCadCliCpf.getText().isEmpty() || tfCadCliNome.getText().isEmpty() || tfCadCliTel.getText().isEmpty()
				|| tfCadCliData.getText().isEmpty() || tfCadCliEmail.getText().isEmpty()
				|| tfCadCliSenha.getText().isEmpty() || tfCadCliConfSenha.getText().isEmpty()
				|| tfCadCliTitulo.getText().isEmpty() || tfCadCliCep.getText().isEmpty()
				|| tfCadCliLogradouro.getText().isEmpty()) {
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
		tfCadCliCpf.setText("");
		tfCadCliNome.setText("");
		tfCadCliTel.setText("");
		tfCadCliData.setText("");
		tfCadCliEmail.setText("");
		tfCadCliSenha.setText("");
		tfCadCliConfSenha.setText("");
		tfCadCliTitulo.setText("");
		tfCadCliCep.setText("");
		tfCadCliLogradouro.setText("");
		tfCadCliNumero.setText("");
		tfCadCliComplemento.setText("");
		taCadCliInfo.setText("");
	}

}
