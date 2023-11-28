package application.persintence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.model.Endereco;
import application.model.Produto;
import application.model.Usuario;

public class EnderecoDao implements IEnderecoDao {

	private Connection c;

	public EnderecoDao() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}

	@Override

	public void insereEndereco(Endereco e, Usuario u) throws SQLException {
		// Inserindo todas as informações
		if (!e.getComplemento().equals("") && !e.getInformacoes().equals("")) {
			String sql = "INSERT INTO Endereco Values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, e.getCep());
			ps.setInt(2, e.getNumero());
			ps.setInt(3, buscaIdUsuario(u));
			ps.setString(4, e.getTitulo());
			ps.setString(5, e.getLogradouro());
			ps.setString(6, e.getComplemento());
			ps.setString(7, e.getInformacoes());

			ps.execute();
			ps.close();
		}

		// Inserindo apenas com complemento
		if (!e.getComplemento().equals("") & e.getInformacoes().equals("")) {
			String sql = "INSERT INTO Endereco (cep, numero, id_usuario, titulo, logradouro, complemento) Values (?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, e.getCep());
			ps.setInt(2, e.getNumero());
			ps.setInt(3, buscaIdUsuario(u));
			ps.setString(4, e.getTitulo());
			ps.setString(5, e.getLogradouro());
			ps.setString(6, e.getComplemento());

			ps.execute();
			ps.close();
		}
		// Inserindo apenas com informacoes
		if (!e.getInformacoes().equals("") & e.getComplemento().equals("")) {
			String sql = "INSERT INTO Endereco (cep, numero, id_usuario, titulo, logradouro, informacao) Values (?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, e.getCep());
			ps.setInt(2, e.getNumero());
			ps.setInt(3, buscaIdUsuario(u));
			ps.setString(4, e.getTitulo());
			ps.setString(5, e.getLogradouro());
			ps.setString(6, e.getInformacoes());

			ps.execute();
			ps.close();
		}
		// Inserindo sem info e complemento
		if (e.getInformacoes().equals("") & e.getComplemento().equals("")) {
			String sql = "INSERT INTO Endereco (cep, numero, id_usuario, titulo, logradouro) Values (?, ?, ?, ?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, e.getCep());
			ps.setInt(2, e.getNumero());
			ps.setInt(3, buscaIdUsuario(u));
			ps.setString(4, e.getTitulo());
			ps.setString(5, e.getLogradouro());

			ps.execute();
			ps.close();
		}
	}

	@Override
	public void atualizaEndereco(Endereco e, Usuario u, int numAntigo, String cepAntigo) throws SQLException {
		
		// Atualizando todas as informações
		if (!e.getComplemento().equals("") && !e.getInformacoes().equals("")) {
			String sql = "Update Endereco Set cep = ?, numero = ?, logradouro = ?, titulo = ?, complemento = ?, informacao = ? "
					+ "Where cep = ? and numero = ? and id_usuario = ?  ";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, e.getCep());
			ps.setInt(2, e.getNumero());
			ps.setString(3, e.getLogradouro());
			ps.setString(4, e.getTitulo());
			ps.setString(5, e.getComplemento());
			ps.setString(6, e.getInformacoes());
			ps.setString(7, cepAntigo);
			ps.setInt(8, numAntigo);
			ps.setInt(9, buscaIdUsuario(u));

			ps.execute();
			ps.close();

		}

		// Atualizando apenas com complemento
		if (!e.getComplemento().equals("") & e.getInformacoes().equals("")) {
			String sql = "Update Endereco Set cep = ?, numero = ?, logradouro = ?, titulo = ?, complemento = ?, informacao = ?"
					+ "Where cep = ? and numero = ? and id_usuario = ?  ";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, e.getCep());
			ps.setInt(2, e.getNumero());
			ps.setString(3, e.getLogradouro());
			ps.setString(4, e.getTitulo());
			ps.setString(5, e.getComplemento());
			ps.setNull(6, java.sql.Types.INTEGER);
			ps.setString(7, cepAntigo);
			ps.setInt(8, numAntigo);
			ps.setInt(9, buscaIdUsuario(u));

			ps.execute();
			ps.close();
		}

		// Atualizando apenas com informacoes
		if (!e.getInformacoes().equals("") & e.getComplemento().equals("")) {
			String sql = "Update Endereco Set cep = ?, numero = ?, logradouro = ?, titulo = ?, informacao = ?, complemento = ?"
					+ "Where cep = ? and numero = ? and id_usuario = ?  ";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, e.getCep());
			ps.setInt(2, e.getNumero());
			ps.setString(3, e.getLogradouro());
			ps.setString(4, e.getTitulo());
			ps.setString(5, e.getInformacoes());
			ps.setNull(6, java.sql.Types.INTEGER);
			ps.setString(7, cepAntigo);
			ps.setInt(8, numAntigo);
			ps.setInt(9, buscaIdUsuario(u));

			ps.execute();
			ps.close();
		}

		// Atualiazando sem info e complemento
		if (e.getInformacoes().equals("") & e.getComplemento().equals("")) {
			String sql = "Update Endereco Set cep = ?, numero = ?, logradouro = ?, titulo = ?, complemento = ?, informacao = ?"
					+ "Where cep = ? and numero = ? and id_usuario = ?  ";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, e.getCep());
			ps.setInt(2, e.getNumero());
			ps.setString(3, e.getLogradouro());
			ps.setString(4, e.getTitulo());
			ps.setNull(5, java.sql.Types.INTEGER);
			ps.setNull(6, java.sql.Types.INTEGER);
			ps.setString(7, cepAntigo);
			ps.setInt(8, numAntigo);
			ps.setInt(9, buscaIdUsuario(u));

			ps.execute();
			ps.close();
		}

	}

	@Override
	public void excluiEndereco(Endereco e, Usuario u) throws SQLException {
		String sql = "Delete from Endereco where cep = ? and numero = ? and id_usuario = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, e.getCep());
		ps.setInt(2, e.getNumero());
		ps.setInt(3, buscaIdUsuario(u));
		
		ps.execute();
		ps.close();
	}

	@Override
	public Endereco buscaEndereco(Endereco e, Usuario u) throws SQLException {
		String sql = "Select cep, numero, logradouro, titulo,"
				+ "Case when(complemento is not null) then complemento"
				+ "	else ''" + "end as comp,"
				+ "	Case when(informacao is not null) then informacao"
				+ "	else ''" + "end as info"
				+ " from Endereco Where titulo = ? and id_usuario = ?";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, e.getTitulo());
		ps.setInt(2, buscaIdUsuario(u));
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			Endereco e1 = new Endereco();
			e1.setCep(rs.getString("cep"));
			e1.setTitulo(rs.getString("titulo"));
			e1.setNumero(rs.getInt("numero"));
			e1.setLogradouro(rs.getString("logradouro"));
			e1.setComplemento(rs.getString("comp"));
			e1.setInformacoes(rs.getString("info"));
			
			ps.close();
			rs.close();
			return e1;
		}
		else {
			Endereco e1 = new Endereco();
			ps.close();
			rs.close();
			return e1;
		}
	}

	@Override
	public List<Endereco> listaEndereco(Usuario u) throws SQLException {
		String sql = "Select cep, numero, logradouro, titulo,"
				+ "Case when(complemento is not null) then complemento"
				+ "	else ''" + "end as comp,"
				+ "	Case when(informacao is not null) then informacao"
				+ "	else ''" + "end as info"
				+ " from Endereco Where id_usuario = ?";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, buscaIdUsuario(u));
		
		ResultSet rs = ps.executeQuery();
		ArrayList<Endereco> listaEndereco = new ArrayList<>();
		while(rs.next()) {
			Endereco e1 = new Endereco();
			e1.setCep(rs.getString("cep"));
			e1.setTitulo(rs.getString("titulo"));
			e1.setNumero(rs.getInt("numero"));
			e1.setLogradouro(rs.getString("logradouro"));
			e1.setComplemento(rs.getString("comp"));
			e1.setInformacoes(rs.getString("info"));
			
			listaEndereco.add(e1);	
		}
		ps.close();
		rs.close();
		return listaEndereco;
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
