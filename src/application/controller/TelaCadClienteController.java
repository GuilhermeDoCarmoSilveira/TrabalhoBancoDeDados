package application.controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import application.Principal;
import application.model.Cliente;
import application.model.Endereco;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public class TelaCadClienteController implements Initializable {
	@FXML
	private TextField tfCadCliCpf;
	@FXML
	private TextField tfCadCliNome;
	@FXML
	private TextField tfCadCliTel;
	@FXML
	private TextField tfCadCliEmail;
	@FXML
	private ComboBox cmbCadCliSexo;
	@FXML
	private TextField tfCadCliData;
	@FXML
	private PasswordField tfCadCliSenha;
	@FXML
	private PasswordField tfCadCliConfSenha;
	@FXML
	private Button btnCadCliFinalizar;
	@FXML
	private TextField tfCadCliTitulo;
	@FXML
	private TextField tfCadCliCep;
	@FXML
	private TextField tfCadCliLogradouro;
	@FXML
	private TextField tfCadCliNumero;
	@FXML
	private TextField tfCadCliComplemento;
	@FXML
	private TextArea taCadCliInfo;

	@FXML
	public void acaoCliente(ActionEvent event) throws ClassNotFoundException, SQLException {

		String cmd = event.getSource().toString();
		
		ClienteController cc = new ClienteController(tfCadCliCpf, tfCadCliNome, tfCadCliTel, tfCadCliEmail,
				cmbCadCliSexo, tfCadCliData, tfCadCliSenha, tfCadCliConfSenha, btnCadCliFinalizar, tfCadCliTitulo,
				tfCadCliCep, tfCadCliLogradouro, tfCadCliNumero, tfCadCliComplemento, taCadCliInfo);
		
		if(cmd.contains("btnCadCliVoltar")) {
			Principal.trocaTela("Login");
		}
		
		if (cmd.contains("Finalizar Cadastro")) {
			boolean resultado = cc.verificaCamposVazios();
			if (resultado == true) {
				Cliente c = new Cliente();
				c.setCpf(tfCadCliCpf.getText());
				c.setNome(tfCadCliNome.getText());
				c.setTelefone(tfCadCliTel.getText());
				if (cmbCadCliSexo.getSelectionModel().getSelectedItem().toString().equals("Masculino")) {
					c.setSexo("M");
				}
				if (cmbCadCliSexo.getSelectionModel().getSelectedItem().toString().equals("Feminino")) {
					c.setSexo("F");
				}
				if (cc.verificaData(c)) {
					c.setDtNascimento(tfCadCliData.getText());
					c.setEmail(tfCadCliEmail.getText());
					c.setSenha(tfCadCliSenha.getText());

					Endereco e = new Endereco();
					e.setTitulo(tfCadCliTitulo.getText());
					e.setCep(tfCadCliCep.getText());
					e.setLogradouro(tfCadCliLogradouro.getText());
					e.setNumero(Integer.parseInt(tfCadCliNumero.getText()));
					e.setComplemento(tfCadCliComplemento.getText());
					e.setInformacoes(taCadCliInfo.getText());

					try {
						if (cc.verificaRestricoes()) {
							cc.insereCliente(c, e);
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> itens = FXCollections.observableArrayList("Masculino", "Feminino");
		cmbCadCliSexo.setItems(itens);
	}
}
