package application.controller;

import java.sql.SQLException;

import application.model.Produto;

public interface IProdutoController {
	public void insereProduto(Produto p) throws ClassNotFoundException, SQLException;
	public void atualizaProduto(Produto p, String prodAntigo) throws ClassNotFoundException, SQLException;
	public void excluiProduto(Produto p) throws ClassNotFoundException, SQLException;
	public void buscaProduto(Produto p) throws ClassNotFoundException, SQLException;
	public void listaProduto() throws ClassNotFoundException, SQLException;
}
