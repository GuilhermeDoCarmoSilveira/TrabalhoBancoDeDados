package application.persintence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.model.Produto;
import application.model.Usuario;

public class MeusPedidosDao implements IMeusPedidos {
	private Connection c;

	public MeusPedidosDao() throws ClassNotFoundException, SQLException {
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
	public List<String> listaPedido(Usuario u) throws SQLException {

		// Listando se for cliente
		if (verificaTipoUsuario(u)) {
			String sql = " Select p.numero, prod.nome, p.quantidade, p.valorTotal, "
					+ " (Select email from Usuario where id_usuario = prod.id_usuario)as email_vendedor "
					+ "	from Pedido p, Usuario u, Produto prod, Cliente cl "  
					+  "Where p.id_usuario = u.id_usuario\r\n"
					+ " and p.id_produto = prod.id_produto\r\n"
					+ "	and cl.id_usuario = u.id_usuario\r\n"
					+ "	and u.id_usuario = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, buscaIdUsuario(u));
			ResultSet rs = ps.executeQuery();

			ArrayList<String> listaPedido = new ArrayList<>();
			while (rs.next()) {
				StringBuffer buffer = new StringBuffer();
				buffer.append(rs.getInt("numero") + "\t\t\t\t\t" + rs.getString("nome") + "\t\t\t\t" + rs.getInt("quantidade")
						+ "\t\t\t\t" + rs.getDouble("valorTotal") +  "\t\t\t" + rs.getString("email_vendedor") + "\n");
				listaPedido.add(buffer.toString());
			}
			return listaPedido;
		}
		else {
			String sql = "Select  p.numero, prod.nome, p.quantidade, p.valorTotal, "
					+ "(Select email from Usuario where id_usuario = prod.id_usuario)as email_vendedor "
					+ "	from Pedido p, Usuario u, Produto prod, Empresa emp "
					+ "	Where p.id_usuario = u.id_usuario "
					+ "and p.id_produto = prod.id_produto "
					+ "and emp.id_usuario = u.id_usuario "
					+ "	and u.id_usuario = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, buscaIdUsuario(u));
			ResultSet rs = ps.executeQuery();

			ArrayList<String> listaPedido = new ArrayList<>();
			while (rs.next()) {
				StringBuffer buffer = new StringBuffer();
				buffer.append(rs.getInt("numero") + "\t\t\t\t\t" + rs.getString("nome") + "\t\t\t\t" + rs.getInt("quantidade")
						+ "\t\t\t\t" + rs.getDouble("valorTotal") +  "\t\t\t" + rs.getString("email_vendedor") + "\n");
				listaPedido.add(buffer.toString());
			}
			return listaPedido;
		}
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
