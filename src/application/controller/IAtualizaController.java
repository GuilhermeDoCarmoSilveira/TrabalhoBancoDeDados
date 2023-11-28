package application.controller;

import java.sql.SQLException;

public interface IAtualizaController {
	public void atualizaDados() throws ClassNotFoundException, SQLException;
	public boolean verificaRestricoes() throws ClassNotFoundException, SQLException;
	public void prencheeCampos() throws SQLException, ClassNotFoundException;
}
