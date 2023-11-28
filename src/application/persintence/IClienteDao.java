package application.persintence;

import java.sql.SQLException;

import application.model.Cliente;
import application.model.Endereco;

public interface IClienteDao {
	
	public void insereUsuario(Cliente cl) throws SQLException;
	public void insereCliente(Cliente cl) throws SQLException;
	public void insereEndereco(Endereco e, Cliente cl) throws SQLException;
	public void insereEnderecoSemCompl(Endereco e, Cliente cl) throws SQLException;
	public void insereEnderecoSemInfo(Endereco e, Cliente cl) throws SQLException;
	public void insereEnderecoSemInfoCompl(Endereco e, Cliente cl) throws SQLException;
	public boolean verificaCpf(Cliente cl) throws SQLException;
	
}
