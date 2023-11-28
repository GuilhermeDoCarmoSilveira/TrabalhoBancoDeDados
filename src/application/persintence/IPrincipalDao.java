package application.persintence;

import java.sql.SQLException;
import java.util.List;

import application.model.Produto;

public interface IPrincipalDao {

	public List<String> listaProdutos() throws  SQLException;
	public List<String> buscaProdutos(String pesquisa) throws  SQLException;
	
}
