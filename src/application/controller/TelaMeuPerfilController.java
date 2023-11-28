package application.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import application.Principal;
import application.model.Endereco;
import application.model.Produto;
import application.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TelaMeuPerfilController implements Initializable {
	@FXML
	private Button btnDadosAtualizaDados;
	@FXML
	private TextArea taMeusDadosInfo;
	@FXML
	private Button btnMeuPerfilVoltar;
	@FXML
	private TextField tfEnderecosTitulo;
	@FXML
	private Button btnEnderecosBuscar;
	@FXML
	private TextField tfEnderecosCep;
	@FXML
	private TextField tfEnderecosLoug;
	@FXML
	private TextField tfEnderecosNum;
	@FXML
	private TextField tfEnderecosCompl;
	@FXML
	private TextArea taEnderecosInformacoes;
	@FXML
	private Button btnEnderecosAdicionar;
	@FXML
	private Button btnEnderecosAlterar;
	@FXML
	private Button btnEnderecosExcluir;
	@FXML
	private Button btnEnderecosListar;
	@FXML
	private TextArea taEnderecos;
	@FXML
	private Button btnEnderecosVoltar;
	@FXML
	private TextField tfProdutosNome;
	@FXML
	private Button btnProdutosBuscar;
	@FXML
	private TextField tfProdutosPreco;
	@FXML
	private ComboBox cbProdutosCategoria;
	@FXML
	private TextArea taProdutosInformacoes;
	@FXML
	private Button btnProdutosAdicionar;
	@FXML
	private Button btnProdutosAlterar;
	@FXML
	private Button btnProdutosExcluir;
	@FXML
	private Button btnProdutosListar;
	@FXML
	private TextArea taProdutos;
	@FXML
	private TextField tfProdutosQtd;
	@FXML
	private Button btnMeusProdutosVoltar;
	@FXML
	private TextArea taMeusPedidos;

	private Usuario u;
	
	private String prodAntigo;
	
	private String cepAntigo;
	private int numAntigo;

	// Event Listener on Tab.onSelectionChanged
	@FXML
	public void acaoMeusDados(Event event) {
		String cmd = event.getSource().toString();
		u = Principal.pegoUsuario();
		MeusDadosController m = new MeusDadosController(taMeusDadosInfo, u);
		try {
			m.buscaDados();
		} catch (ClassNotFoundException | SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}

	}

	// Event Listener on Button[#btnDadosAtualizaDados].onAction
	@FXML
	public void acaoDados(ActionEvent event) {
		String cmd = event.getSource().toString();
		MeusDadosController m = new MeusDadosController(taMeusDadosInfo, u);
		
		if(cmd.contains("Sair")) {
			Principal.trocaTela("Login");
		}
		
		if(cmd.contains("btnMeuPerfilVoltar") || cmd.contains("btnEnderecosVoltar") || cmd.contains("btnMeusProdutosVoltar")) {
			Principal.trocaTela("Principal");
		}
		
		if(cmd.contains("btnDadosAtualizaDados")) {
			try {
				if (m.verificaTipoUsuario()) {
					Principal.trocaTela("AtualizaCli");
				} else {
					Principal.trocaTela("AtualizaEmp");
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	// Event Listener on Button[#btnEnderecosListar].onAction
	@FXML
	public void acaoEnderecos(ActionEvent event) {
		String cmd = event.getSource().toString();
		u = Principal.pegoUsuario();
		EnderecoController end = new EnderecoController(tfEnderecosCep, tfEnderecosLoug, tfEnderecosNum,
				tfEnderecosCompl, taEnderecosInformacoes, taEnderecos, u, tfEnderecosTitulo);

		if (cmd.contains("Adicionar")) {
			try {
				if (end.verificaRestricoes()) {
					Endereco e = new Endereco();
					e.setCep(tfEnderecosCep.getText());
					e.setComplemento(tfEnderecosCompl.getText());
					e.setInformacoes(taEnderecosInformacoes.getText());
					e.setLogradouro(tfEnderecosLoug.getText());
					e.setNumero(Integer.parseInt(tfEnderecosNum.getText()));
					e.setTitulo(tfEnderecosTitulo.getText());

					end.insereEndereco(e);
				}
			} catch (ClassNotFoundException | SQLException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}

		if (cmd.contains("Alterar")) {
			try {
				if (end.verificaRestricoes()) {
					Endereco e = new Endereco();
					e.setCep(tfEnderecosCep.getText());
					e.setComplemento(tfEnderecosCompl.getText());
					e.setInformacoes(taEnderecosInformacoes.getText());
					e.setLogradouro(tfEnderecosLoug.getText());
					e.setNumero(Integer.parseInt(tfEnderecosNum.getText()));
					e.setTitulo(tfEnderecosTitulo.getText());

					end.atualizaEndereco(e, numAntigo, cepAntigo);
				}
			} catch (ClassNotFoundException | SQLException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}

		if (cmd.contains("Buscar")) {
			try {
				if (!tfEnderecosTitulo.getText().equals("")) {
					Endereco e = new Endereco();;
					e.setTitulo(tfEnderecosTitulo.getText());
					end.buscaEndereco(e);
					numAntigo = Integer.parseInt(tfEnderecosNum.getText());
					cepAntigo = tfEnderecosCep.getText();
				}
			} catch (ClassNotFoundException | SQLException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}

		if (cmd.contains("Excluir")) {
			try {
				if (end.verificaRestricoes()) {
					Endereco e = new Endereco();
					e.setCep(tfEnderecosCep.getText());
					e.setComplemento(tfEnderecosCompl.getText());
					e.setInformacoes(taEnderecos.getText());
					e.setLogradouro(tfEnderecosLoug.getText());
					e.setNumero(Integer.parseInt(tfEnderecosNum.getText()));
					e.setTitulo(tfEnderecosTitulo.getText());

					end.excluiEndereco(e);
				}
			} catch (ClassNotFoundException | SQLException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}

		if (cmd.contains("Listar")) {
			try {
				end.listaEndereco();

			} catch (ClassNotFoundException | SQLException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}

	}

	// Event Listener on Button[#btnProdutosListar].onAction
	@FXML
	public void acaoProdutos(ActionEvent event) {
		String cmd = event.getSource().toString();
		u = Principal.pegoUsuario();
		ProdutoController prod = new ProdutoController(tfProdutosNome, tfProdutosPreco, cbProdutosCategoria,
				tfProdutosQtd, taProdutosInformacoes, taProdutos, u);
		if (cmd.contains("Adicionar")) {
			if (prod.verificaCamposVazio()) {
				Produto p = new Produto();
				p.setNome(tfProdutosNome.getText());
				p.setDescricao(taProdutosInformacoes.getText());
				p.setPreco(Double.parseDouble(tfProdutosPreco.getText().replace(",", ".")));
				
				if(Integer.parseInt(tfProdutosQtd.getText())<=0) {
					p.setQuantidade(Integer.parseInt(tfProdutosQtd.getText()));
					p.setStatus("I");
				}else{
					p.setQuantidade(Integer.parseInt(tfProdutosQtd.getText()));
					p.setStatus("D");
				}
				p.setCategoria(cbProdutosCategoria.getSelectionModel().getSelectedItem().toString());
				
				try {
					prod.insereProduto(p);
				} catch (ClassNotFoundException | SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
		}
		if (cmd.contains("Buscar")) {
			if (!tfProdutosNome.getText().isEmpty()) {
				Produto p = new Produto();
				p.setNome(tfProdutosNome.getText());
				prodAntigo = tfProdutosNome.getText();
				try {
					prod.buscaProduto(p);
				} catch (ClassNotFoundException | SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			} else {
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setContentText("Verifique se o nome do produto é valido");
				alerta.setTitle("ERRO");
				alerta.show();
			}
		}

		if (cmd.contains("Listar")) {
			try {
				prod.listaProduto();
			} catch (ClassNotFoundException | SQLException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}

		if (cmd.contains("Alterar")) {
			if (prod.verificaCamposVazio()) {
				Produto p = new Produto();
				p.setNome(tfProdutosNome.getText());
				p.setDescricao(taProdutosInformacoes.getText());
				p.setPreco(Double.parseDouble(tfProdutosPreco.getText().replace(",", ".")));
				
				if(Integer.parseInt(tfProdutosQtd.getText())<=0) {
					p.setQuantidade(Integer.parseInt(tfProdutosQtd.getText()));
					p.setStatus("I");
				}else{
					p.setQuantidade(Integer.parseInt(tfProdutosQtd.getText()));
					p.setStatus("D");
				}
				
				p.setCategoria(cbProdutosCategoria.getSelectionModel().getSelectedItem().toString());
				try {
					prod.atualizaProduto(p, prodAntigo);
				} catch (ClassNotFoundException | SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
		}

		if (cmd.contains("Excluir")) {
			if (!tfProdutosNome.getText().isEmpty()) {
				Produto p = new Produto();
				p.setNome(tfProdutosNome.getText());
				try {
					prod.excluiProduto(p);
				} catch (ClassNotFoundException | SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			} else {
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setContentText("Verifique se o nome do produto é valido");
				alerta.setTitle("ERRO");
				alerta.show();
			}
		}

	}
	
	@FXML
	public void acaoMeusPedidos(Event event) {
		MeusPedidosController m = new MeusPedidosController(taMeusPedidos, Principal.pegoUsuario());
		String cmd = event.getSource().toString();
		if(cmd.contains("Listar")) {
			try {
				m.listaPedido();
			} catch (ClassNotFoundException | SQLException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}
		
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> itens = FXCollections.observableArrayList("Vestuario", "Esporte",
				"Ferramentas", "Estudos", "Eletrônicos", "Moda", "Casa e Jardim", "Esportes e Fitness", "Brinquedos e Hobbies",
				"Automotivo", "Saúde e Beleza", "Livraria e Papelaria", "Joias e Relógios", 
				"Colecionáveis e Antiguidades", "Animais de Estimação", "Instrumentos Musicais");
		cbProdutosCategoria.setItems(itens);
	}
}
