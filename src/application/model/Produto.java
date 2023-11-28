package application.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Produto {
	// nome varchar(100) not null,
//	quantidade		int				not null,
//	preco			decimal(7,2)	not null,
//	descricao		varchar(255)	not null,
//	categoria		varchar(200)	not null,
//	statusProduto	char(1)			not null,
//	dataCadastro	date			not null,
	private String nome;
	private int quantidade;
	private double preco;
	private String descricao;
	private String status;
	private LocalDate dtCadastro;
	private String categoria;

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDtCadastro() {
		String dtCad = dtCadastro.toString();
		return dtCad;
	}

	public void setDtCadastro(String dtCadastro) {
		if(dtCadastro.length() == 8) {
			String vt[] = dtCadastro.split("");
			String txt = vt[0] + vt[1] + "/" + vt[2] + vt[3] + "/" + vt[4] + vt[5] + vt[6] + vt[7];
			LocalDate data = LocalDate.parse(txt, dtf);
			this.dtCadastro = data;
		}else {
			LocalDate data = LocalDate.parse(dtCadastro, dtf);
			this.dtCadastro = data;
		}
		
	}
}
