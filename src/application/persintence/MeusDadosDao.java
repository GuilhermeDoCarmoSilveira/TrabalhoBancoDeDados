package application.persintence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.model.Cliente;
import application.model.Empresa;
import application.model.Usuario;

public class MeusDadosDao implements IMeusDadosDao {

	private Connection c;

	public MeusDadosDao() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}

	@Override
	public boolean verificaTipoUsuario(Usuario u) throws SQLException {
		String sql = "SELECT id_usuario FROM Cliente Where id_usuario = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, buscaIdUsuario(u));
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			rs.close();
			ps.close();
			return true;
		} else {
			rs.close();
			ps.close();
			return false;
		}
	}

	@Override
	public <T> T buscaDadosUsuario(Usuario u, boolean resul) throws SQLException {
		if (resul) {
			String sql = "SELECT cpf, nome, telefone, Convert(char(10),dataNascimento,103) as dtnasc, "
                    + "Case when (sexo='M') then 'Masculino'"
                    + "else 'Feminino'"
                    + "end as sex "
                    + "FROM Cliente Where id_usuario = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, buscaIdUsuario(u));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Cliente cl = new Cliente();
				cl.setCpf(rs.getString("cpf"));
				cl.setNome(rs.getString("nome"));
				cl.setTelefone(rs.getString("telefone"));
				cl.setDtNascimento(rs.getString("dtnasc"));
				cl.setSexo(rs.getString("sex"));
				cl.setEmail(u.getEmail());
				cl.setSenha(u.getSenha());

				rs.close();
				ps.close();
				return (T) cl;
			}
		} else {
			String sql = "SELECT cnpj, razaoSocial, telefone, apelido FROM Empresa Where id_usuario = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, buscaIdUsuario(u));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Empresa emp = new Empresa();
				emp.setCnpj(rs.getString("cnpj"));
				emp.setRazaoSocial(rs.getString("razaoSocial"));
				emp.setApelido(rs.getString("apelido"));
				emp.setTelefone(rs.getString("telefone"));
				emp.setEmail(u.getEmail());
				emp.setSenha(u.getSenha());

				rs.close();
				ps.close();
				return (T) emp;
			}
		}
		return null;

	}

	private int buscaIdUsuario(Usuario u) throws SQLException {
		int id = -1;

		String sql = "SELECT id_usuario FROM Usuario Where email = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, u.getEmail());

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			id = rs.getInt("id_usuario");
		}

		rs.close();
		ps.close();

		return id;
	}

}
