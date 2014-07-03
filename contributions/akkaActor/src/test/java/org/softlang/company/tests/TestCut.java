package org.softlang.company.tests;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.softlang.company.features.Cut;
import org.softlang.company.features.Total;
import org.softlang.company.model.Company;
import org.softlang.company.model.Department;
import org.softlang.company.model.Employee;

public class TestCut {
	public Company snpp = new Company("Springfield nuclear power plant");

	/**
	 * Build Simpsons Example Company
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Department management = new Department("Burn's Office", new Employee(
				"Montgomery Burns", "Springfield", 1000000));
		management.addEmployee(new Employee("Waylon Smithers", "Springfield",
				55555));
		Department sector7g = new Department("Sector 7-G", new Employee(
				"Charlie", "Shelbyville", 8494));
		Department sector4f = new Department("Sector 4-F", new Employee("Joe",
				"Shelbyville", 7489));
		Department sector7m = new Department("Sector 7-M", new Employee(
				"Eugene Fisk", "Springfield", 10486));
		Department sector7q = new Department("Sector 7-Q", new Employee(
				"Frank Grimes", "Shelbyville", 12487));
		sector7g.addEmployee(new Employee("Carl Carlson", "Springfield", 3234));
		sector7g.addEmployee(new Employee("Lenny Leonard", "Springfield", 2599));
		sector7g.addEmployee(new Employee("Homer Simpson", "Evergreen Terrace",
				1875));
		sector4f.addEmployee(new Employee("Al Simmons", "Detroit", 861));
		sector4f.addEmployee(new Employee("Dr. Olberman", "Shelbyville", 17538));
		sector7m.addEmployee(new Employee("Stewart", "New York", 5475));
		sector7q.addEmployee(new Employee("Robert Marlow", "New York", 746));
		snpp.addDepartment(management);
		management.addSubDepartment(sector4f);
		management.addSubDepartment(sector7g);
		sector7g.addSubDepartment(sector7m);
		sector7g.addSubDepartment(sector7q);
	}

	@Test
	public void testCut() {

	}

}
