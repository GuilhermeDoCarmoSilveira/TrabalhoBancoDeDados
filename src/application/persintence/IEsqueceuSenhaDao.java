package application.persintence;

import java.sql.SQLException;

import application.model.Usuario;

public interface IEsqueceuSenhaDao {
	
	public boolean atualizarSenha(Usuario u) throws ClassNotFoundException, SQLException;

}
