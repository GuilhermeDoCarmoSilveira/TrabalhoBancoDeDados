package application.persintence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.model.Usuario;

public class UsuarioDao implements IUsuarioDao {
	
	private Connection c;
	
	public UsuarioDao() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}
	
	@Override
	public Usuario buscarUsuario(Usuario u) throws SQLException {
		String sql = "SELECT email, senha FROM Usuario WHERE email = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, u.getEmail());
		
		int cont = 0;
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			u.setEmail(rs.getString("email"));
			u.setSenha(rs.getString("senha"));
			cont ++;
		}
		
		if(cont == 0) {
			u = new Usuario();
			u.setEmail("");
			u.setSenha("");
		}
		
		rs.close();
		ps.close();
		return u;
	}

	

}
