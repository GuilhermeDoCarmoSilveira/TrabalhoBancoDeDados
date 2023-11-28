package application.persintence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;

import application.model.Cliente;
import application.model.Empresa;
import application.model.Usuario;

public class AtualizaDadosDao implements IAtualizaDados {

	private Connection c;

	public AtualizaDadosDao() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}

	@Override
	public void atualizaCLiente(Cliente cl, Usuario u, Usuario uAntigo) throws SQLException {
		String sql = "Update Cliente Set nome = ?, telefone = ?  Where id_usuario = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, cl.getNome());
		ps.setString(2, cl.getTelefone());
		ps.setInt(3, buscaIdUsuario(uAntigo));

		ps.execute();
		ps.close();
		
		atualizaUsuario(uAntigo, u);

	}

	@Override
	public void atualizaEmpresa(Empresa emp, Usuario u, Usuario uAntigo) throws SQLException {
		String sql = "Update Empresa Set razaoSocial = ?, apelido = ?, telefone = ?  Where id_usuario = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, emp.getRazaoSocial());
		ps.setString(2, emp.getApelido());
		ps.setString(3, emp.getTelefone());
		ps.setInt(4, buscaIdUsuario(uAntigo));
		
		ps.execute();
		ps.close();
		
		atualizaUsuario(uAntigo, u);

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

	private void atualizaUsuario(Usuario uAntigo, Usuario uAtualizado ) throws SQLException {
		String sql = "Update Usuario Set email= ?, senha = ?  Where id_usuario = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, uAtualizado.getEmail());
		ps.setString(2, uAtualizado.getSenha());
		ps.setInt(3, buscaIdUsuario(uAntigo));

		ps.execute();
		ps.close();
	}
}
