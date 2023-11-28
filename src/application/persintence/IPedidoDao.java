package application.persintence;

import java.sql.SQLException;

import application.model.Usuario;

public interface IPedidoDao {
	
	public void inserePedido(Usuario u, String nomeProd, Double valorProd, String email, int numPed) throws SQLException;

}
