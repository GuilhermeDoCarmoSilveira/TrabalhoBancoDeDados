package application.controller;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import application.Principal;
import application.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TelaPrincipalController  {
	@FXML
	private TextField tfPesquisar;
	@FXML
	private Button btnPesuisarProduto;
	@FXML
	private Button btnPrincipalMeuPerfil;
	@FXML
	private Label lbGrid1Produtos;
	@FXML
	private Label lbGrid2Produtos;
	@FXML
	private Label lbGrid3Produtos;
	@FXML
	private Label lbGrid4Produtos;
	@FXML
	private Label lbGrid5Produtos;
	@FXML
	private Label lbGrid6Produtos;
	@FXML
	private Button btnPrincipalAddCar1;
	@FXML
	private Button btnPrincipalAddCar2;
	@FXML
	private Button btnPrincipalAddCar3;
	@FXML
	private Button btnPrincipalAddCar4;
	@FXML
	private Button btnPrincipalAddCar41;
	@FXML
	private Button btnPrincipalAddCar6;
	@FXML
	private TextArea taListaCarrinho;
	@FXML
	private Label lbCarrinhoSubTotal;
	@FXML
	private Button btnCarrinhoFinalizar;
	private Usuario u;

	// Event Listener on Button[#btnPesuisarProduto].onAction
	@FXML
	public void acaoPrincipal(ActionEvent event) {
		String cmd = event.getSource().toString();
		u = Principal.pegoUsuario();
		PrincipalController pc = new PrincipalController(tfPesquisar, lbGrid1Produtos, lbGrid2Produtos, lbGrid3Produtos, lbGrid4Produtos, lbGrid5Produtos, lbGrid6Produtos, taListaCarrinho, lbCarrinhoSubTotal,u);
		
		if(cmd.contains("btnPesuisarProduto")) {
			if(tfPesquisar.getText().isEmpty()) {
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("ERRO");
				alerta.setHeaderText("Campo de pesquisa vazio");
				alerta.show();
			} else {
				try {
					pc.buscaBroduto();
				} catch (ClassNotFoundException | SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
		}
		
		if(cmd.contains("btnPrincipalAddCar1")) {
			taListaCarrinho.appendText(lbGrid1Produtos.getText());
		}
		if(cmd.contains("btnPrincipalAddCar2")) {
			taListaCarrinho.appendText(lbGrid2Produtos.getText());
		}
		if(cmd.contains("btnPrincipalAddCar3")) {
			taListaCarrinho.appendText(lbGrid3Produtos.getText());
		}
		if(cmd.contains("btnPrincipalAddCar4")) {
			taListaCarrinho.appendText(lbGrid4Produtos.getText());
		}
		if(cmd.contains("btnPrincipalAddCar5")) {
			taListaCarrinho.appendText(lbGrid5Produtos.getText());
		}
		if(cmd.contains("btnPrincipalAddCar6")) {
			taListaCarrinho.appendText(lbGrid6Produtos.getText());
		}
	
		if(cmd.contains("btnRecomendaProduto")) {
			try {
				pc.listarProdutos();
			} catch (ClassNotFoundException | SQLException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}
	}
	// Event Listener on Button[#btnPrincipalMeuPerfil].onAction
	@FXML
	public void acaoMeuPerfil(ActionEvent event) {
		Principal.trocaTela("MeuPerfil");
	}
	// Event Listener on Button[#btnCarrinhoFinalizar].onAction
	@FXML
	public void acaoCarrinho(ActionEvent event) {
		String cmd = event.getSource().toString();
		
		u = Principal.pegoUsuario();
		PrincipalController pc = new PrincipalController(tfPesquisar, lbGrid1Produtos, lbGrid2Produtos, lbGrid3Produtos, lbGrid4Produtos, lbGrid5Produtos, lbGrid6Produtos, taListaCarrinho, lbCarrinhoSubTotal, u);
	
		if(cmd.contains("Calcular Sub Total")) {
			pc.calculaSubTotal();
		}
		
		if(cmd.contains("Limpar Carrinho")) {
			taListaCarrinho.setText("");
			lbCarrinhoSubTotal.setText("");
		}
		
		if(cmd.contains("Finalizar Pedido")) {
			try {
				pc.manipulaPedido();
			} catch (ClassNotFoundException | SQLException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}
	}
}
