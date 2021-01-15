package contabilita;

import java.time.LocalDate;
import java.util.ArrayList;

public class ContoTotale {

	public ContoTotale(String numeroConto, double importo, LocalDate dataPagamento, String codCliente) {
		this.numeroConto = numeroConto;
		this.importo = importo;
		this.dataPagamento = dataPagamento;
		this.codCliente = codCliente;
		this.voci=voci;
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

	public ArrayList<VoceConto> getVoci() {
		return voci;
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
	
	public void addVoce(VoceConto vc) {
		voci.add(vc);
	}
	public void printConto() {
		System.out.println(numeroConto);
		System.out.println(importo);
		System.out.println(dataPagamento);
		System.out.println(codCliente);
		for(VoceConto v: voci) {
			System.out.println(v);
		}
	}
	private String numeroConto;
	private double importo;
	private LocalDate dataPagamento;
	private String codCliente;
	private ArrayList<VoceConto> voci;
}
