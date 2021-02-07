package contabilita;

import java.time.LocalDate;
import java.util.ArrayList;

public class ContoTotale {

	public ContoTotale(String numeroConto, double importo, LocalDate dataPagamento, String codCliente) {
		this.numeroConto = numeroConto;
		this.importo = importo;
		this.dataPagamento = dataPagamento;
		this.codCliente = codCliente;
	}

	@Override
	public String toString() {
		return "ContoTotale [numeroConto=" + numeroConto + ", importo=" + importo + ", dataPagamento=" + dataPagamento
				+ ", codCliente=" + codCliente + "]";
	}

	public String getNumeroConto() {
		return numeroConto;
	}

	
	public String getCodCliente() {
		return codCliente;
	}

	public double getImporto() {
		return importo;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public String getCliente() {
		return codCliente;
	}


	private String numeroConto;
	private double importo;
	private LocalDate dataPagamento;
	private String codCliente;
	
}
