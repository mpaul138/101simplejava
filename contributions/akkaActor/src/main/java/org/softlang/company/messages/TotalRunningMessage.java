package org.softlang.company.messages;

public class TotalRunningMessage {
	private double number = 0.0;

	public TotalRunningMessage(int number) {
		this.number = number;
	}

	public double getNumber() {
		return number;
	}

	public void setNumber(double number) {
		this.number = number;
	}

}
