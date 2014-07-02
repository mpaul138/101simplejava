package org.softlang.company.messages;

import java.util.ArrayList;
import java.util.List;

import org.softlang.company.model.Department;

import akka.actor.ActorRef;

public class TotalDepartmentsMessage {
	private List<Department> departments = new ArrayList<Department>();
	private ActorRef back;

	public TotalDepartmentsMessage(List<Department> departments) {
		this.departments = departments;

	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public ActorRef getBack() {
		return back;
	}

	public void setBack(ActorRef back) {
		this.back = back;
	}

}
