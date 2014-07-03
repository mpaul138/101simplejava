package org.softlang.company.messages;

import java.util.ArrayList;
import java.util.List;

import org.softlang.company.model.Department;

import akka.actor.ActorRef;

public class CutDepartmentsMessage {
	private List<Department> departments = new ArrayList<Department>();

	public CutDepartmentsMessage(List<Department> departments) {
		this.departments = departments;

	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

}
