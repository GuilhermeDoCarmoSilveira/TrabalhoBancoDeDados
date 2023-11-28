package application.controller;

import java.sql.SQLException;
import java.util.List;

import application.model.Usuario;
import application.persintence.MeusPedidosDao;
import javafx.scene.control.TextArea;

public class MeusPedidosController implements IMeusPedidosController {
	private TextArea taMeusPedidos;
	private Usuario u;
	
	public MeusPedidosController(TextArea taMeusPedidos, Usuario u) {
		this.taMeusPedidos = taMeusPedidos;
		this.u = u;
	}

	@Override
	public void listaPedido() throws ClassNotFoundException, SQLException {
	taMeusPedidos.setText("");
	MeusPedidosDao mDao = new MeusPedidosDao();
	List<String> lista = mDao.listaPedido(u);
	StringBuffer buffer = new StringBuffer();
	buffer.append("Numero do Pedido\t\tNome Produto\t\tQuantidade\t\tValor Total\t\tEmail Vendendor\n");
	
	for(String txt : lista) {
		buffer.append(txt);
	}
	taMeusPedidos.setText(buffer.toString());
	}

}
