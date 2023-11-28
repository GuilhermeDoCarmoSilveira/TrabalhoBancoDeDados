package application.controller;

import java.sql.SQLException;

import application.model.Usuario;

public interface IEsqueceuSenha {
	
	public void atualizarSenha(Usuario u) throws ClassNotFoundException, SQLException;
	public boolean verificaRestricoes() throws ClassNotFoundException, SQLException;
	public boolean verificaCamposVazios() throws ClassNotFoundException, SQLException;
}
