package application.persintence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.model.Produto;
import application.model.Usuario;

public class ProdutoDao implements IProdutoDao {

	private Connection c;

	public ProdutoDao() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}

	@Override
	public void insereProduto(Produto p, Usuario u) throws SQLException {
		String sql = "INSERT INTO Produto Values (?, ?, ?, ?, ?, ?, getdate(), ?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, p.getNome());
		ps.setInt(2, p.getQuantidade());
		ps.setDouble(3, p.getPreco());
		ps.setString(4, p.getDescricao());
		ps.setString(5, p.getCategoria());
		ps.setString(6, p.getStatus());
		ps.setInt(7, buscaIdUsuario(u));

		ps.execute();
		ps.close();
	}

	@Override
	public void atualizaProduto(Produto p, Usuario u, String prodAntigo) throws SQLException {
		String sql = "Update Produto Set nome = ?, quantidade = ?, preco = ?, descricao = ?, categoria = ?, statusProduto = ?"
				+ "Where nome = ? and id_usuario = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, p.getNome());
		ps.setInt(2, p.getQuantidade());
		ps.setDouble(3, p.getPreco());
		ps.setString(4, p.getDescricao());
		ps.setString(5, p.getCategoria());
		ps.setString(6, p.getStatus());
		ps.setString(7, prodAntigo);
		ps.setInt(8, buscaIdUsuario(u));

		ps.execute();
		ps.close();

	}

	@Override
	public void excluiProduto(Produto p, Usuario u) throws SQLException {
		String sql = "Delete from Produto where nome = ? and id_usuario = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, p.getNome());
		ps.setInt(2, buscaIdUsuario(u));

		ps.execute();
		ps.close();

	}

	@Override
	public Produto buscaProduto(Produto p, Usuario u) throws SQLException {
		String sql = "Select nome, quantidade, preco, descricao, categoria, statusProduto  from Produto "
				+ "Where nome = ? and id_usuario = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, p.getNome());
		ps.setInt(2, buscaIdUsuario(u));

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			Produto p1 = new Produto();
			p1.setNome(rs.getString("nome"));
			p1.setDescricao(rs.getString("descricao"));
			p1.setCategoria(rs.getString("categoria"));
			p1.setPreco(rs.getDouble("preco"));
			p1.setQuantidade(rs.getInt("quantidade"));
			p1.setStatus(rs.getString("statusProduto"));

			ps.close();
			rs.close();
			return p1;
		} else {
			Produto p2 = new Produto();
			ps.close();
			rs.close();
			
			return p2;
		}
	}

	@Override
	public List<Produto> listaProduto(Usuario u) throws SQLException {
		String sql = "Select nome, quantidade, preco, categoria,"
				+ " Case When(statusProduto='D') then 'Disponivel' else 'Indisponivel' end as status,"
				+ " Case When(LEN(descricao) > 15) then SUBSTRING(descricao,1, 12) + '...' else descricao end as descricao,"
				+ " Convert(char(10),dataCadastro,103) as dtCad  from Produto Where id_usuario = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, buscaIdUsuario(u));

		ResultSet rs = ps.executeQuery();
		ArrayList<Produto> listaProduto = new ArrayList<>();

		while (rs.next()) {
			Produto p1 = new Produto();
			p1.setNome(rs.getString("nome"));
			p1.setDescricao(rs.getString("descricao"));
			p1.setCategoria(rs.getString("categoria"));
			p1.setDtCadastro(rs.getString("dtCad"));
			p1.setPreco(rs.getDouble("preco"));
			p1.setQuantidade(rs.getInt("quantidade"));
			p1.setStatus(rs.getString("status"));

			listaProduto.add(p1);
		}
		ps.close();
		rs.close();
		
		return listaProduto;
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
