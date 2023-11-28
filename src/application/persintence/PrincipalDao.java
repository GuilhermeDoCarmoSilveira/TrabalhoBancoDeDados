package application.persintence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrincipalDao implements IPrincipalDao {
	
	private Connection c;
	
	public PrincipalDao() throws ClassNotFoundException, SQLException{
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}

	@Override
	public List<String> listaProdutos() throws SQLException {
		String sql = "Select p.nome, CAST(p.preco as varchar(10)) as preco, u.email From Usuario u, Produto p "
				+ "Where u.id_usuario = p.id_usuario "
				+ "ORDER BY NEWID()";
		
		PreparedStatement ps = c.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		ArrayList<String> listaProduto = new ArrayList<>();
		
		
		for(int i = 0; i < 6; i++) {
			if(rs.next()) {
				StringBuffer buffer = new StringBuffer();
				
				buffer.append(rs.getString("nome") + "\n");
				buffer.append("R$ " + rs.getString("preco") + "\n");
				buffer.append(rs.getString("email") + "\n\n");
				
				listaProduto.add(buffer.toString());
			} else {
				break;
			}
		}
		return listaProduto;
	}

	@Override
	public List<String> buscaProdutos(String pesquisa) throws SQLException {
		String sql = "Select p.nome, CAST(p.preco as varchar(10)) as preco, u.email From Usuario u, Produto p "
				+ "Where u.id_usuario = p.id_usuario "
				+ "      and p.nome Like ? "
				+ "ORDER BY NEWID()";
		
		PreparedStatement ps = c.prepareStatement(sql);
		String p = "%" + pesquisa + "%";
		ps.setString(1, p);
		
		ResultSet rs = ps.executeQuery();
		ArrayList<String> listaProduto = new ArrayList<>();
		
		
		for(int i = 0; i < 6; i++) {
			if(rs.next()) {
				StringBuffer buffer = new StringBuffer();
				
				buffer.append(rs.getString("nome") + "\n");
				buffer.append("R$ " + rs.getString("preco") + "\n");
				buffer.append(rs.getString("email") + "\n\n");
				
				listaProduto.add(buffer.toString());
			} else {
				break;
			}
		}
		return listaProduto;
	}

}
