package org.softlang.company.actors;

import java.util.ArrayList;
import java.util.List;

import org.softlang.company.messages.CutDepartmentsMessage;
import org.softlang.company.messages.EndMessage;
import org.softlang.company.messages.TotalDepartmentsMessage;
import org.softlang.company.messages.TotalResultMessage;
import org.softlang.company.model.Department;
import org.softlang.company.model.Employee;

import scala.concurrent.Await;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.pattern.Patterns;
import akka.util.Timeout;

public class CutDepartmentsActor extends UntypedActor {
	private boolean end = false;

	// private ActorRef back = null;

	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof CutDepartmentsMessage) {
			List<Department> ds = ((CutDepartmentsMessage) message)
					.getDepartments();
			cutSalaries(getEmployees(ds));
			if (end)
				this.getSender().tell(new EndMessage(), this.getSelf());
		} else if (message instanceof EndMessage)
			this.getContext().parent().tell(message, this.getSelf());
	}

	/**
	 * collect employees from departments ds
	 * 
	 * @param ds
	 * @return collected employees
	 */
	private List<Employee> getEmployees(List<Department> ds) {
		List<Employee> employees = new ArrayList<Employee>();
		List<Department> nextDepartments = new ArrayList<Department>();
		for (Department d : ds) {
			nextDepartments.addAll(d.getDepartments());
		}
		nextInstance(nextDepartments);
		for (Department d : ds) {
			if (d.getManager() != null)
				employees.add(d.getManager());
			employees.addAll(d.getEmployees());
		}
		return employees;
	}

	/**
	 * Create next instance if departments available
	 * 
	 * @param nextDepartments
	 */
	private void nextInstance(List<Department> nextDepartments) {
		if (!nextDepartments.isEmpty()) {
			ActorRef child = this.context().actorOf(
					Props.create(CutDepartmentsActor.class));
			Timeout timeout = new Timeout(Duration.create(5, "seconds"));
			child.tell(new CutDepartmentsMessage(nextDepartments),
					this.getSelf());
		} else
			end = true;
	}

	private void cutSalaries(List<Employee> es) {
		for (Employee e : es) {
			e.setSalary(e.getSalary() / 2);
			// System.out.println(e.getSalary());
		}
	}

}
