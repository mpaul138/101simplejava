package org.softlang.company.features;

import java.util.ArrayList;

import org.apache.commons.jxpath.JXPathContext;
import org.softlang.company.model.Company;
import org.softlang.company.model.Employee;

/**
 * 
 * @author Matthias Paul
 * 
 */
public class Cut {

	/**
	 * 
	 * @param c
	 *            Company to cut salaries
	 */
	public static void cut(Company c) {
		JXPathContext con = JXPathContext.newContext(c);
		ArrayList<Employee> es = new ArrayList<Employee>();
		es.addAll(con.selectNodes("//employees"));
		es.addAll(con.selectNodes("//manager"));
		for (Employee e : es)
			e.setSalary(e.getSalary() / 2);
	}

	public static void cutX(Company c) {
		JXPathContext con = JXPathContext.newContext(c);
		ArrayList<Employee> es = new ArrayList<Employee>();
		es.addAll(con.selectNodes("//employees"));
		es.addAll(con.selectNodes("//manager"));
		for (Employee e : es)
			e.setSalary(e.getSalary() / 2);
	}
}
