package application.persintence;

import java.sql.SQLException;
import java.util.ArrayList;

import application.model.Usuario;

public interface IMeusDadosDao {
	public boolean verificaTipoUsuario(Usuario u) throws SQLException;

	public <T> T buscaDadosUsuario(Usuario u, boolean resul) throws SQLException;
}
