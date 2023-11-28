package application.controller;

import java.sql.SQLException;
import java.util.List;

import application.model.Pedido;
import application.model.Usuario;
import application.persintence.PedidoDao;
import application.persintence.PrincipalDao;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PrincipalController implements IPrincipal {

	private TextField tfPesquisar;
	private Label lbGrid1Produtos;
	private Label lbGrid2Produtos;
	private Label lbGrid3Produtos;
	private Label lbGrid4Produtos;
	private Label lbGrid5Produtos;
	private Label lbGrid6Produtos;
	private TextArea taListaCarrinho;
	private Label lbValorSubTotal;
	private Double subTotal = 0.0;
	private Usuario u;

	public PrincipalController(TextField tfPesquisar, Label lbGrid1Produtos, Label lbGrid2Produtos,
			Label lbGrid3Produtos, Label lbGrid4Produtos, Label lbGrid5Produtos, Label lbGrid6Produtos,
			TextArea taListaCarrinho, Label lbValorSubTotal, Usuario u) {
		super();
		this.tfPesquisar = tfPesquisar;
		this.lbGrid1Produtos = lbGrid1Produtos;
		this.lbGrid2Produtos = lbGrid2Produtos;
		this.lbGrid3Produtos = lbGrid3Produtos;
		this.lbGrid4Produtos = lbGrid4Produtos;
		this.lbGrid5Produtos = lbGrid5Produtos;
		this.lbGrid6Produtos = lbGrid6Produtos;
		this.taListaCarrinho = taListaCarrinho;
		this.lbValorSubTotal = lbValorSubTotal;
		this.u = u;
	}

	@Override
	public void listarProdutos() throws ClassNotFoundException, SQLException {
		PrincipalDao pDao = new PrincipalDao();

		List<String> lp = pDao.listaProdutos();

		int tam = lp.size();

		if (!lp.isEmpty()) {
			if (tam >= 1) {
				lbGrid1Produtos.setText(lp.get(0));
			}
			if (tam >= 2) {
				lbGrid2Produtos.setText(lp.get(1));
			}
			if (tam >= 3) {
				lbGrid3Produtos.setText(lp.get(2));
			}
			if (tam >= 4) {
				lbGrid4Produtos.setText(lp.get(3));
			}
			if (tam >= 5) {
				lbGrid5Produtos.setText(lp.get(4));
			}
			if (tam == 6) {
				lbGrid6Produtos.setText(lp.get(5));
			}
		}
	}

	@Override
	public void buscaBroduto() throws ClassNotFoundException, SQLException {
		limpaCampos();

		PrincipalDao pDao = new PrincipalDao();

		List<String> lp = pDao.buscaProdutos(tfPesquisar.getText());

		int tam = lp.size();

		if (!lp.isEmpty()) {
			if (tam >= 1) {
				lbGrid1Produtos.setText(lp.get(0));
			}
			if (tam >= 2) {
				lbGrid2Produtos.setText(lp.get(1));
			}
			if (tam >= 3) {
				lbGrid3Produtos.setText(lp.get(2));
			}
			if (tam >= 4) {
				lbGrid4Produtos.setText(lp.get(3));
			}
			if (tam >= 5) {
				lbGrid5Produtos.setText(lp.get(4));
			}
			if (tam == 6) {
				lbGrid6Produtos.setText(lp.get(5));
			}
		}
	}

	private void limpaCampos() {
		lbGrid1Produtos.setText("");
		lbGrid2Produtos.setText("");
		lbGrid3Produtos.setText("");
		lbGrid4Produtos.setText("");
		lbGrid5Produtos.setText("");
		lbGrid6Produtos.setText("");
	}

	@Override
	public void calculaSubTotal() {
		if (!taListaCarrinho.getText().isEmpty()) {
			String vtCarrinho[] = taListaCarrinho.getText().split("\n");

			for (String s : vtCarrinho) {
				if (s.contains("R$")) {
					String vtPreco[] = s.split(" ");
					subTotal += Double.parseDouble(vtPreco[1]);
				}
			}

			lbValorSubTotal.setText(subTotal.toString());
		} else {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("ERRO");
			alerta.setHeaderText("Carrinho vazio");
			alerta.show();
		}
	}

	public void manipulaPedido() throws ClassNotFoundException, SQLException {
		PedidoDao pDao = new PedidoDao();
		String vtCarrinho[] = taListaCarrinho.getText().split("\n");

		int tam = vtCarrinho.length;

		int cont = 0;
		int numPed = pDao.buscaNumPedido();
		boolean resultado = true;
		int qtd = 0;

		for (int i = 0; i < tam; i++) {
			cont++;
			if (cont == 3) {
				qtd++;
				Double valorProd = Double.parseDouble(vtCarrinho[i - 1].substring(3));
				Pedido p = new Pedido();
				if (!pDao.verificaQtdProduto(vtCarrinho[i - 2], vtCarrinho[i], qtd)) {
					resultado = false;
					Alert alerta = new Alert(AlertType.ERROR);
					alerta.setTitle("Pedido cancelado");
					alerta.setHeaderText("O Produto " + vtCarrinho[i - 2] +  " Tem apenas "
							+ pDao.recebeQuantidade(vtCarrinho[i - 2], vtCarrinho[i]) + " Unidade(s)");
					alerta.show();
					taListaCarrinho.setText("");
					break;
				}
				cont = 0;
				i++;
			}
		}

		if (resultado) {
			for (int z = 0; z < tam; z++) {
				cont++;
				if (cont == 3) {
					Double valorProd = Double.parseDouble(vtCarrinho[z - 1].substring(3));
					Pedido p = new Pedido();
					p.setNumero(numPed);
					p.setValorTotal(valorProd);

					pDao.vericaProduto(u, vtCarrinho[z - 2], vtCarrinho[z], p.getValorTotal(), p.getNumero());
					pDao.atualizaQtd(vtCarrinho[z - 2],  vtCarrinho[z]);
					cont = 0;
					z++;
				}
			}
			Alert alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Pedido confirmado");
			alerta.setHeaderText("Seu pedido foi registrado com sucesso, para consultar o mesmo, acesse meus pedidos");
			alerta.show();
			taListaCarrinho.setText("");
			lbValorSubTotal.setText("");
		}

	}

}
