package br.com.tw.model;

public class CalcularValorTotal {
	
	private double valorItens;
	private double valorCouvert;
	private double valorGarcom;	
	
	public CalcularValorTotal(double valorItens, double valorCouvert, double valorGarcom) {
		this.valorItens = valorItens;
		this.valorCouvert = valorCouvert;
		this.valorGarcom = valorGarcom;
	}
	public double getValorItens() {
		return valorItens;
	}
	public void setValorItens(double valorItens) {
		this.valorItens = valorItens;
	}
	public double getValorCouvert() {
		return valorCouvert;
	}
	public void setValorCouvert(double valorCouvert) {
		this.valorCouvert = valorCouvert;
	}
	public double getValorGarcom() {
		return valorGarcom;
	}
	public void setValorGarcom(double valorGarcom) {
		this.valorGarcom = valorGarcom;
	}
	
	public double getValorTotal(){
		return getValorCouvert()+getValorGarcom()+getValorItens();
	}

}
