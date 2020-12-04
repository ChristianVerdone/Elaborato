package contabilità;

import java.time.LocalDate;
import java.util.Date;

public class ContoTotale {
	
	
	public ContoTotale(String numeroConto, double importo, LocalDate dataPagamento, String codCliente) {
		super();
		this.numeroConto = numeroConto;
		this.importo = importo;
		this.dataPagamento = dataPagamento;
		this.codCliente = codCliente;
	}
	
	
	public String getNumeroConto() {
		return numeroConto;
	}
	

	@Override
	public String toString() {
		return "ContoTotale [numeroConto=" + numeroConto + ", importo=" + importo + ", dataPagamento=" + dataPagamento
				+ ", codCliente=" + codCliente + "]";
	}


	public void setNumeroConto(String numeroConto) {
		this.numeroConto = numeroConto;
	}
	public double getImporto() {
		return importo;
	}
	public void setImporto(double importo) {
		this.importo = importo;
	}
	public LocalDate getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public String getCliente() {
		return codCliente;
	}
	public void setCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	
	private String numeroConto;
	private double importo;
	private LocalDate dataPagamento;
	private String codCliente;
}
