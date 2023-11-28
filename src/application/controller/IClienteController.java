package application.controller;

import java.sql.SQLException;

import application.model.Cliente;
import application.model.Endereco;

public interface IClienteController {
	
	public void insereCliente(Cliente c, Endereco e) throws ClassNotFoundException, SQLException;
	public boolean verificaRestricoes() throws ClassNotFoundException, SQLException;
	public boolean verificaCamposVazios() throws ClassNotFoundException, SQLException;
}
