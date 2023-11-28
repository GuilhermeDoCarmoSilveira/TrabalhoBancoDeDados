package application.persintence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.model.Usuario;

public class PedidoDao implements IPedidoDao {

	private Connection c;

	public PedidoDao() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}

	@Override
	public void inserePedido(Usuario u, String nomeProd, Double valorProd, String email, int numPed)
			throws SQLException {
		String sql = "INSERT INTO Pedido VALUES " + "(?, GETDATE(), 1, ?, ?, ?)";

		PreparedStatement ps = c.prepareStatement(sql);

		ps.setInt(1, numPed);
		ps.setDouble(2, valorProd);
		ps.setInt(3, buscaIdProduto(email, nomeProd));
		ps.setInt(4, buscaIdUsuario(u));

		ps.execute();
	}

	public void vericaProduto(Usuario u, String nome, String email, Double valorProd, int numPed) throws SQLException {
		String sql = "SELECT numero, id_usuario, id_produto FROM Pedido "
				+ "WHERE numero = ? AND id_usuario = ? AND id_produto = ?";

		PreparedStatement ps = c.prepareStatement(sql);

		ps.setInt(1, numPed);
		ps.setInt(2, buscaIdUsuario(u));
		ps.setInt(3, buscaIdProduto(email, nome));

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			String sql2 = "UPDATE Pedido SET quantidade = quantidade + 1, valorTotal = ? * (quantidade+1) where numero = ?";

			PreparedStatement ps1 = c.prepareStatement(sql2);
			ps1.setDouble(1, valorProd);
			ps1.setInt(2, numPed);
			ps1.execute();

			ps1.close();
		} else {
			inserePedido(u, nome, valorProd, email, numPed);
		}

		ps.close();
		rs.close();
	}

	public int buscaNumPedido() throws SQLException {
		String sql = "SELECT numero FROM Pedido " + "ORDER BY numero Desc";

		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			int numPedido = rs.getInt("numero");

			rs.close();
			ps.close();

			return numPedido + 1;
		}

		rs.close();
		ps.close();

		return 0;

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

	private int buscaIdProduto(String email, String nome) throws SQLException {
		int id = 0;
		String sql = "Select p.id_produto from Produto p, Usuario u " + "    where p.id_usuario = u.id_usuario "
				+ "    and u.email = ? " + "    and p.nome = ?";

		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, email);
		ps.setString(2, nome);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			id = rs.getInt("id_produto");
		}

		rs.close();
		ps.close();

		return id;
	}

	public boolean verificaQtdProduto(String nome, String email, int qtd) throws SQLException {
		String sql = "Select quantidade from produto where id_produto = ? and statusProduto = 'D'";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, buscaIdProduto(email, nome));
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			int qtd1 = rs.getInt("quantidade");
			if (qtd <= qtd1) {
				rs.close();
				ps.close();
				return true;
			} else {
				rs.close();
				ps.close();

				if (qtd > qtd1) {
					return false;
				}

			}

		} else {
			rs.close();
			ps.close();
			return false;
		}
		return true;

	}

	public int recebeQuantidade(String nome, String email) throws SQLException {
		String sql = "Select quantidade from produto where id_produto = ? and statusProduto = 'D'";

		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, buscaIdProduto(email, nome));
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			int qtd = rs.getInt("quantidade");
			rs.close();
			ps.close();
			return qtd;
		} else {
			ps.close();
			return 0;
		}
	}

	public void atualizaQtd(String nome, String email) throws SQLException {
		String sql = "Update Produto set quantidade = quantidade-1 where id_produto = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, buscaIdProduto(email, nome));
		ps.execute();
		ps.close();
		atualizaStatus(nome, email);

	}

	private void atualizaStatus(String nome, String email) throws SQLException {
		String sql = "Select quantidade from produto where id_produto = ? ";

		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, buscaIdProduto(email, nome));
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			int qtd = rs.getInt("quantidade");
			if (qtd == 0) {
				String sql1 = "Update Produto set statusProduto = 'I' where id_produto = ?";
				PreparedStatement ps1 = c.prepareStatement(sql1);
				ps1.setInt(1, buscaIdProduto(email, nome));
				ps1.execute();
				ps1.close();
			}
		}
		rs.close();
		ps.close();
	}

}
