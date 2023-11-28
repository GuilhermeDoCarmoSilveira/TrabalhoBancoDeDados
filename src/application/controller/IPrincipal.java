package application.controller;

import java.sql.SQLException;

public interface IPrincipal {
	
	public void listarProdutos() throws ClassNotFoundException, SQLException;
	public void buscaBroduto() throws ClassNotFoundException, SQLException;
	public void calculaSubTotal();
}
