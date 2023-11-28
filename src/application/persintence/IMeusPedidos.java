package application.persintence;

import java.sql.SQLException;
import java.util.List;

import application.model.Produto;
import application.model.Usuario;

public interface IMeusPedidos {
	public List<String> listaPedido( Usuario u) throws SQLException;

	public boolean verificaTipoUsuario(Usuario u) throws SQLException;
}
