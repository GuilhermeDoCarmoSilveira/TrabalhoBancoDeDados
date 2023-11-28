package application.model;

public class Pedido {

	private int numero;
	private Double valorTotal;
 
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	@Override
	public String toString() {
		return "Pedido [numero=" + numero + ", valorTotal=" + valorTotal + "]";
	}
	
}
