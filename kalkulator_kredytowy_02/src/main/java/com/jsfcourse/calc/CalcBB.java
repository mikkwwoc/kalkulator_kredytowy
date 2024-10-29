package com.jsfcourse.calc;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
//@SessionScoped
public class CalcBB {
	private String amount;
	private String years;
	private String percent;
	
	private Double result;
	
	@Inject
	FacesContext ctx;
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getYears() {
		return years;
	}

	public void setYears(String years) {
		this.years = years;
	}

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}

	//obliczenia
	public boolean doTheMath() {
		try {
			
			double amount = Double.parseDouble(this.amount);
			double years = Double.parseDouble(this.years);
			double percent = Double.parseDouble(this.percent);
			
		    double monthlyInterestRate = (percent / 100) / 12;

		    double numberOfPayments = years * 12;

		    result = (amount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, - numberOfPayments));
		    result = (double) (Math.round(result * 100)/ 100.0); //zaokraglenie
		    
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return true;
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
			return false;
		}
	}

	public String calc() {
		if (doTheMath()) {
			return "showresult";
		}
		return null;
	}


	public String info() {
		return "info";
	}
}
