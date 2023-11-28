package application.persintence;

import java.sql.SQLException;
import java.util.List;

import application.model.Cliente;
import application.model.Endereco;
import application.model.Usuario;

public interface IEnderecoDao {
	public void insereEndereco(Endereco e, Usuario u) throws SQLException;
	public void atualizaEndereco(Endereco e, Usuario u, int numAntigo, String cep) throws SQLException;
	public void excluiEndereco(Endereco e, Usuario u) throws SQLException;
	public Endereco buscaEndereco(Endereco e, Usuario u) throws SQLException;
	public List<Endereco> listaEndereco(Usuario u) throws SQLException;
}
