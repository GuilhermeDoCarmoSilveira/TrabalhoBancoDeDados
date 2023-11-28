package application.persintence;

import java.sql.SQLException;

import application.model.Usuario;

public interface IUsuarioDao {
	
	public Usuario buscarUsuario(Usuario u) throws SQLException;
	
}
