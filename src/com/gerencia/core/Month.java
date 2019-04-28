package com.gerencia.core;

public class Month {
	
	public static final String[] MONTHLIST = {"Janeiro","Fevereiro","Março","Abril",
											  "Maio","Junho","Julho","Agosto","Setembro",
											  "Outubro","Novembro","Dezembro"};
	private int idMonth;
	private int idYear;
	private int received;
	private int prervious;
	private String month;
	
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public int getReceived() {
		return received;
	}
	public void setReceived(int received) {
		this.received = received;
	}
	public int getPrervious() {
		return prervious;
	}
	public void setPrervious(int prervious) {
		this.prervious = prervious;
	}
	public int getIdMonth() {
		return idMonth;
	}
	
	public int getIdYear() {
		return idYear;
	}
	public void setIdYear(int idYear) {
		this.idYear = idYear;
	}
		
}
