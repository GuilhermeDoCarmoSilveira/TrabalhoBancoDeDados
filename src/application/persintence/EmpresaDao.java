package application.persintence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.model.Cliente;
import application.model.Empresa;
import application.model.Endereco;

public class EmpresaDao implements IEmpresaDao {

	private Connection c;

	public EmpresaDao() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}
	
	@Override
	public boolean verificaCnpj(Empresa emp) throws SQLException {
		String sql = "SELECT cnpj FROM Empresa WHERE cnpj = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, emp.getCnpj());
		
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
	public void insereUsuario(Empresa emp) throws SQLException {
		String sql = "INSERT INTO Usuario Values (?, ?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, emp.getEmail());
		ps.setString(2, emp.getSenha());
		
		ps.execute();
		ps.close();
	}
	
	private int buscaIdUsuario(Empresa emp) throws SQLException {
		int id = -1;
		
		String sql = "SELECT id_usuario FROM Usuario Where email = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, emp.getEmail());
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			id = rs.getInt("id_usuario");
		}
		
		rs.close();
		ps.close();
		
		return id;
	}

	@Override
	public void insereEmpresa(Empresa emp) throws SQLException {
		String sql = "INSERT INTO Empresa Values (?, ?, ?, ?, ?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, emp.getCnpj());
		ps.setString(2, emp.getRazaoSocial());
		ps.setString(3, emp.getApelido());
		ps.setString(4, emp.getTelefone());
		ps.setInt(5, buscaIdUsuario(emp));
		
		ps.execute();
		ps.close();
	}

	@Override
	public void insereEndereco(Endereco e, Empresa emp) throws SQLException {
		String sql = "INSERT INTO Endereco Values (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, e.getCep());
		ps.setInt(2, e.getNumero());
		ps.setInt(3, buscaIdUsuario(emp));
		ps.setString(4, e.getTitulo());
		ps.setString(5, e.getLogradouro());
		ps.setString(6, e.getComplemento());
		ps.setString(7, e.getInformacoes());
		
		ps.execute();
		ps.close();
	}

	@Override
	public void insereEnderecoSemCompl(Endereco e, Empresa emp) throws SQLException {
		String sql = "INSERT INTO Endereco (cep, numero, id_usuario, titulo, logradouro, informacao) Values (?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, e.getCep());
		ps.setInt(2, e.getNumero());
		ps.setInt(3, buscaIdUsuario(emp));
		ps.setString(4, e.getTitulo());
		ps.setString(5, e.getLogradouro());
		ps.setString(6, e.getInformacoes());
		
		ps.execute();
		ps.close();
	}

	@Override
	public void insereEnderecoSemInfo(Endereco e, Empresa emp) throws SQLException {
		String sql = "INSERT INTO Endereco (cep, numero, id_usuario, titulo, logradouro, complemento) Values (?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, e.getCep());
		ps.setInt(2, e.getNumero());
		ps.setInt(3, buscaIdUsuario(emp));
		ps.setString(4, e.getTitulo());
		ps.setString(5, e.getLogradouro());
		ps.setString(6, e.getComplemento());
		
		ps.execute();
		ps.close();	
	}

	@Override
	public void insereEnderecoSemInfoCompl(Endereco e, Empresa emp) throws SQLException {
		String sql = "INSERT INTO Endereco (cep, numero, id_usuario, titulo, logradouro) Values (?, ?, ?, ?, ?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, e.getCep());
		ps.setInt(2, e.getNumero());
		ps.setInt(3, buscaIdUsuario(emp));
		ps.setString(4, e.getTitulo());
		ps.setString(5, e.getLogradouro());
		
		ps.execute();
		ps.close();	
	}

}
