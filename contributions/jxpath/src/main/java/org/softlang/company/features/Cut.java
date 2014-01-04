package org.softlang.company.features;

import java.util.Iterator;
import java.util.LinkedList;

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
	 * Cut using loop to modify salaries. Much shorter version of cut.
	 * 
	 * @param c
	 *            Company to cut salaries
	 */
	public static void cut(Company c) {
		JXPathContext con = JXPathContext.newContext(c);
		Iterator<Employee> it = con.iterate("//employees|//manager");
		while (it.hasNext()) {
			Employee e = it.next();
			e.setSalary(e.getSalary() / 2);
		}
	}

	/**
	 * Cut using JXPath to modify salaries. Method demonstrate JXPath ability to
	 * alter values.
	 * 
	 * @param c
	 *            Company to cut salaries
	 */
	public static void cutXPath(Company c) {
		JXPathContext con = JXPathContext.newContext(c);
		LinkedList<Employee> es = new LinkedList<Employee>();
		es.addAll(con.selectNodes("//employees | //manager"));
		con = JXPathContext.newContext(es);
		for (int i = 1; i <= es.size(); i++) {
			con.setValue("@salary[" + i + "]",
					(Double) con.getValue("@salary[" + i + "]") / 2);
		}
	}
}
