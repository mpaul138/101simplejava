package org.softlang.company.messages;

public class TotalResultMessage {
	private double result = 0.0;

	public TotalResultMessage(double result){
		this.result = result;
	}
	
	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}
}
