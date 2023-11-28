package application.persintence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.model.Cliente;
import application.model.Endereco;

public class ClienteDao implements IClienteDao {
	
	private Connection c;
	
	public ClienteDao() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}
	
	@Override
	public boolean verificaCpf(Cliente cl) throws SQLException {
		String sql = "SELECT cpf FROM Cliente WHERE cpf = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, cl.getCpf());
		
		ResultSet rs = ps.executeQuery();
		
		int cont = 0;
		if(rs.next()) {
			cont++;
			return true;
		}
		
		rs.close();
		ps.close();
		
		return false;
	}
	
	@Override
	public void insereUsuario(Cliente cl) throws SQLException {
		String sql = "INSERT INTO Usuario Values (?, ?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, cl.getEmail());
		ps.setString(2, cl.getSenha());
		
		ps.execute();
		ps.close();
	}
	
	private int buscaIdUsuario(Cliente cl) throws SQLException {
		int id = -1;
		
		String sql = "SELECT id_usuario FROM Usuario Where email = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, cl.getEmail());
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			id = rs.getInt("id_usuario");
		}
		
		
		rs.close();
		ps.close();
		
		return id;
	}

	@Override
	public void insereCliente(Cliente cl) throws SQLException {
		String sql = "INSERT INTO Cliente Values (?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, cl.getCpf());
		ps.setString(2, cl.getNome());
		ps.setString(3, cl.getTelefone());
		ps.setString(4, cl.getDtNascimento());
		ps.setString(5, cl.getSexo());
		ps.setInt(6, buscaIdUsuario(cl));
		
		ps.execute();
		ps.close();
		
	}

	@Override
	public void insereEndereco(Endereco e, Cliente cl) throws SQLException {
		String sql = "INSERT INTO Endereco Values (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, e.getCep());
		ps.setInt(2, e.getNumero());
		ps.setInt(3, buscaIdUsuario(cl));
		ps.setString(4, e.getTitulo());
		ps.setString(5, e.getLogradouro());
		ps.setString(6, e.getComplemento());
		ps.setString(7, e.getInformacoes());
		
		ps.execute();
		ps.close();
		
	}

	@Override
	public void insereEnderecoSemCompl(Endereco e, Cliente cl) throws SQLException {
		String sql = "INSERT INTO Endereco (cep, numero, id_usuario, titulo, logradouro, informacao) Values (?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, e.getCep());
		ps.setInt(2, e.getNumero());
		ps.setInt(3, buscaIdUsuario(cl));
		ps.setString(4, e.getTitulo());
		ps.setString(5, e.getLogradouro());
		ps.setString(6, e.getInformacoes());
		
		ps.execute();
		ps.close();
		
	}

	@Override
	public void insereEnderecoSemInfo(Endereco e, Cliente cl) throws SQLException {
		String sql = "INSERT INTO Endereco (cep, numero, id_usuario, titulo, logradouro, complemento) Values (?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, e.getCep());
		ps.setInt(2, e.getNumero());
		ps.setInt(3, buscaIdUsuario(cl));
		ps.setString(4, e.getTitulo());
		ps.setString(5, e.getLogradouro());
		ps.setString(6, e.getComplemento());
		
		ps.execute();
		ps.close();		
	}

	@Override
	public void insereEnderecoSemInfoCompl(Endereco e, Cliente cl) throws SQLException {
		String sql = "INSERT INTO Endereco (cep, numero, id_usuario, titulo, logradouro) Values (?, ?, ?, ?, ?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, e.getCep());
		ps.setInt(2, e.getNumero());
		ps.setInt(3, buscaIdUsuario(cl));
		ps.setString(4, e.getTitulo());
		ps.setString(5, e.getLogradouro());
		
		ps.execute();
		ps.close();	
	}

}
