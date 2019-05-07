package com.gerencia.core;

import com.gerencia.interfaces.Container;
import com.gerencia.interfaces.Iterator;

public class Month implements Container {
	
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
	
	public void setIdMonth(int idMonth) {
		this.idMonth = idMonth;
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
	@Override
	public Iterator getIterator() {
		// TODO Auto-generated method stub
		return new MonthIterator();
	}
	
	  private class MonthIterator implements Iterator {

	      int index;

	      @Override
	      public boolean hasNext() {
	      
	         if(index < MONTHLIST.length){
	            return true;
	         }
	         return false;
	      }

	      @Override
	      public Object next() {
	      
	         if(this.hasNext()){
	            return MONTHLIST[index++];
	         }
	         return null;
	      }		
	   }
		
}
