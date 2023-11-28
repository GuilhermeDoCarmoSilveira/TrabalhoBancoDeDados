package application.persintence;

import java.sql.SQLException;

import application.model.Empresa;
import application.model.Endereco;

public interface IEmpresaDao {
	
	public void insereUsuario(Empresa emp) throws SQLException;
	public void insereEmpresa(Empresa emp) throws SQLException;
	public void insereEndereco(Endereco e, Empresa emp) throws SQLException;
	public void insereEnderecoSemCompl(Endereco e, Empresa emp) throws SQLException;
	public void insereEnderecoSemInfo(Endereco e, Empresa emp) throws SQLException;
	public void insereEnderecoSemInfoCompl(Endereco e, Empresa emp) throws SQLException;
	public boolean verificaCnpj(Empresa emp) throws SQLException;
}
