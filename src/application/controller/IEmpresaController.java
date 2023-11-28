package application.controller;

import java.sql.SQLException;

import application.model.Empresa;
import application.model.Endereco;

public interface IEmpresaController {
	
	public void insereEmpresa(Empresa emp, Endereco e) throws ClassNotFoundException, SQLException;
	public boolean verificaRestricoes() throws ClassNotFoundException, SQLException;
	public boolean verificaCamposVazios() throws ClassNotFoundException, SQLException;
}
