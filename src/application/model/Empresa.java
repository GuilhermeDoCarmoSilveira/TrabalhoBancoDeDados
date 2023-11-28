package application.model;

public class Empresa extends Usuario {

	private String cnpj; 
	private String razaoSocial;
	private String apelido;
	private String telefone;
	
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getApelido() {
		return apelido;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	@Override
	public String toString() {
		return "Empresa cnpj = " + cnpj + ", razaoSocial = " + razaoSocial + ", apelido = " + apelido + ", telefone = "
				+ telefone;
	}
	
	
	
	
}