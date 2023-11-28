package application.controller;

import java.sql.SQLException;
import java.util.List;

import application.model.Produto;
import application.model.Usuario;
import application.persintence.ProdutoDao;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ProdutoController implements IProdutoController {

	private TextField tfProdutosNome;
	private TextField tfProdutosPreco;
	private ComboBox cbProdutosCategoria;
	private TextField tfProdutosQtd;
	private TextArea taProdutosInformacoes;
	private TextArea taProdutos;

	private Usuario u;

	public ProdutoController(TextField tfProdutosNome, TextField tfProdutosPreco, ComboBox cbProdutosCategoria,
			TextField tfProdutosQtd, TextArea taProdutosInformacoes, TextArea taProdutos, Usuario u) {
		super();
		this.tfProdutosNome = tfProdutosNome;
		this.tfProdutosPreco = tfProdutosPreco;
		this.cbProdutosCategoria = cbProdutosCategoria;
		this.tfProdutosQtd = tfProdutosQtd;
		this.taProdutosInformacoes = taProdutosInformacoes;
		this.taProdutos = taProdutos;
		this.u = u;
	}

	@Override
	public void insereProduto(Produto p) throws ClassNotFoundException, SQLException {
		ProdutoDao pDao = new ProdutoDao();
		pDao.insereProduto(p, u);
		limpaCampos();
		listaProduto();

	}

	@Override
	public void atualizaProduto(Produto p, String prodAntigo) throws ClassNotFoundException, SQLException {
		ProdutoDao pDao = new ProdutoDao();
		pDao.atualizaProduto(p, u, prodAntigo);
		limpaCampos();
		listaProduto();
	}

	@Override
	public void excluiProduto(Produto p) throws ClassNotFoundException, SQLException {
		ProdutoDao pDao = new ProdutoDao();
		pDao.excluiProduto(p, u);
		limpaCampos();
		listaProduto();
	}

	@Override
	public void buscaProduto(Produto p) throws ClassNotFoundException, SQLException {
		ProdutoDao pDao = new ProdutoDao();
		p = pDao.buscaProduto(p, u);

		tfProdutosNome.setText(p.getNome());
		tfProdutosPreco.setText(String.valueOf(p.getPreco()));
		tfProdutosQtd.setText(String.valueOf(p.getQuantidade()));
		taProdutosInformacoes.setText(p.getDescricao());
		cbProdutosCategoria.setValue(p.getCategoria());
	}

	@Override
	public void listaProduto() throws ClassNotFoundException, SQLException {
		limpaCampos();
		taProdutos.setText("");

		ProdutoDao pDao = new ProdutoDao();
		List<Produto> listaProduto = pDao.listaProduto(u);
		StringBuffer buffer = new StringBuffer();
		buffer.append("Nome\t\tQuantidade\t\tCategoria\t\tPreco\t\tDataCadastro\t\tDescricao\t\tStatus\t\t\n");
		for (Produto p : listaProduto) {
			buffer.append(p.getNome() + "\t\t" + p.getQuantidade() + "\t\t" + p.getCategoria() + "\t\t" + p.getPreco()
					+ "\t\t" + p.getDtCadastro() + "\t\t" + p.getDescricao() + "\t\t" + p.getStatus() + "\t\t\n");
		}
		taProdutos.setText(buffer.toString());
	}

	private void limpaCampos() {
		tfProdutosPreco.setText("");
		tfProdutosQtd.setText("");
		tfProdutosNome.setText("");
		taProdutosInformacoes.setText("");
	}

	public boolean verificaCamposVazio() {
		if (tfProdutosNome.getText().isEmpty() || tfProdutosPreco.getText().isEmpty()
				|| tfProdutosQtd.getText().isEmpty() ||  taProdutosInformacoes.getText().isEmpty()) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setContentText("Verifique se possui campos vazio");
			alerta.setTitle("ERRO");
			alerta.show();
			return false;
		} else {
			return true;
		}
	}
}
