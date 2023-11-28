package application.persintence;

import java.sql.SQLException;

import application.model.Cliente;
import application.model.Empresa;
import application.model.Usuario;

public interface IAtualizaDados {
	public void atualizaCLiente(Cliente cl, Usuario u, Usuario uAntigo)throws SQLException;
	public void atualizaEmpresa(Empresa emp, Usuario u, Usuario uAntigo)throws SQLException;
}
