package application.persintence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.model.Usuario;

public class EsqueceuSenhaDao implements IEsqueceuSenhaDao {

	private Connection c;

	public EsqueceuSenhaDao() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}

	@Override
	public boolean atualizarSenha(Usuario u) throws ClassNotFoundException, SQLException {
		String sqlselect = "SELECT email FROM Usuario where email = ? ";
		PreparedStatement psSelect = c.prepareStatement(sqlselect);
		psSelect.setString(1, u.getEmail());

		ResultSet rs = psSelect.executeQuery();

		if (rs.next()) {
			String sql = "UPDATE Usuario SET senha = ? WHERE email = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, u.getSenha());
			ps.setString(2, u.getEmail());

			ps.execute();
			ps.close();
			return true;
		} else {
			return false;
		}

	}

}
