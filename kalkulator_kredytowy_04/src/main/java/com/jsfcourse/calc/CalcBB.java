package com.jsfcourse.calc;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ResourceBundle;

@Named
@RequestScoped
//@SessionScoped
public class CalcBB implements Serializable{
	private Double amount;
	private Double years;
	private Double percent;
	
	private Double result;
	
	@Inject
	@ManagedProperty("#{txtCalcErr}")
	private ResourceBundle txtCalcErr;

	// Resource injected
	@Inject
	@ManagedProperty("#{txtCalc}")
	private ResourceBundle txtCalc;
	
	@Inject
	FacesContext ctx;
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getYears() {
		return years;
	}

	public void setYears(Double years) {
		this.years = years;
	}

	public Double getPercent() {
		return percent;
	}

	public void setPercent(Double percent) {
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
	public String calc_AJAX() {
		if (doTheMath()) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "miesięczna rata: " + result + "zł", null));
		}
		return null;
	}

	public String info() {
		return "info";
	}
}
