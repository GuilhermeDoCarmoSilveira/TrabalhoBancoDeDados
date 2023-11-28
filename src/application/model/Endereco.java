package application.model;

public class Endereco {
	
	private String titulo;
	private String cep;
	private String logradouro;
	private int numero;
	private String complemento;
	private String informacoes;
	
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getInformacoes() {
		return informacoes;
	}
	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}
	@Override
	public String toString() {
		return "titulo = " + titulo + ", cep = " + cep + ", logradouro = " + logradouro + ", numero = " + numero
				+ ", complemento = " + complemento + ", informacoes = " + informacoes;
	}
	
	
	
	
}
