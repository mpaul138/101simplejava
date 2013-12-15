package org.softlang.company.model;

import java.util.ArrayList;
import java.util.List;

public class Company {
	private String name;
	private List<Department> departments;

	public Company(String name) {
		super();
		this.name = name;
		this.departments = new ArrayList<Department>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void addDepartment(Department d) {
		this.departments.add(d);
	}

}
