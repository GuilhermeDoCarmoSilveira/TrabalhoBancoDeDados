package application.controller;

import java.sql.SQLException;

import application.model.Endereco;

public interface IEnderecoController {
	public void insereEndereco(Endereco e) throws ClassNotFoundException, SQLException;
	public void atualizaEndereco(Endereco e,  int numAntigo, String cepAntigo) throws ClassNotFoundException, SQLException;
	public void excluiEndereco(Endereco e) throws ClassNotFoundException, SQLException;
	public void buscaEndereco(Endereco e) throws ClassNotFoundException, SQLException;
	public void listaEndereco() throws ClassNotFoundException, SQLException;

}
