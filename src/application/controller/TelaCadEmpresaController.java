package application.controller;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import application.Principal;
import application.model.Empresa;
import application.model.Endereco;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TelaCadEmpresaController {
	@FXML
	private TextField tfCadEmpCnpj;
	@FXML
	private TextField tfCadEmpRazaoSocial;
	@FXML
	private TextField tfCadEmpApelido;
	@FXML
	private TextField tfCadEmpEmail;
	@FXML
	private PasswordField tfCadEmpSenha;
	@FXML
	private PasswordField tfCadEmpConfSenha;
	@FXML
	private Button btnCadEmpFinalizar;
	@FXML
	private TextField tfCadEmpTitulo;
	@FXML
	private TextField tfCadEmpCep;
	@FXML
	private TextField tfCadEmpLogradouro;
	@FXML
	private TextField tfCadEmpNumero;
	@FXML
	private TextField tfCadEmpComplemento;
	@FXML
	private TextField tfCadEmpTelefone;
	@FXML
	private TextArea taCadEmpInfo;

	@FXML
	public void acaoEmpresa(ActionEvent event) throws ClassNotFoundException, SQLException {

		String cmd = event.getSource().toString();

		EmpresaController ec = new EmpresaController(tfCadEmpCnpj, tfCadEmpRazaoSocial, tfCadEmpApelido, tfCadEmpEmail,
				tfCadEmpSenha, tfCadEmpConfSenha, btnCadEmpFinalizar, tfCadEmpTitulo, tfCadEmpCep, tfCadEmpLogradouro,
				tfCadEmpNumero, tfCadEmpComplemento, tfCadEmpTelefone, taCadEmpInfo);
		
		if(cmd.contains("btnCadEmpVoltar")) {
			Principal.trocaTela("Login");
		}
		
		if (cmd.contains("Finalizar Cadastro")) {
			boolean resultado = ec.verificaCamposVazios();
			if (resultado == true) {
				Empresa emp = new Empresa();
				emp.setCnpj(tfCadEmpCnpj.getText());
				emp.setRazaoSocial(tfCadEmpRazaoSocial.getText());
				emp.setApelido(tfCadEmpApelido.getText());
				emp.setTelefone(tfCadEmpTelefone.getText());
				emp.setEmail(tfCadEmpEmail.getText());
				emp.setSenha(tfCadEmpSenha.getText());

				Endereco e = new Endereco();
				e.setTitulo(tfCadEmpTitulo.getText());
				e.setCep(tfCadEmpCep.getText());
				e.setLogradouro(tfCadEmpLogradouro.getText());
				e.setNumero(Integer.parseInt(tfCadEmpNumero.getText()));
				e.setComplemento(tfCadEmpComplemento.getText());
				e.setInformacoes(taCadEmpInfo.getText());

				try {
					if (ec.verificaRestricoes()) {
						ec.insereEmpresa(emp, e);
						Principal.trocaTela("Login");
					}
				} catch (ClassNotFoundException | SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}

			}
		}
	}
}
