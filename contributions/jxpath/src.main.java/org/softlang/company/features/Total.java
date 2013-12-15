package org.softlang.company.features;

import java.util.ArrayList;

import org.apache.commons.jxpath.JXPathContext;
import org.softlang.company.model.Company;

public class Total {
	public static double total(Company c) {
		JXPathContext con = JXPathContext.newContext(c);
		ArrayList<Double> salaries = new ArrayList<Double>();
		salaries = (ArrayList<Double>) con.selectNodes("//@salary");
		double result = 0;
		for (double salary : salaries)
			result += salary;
		return result;
	}
}
