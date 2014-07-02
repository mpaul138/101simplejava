package org.softlang.company.messages;

public class TotalEndResultMessage {
	private double result = 0.0;

	public TotalEndResultMessage(double result) {
		this.result = result;
	}

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}
}
