package application.persintence;

import java.sql.SQLException;
import java.util.List;

import application.model.Produto;
import application.model.Usuario;

public interface IProdutoDao {
	public void insereProduto(Produto p, Usuario u) throws SQLException;
	public void atualizaProduto(Produto p, Usuario u, String prodAntigo) throws SQLException;
	public void excluiProduto(Produto p, Usuario u) throws SQLException;
	public Produto buscaProduto(Produto p, Usuario u) throws SQLException;
	public List<Produto> listaProduto( Usuario u) throws SQLException;
}
