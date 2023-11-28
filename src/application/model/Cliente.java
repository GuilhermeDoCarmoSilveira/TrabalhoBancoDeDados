package application.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Spliterator;

public class Cliente extends Usuario {
	
	private String cpf;
	private String nome;
	private String telefone;
	private String sexo;
	private LocalDate dtNascimento;

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getDtNascimento() {
		String nasc = dtNascimento.toString();
		return nasc;
	}
	public void setDtNascimento(String dtNascimento) {
		if(dtNascimento.length() == 8) {
			String vt[] = dtNascimento.split("");
			String txt = vt[0] + vt[1] + "/" + vt[2] + vt[3] + "/" + vt[4] + vt[5] + vt[6] + vt[7];
			LocalDate data = LocalDate.parse(txt, dtf);
			this.dtNascimento = data;
		}else {
			LocalDate data = LocalDate.parse(dtNascimento, dtf);
			this.dtNascimento = data;
		}
		
	}
	
	@Override
	public String toString() {
		return "Cliente	 cpf = " + cpf + ", nome = " + nome + ", telefone = " + telefone + ", sexo = " + sexo
				+ ", dtNascimento = " + dtNascimento;
	}
	
	
			

	
	
	
	
	
	
	
	
	
}
