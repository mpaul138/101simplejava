package org.softlang.company.actors;

import java.util.ArrayList;
import java.util.List;

import org.softlang.company.messages.DepartmentsMessage;
import org.softlang.company.messages.EmployeeMessage;
import org.softlang.company.messages.EndMessage;
import org.softlang.company.messages.TotalMessage;
import org.softlang.company.model.Department;
import org.softlang.company.model.Employee;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class TotalActor extends UntypedActor {
	// private ArrayList<Double> results = new ArrayList<Double>();
	private ActorRef back = null;
	// private ArrayList<Employee> employees = new ArrayList<Employee>();
	// private Timeout timeout = new Timeout(Duration.create(5, "seconds"));
	private ArrayList<Double> results = new ArrayList<Double>();

	@Override
	public void onReceive(Object message) throws Exception {
		ActorRef child;
		if (message instanceof TotalMessage) {
			back = this.getSender();
			child = this.context().actorOf(
					Props.create(DepartmentsActor.class), "total");
			List<Department> ds = ((TotalMessage) message).getCompany()
					.getDepartments();
			child.tell(new DepartmentsMessage(ds), this.getSelf());
		} else if (message instanceof EmployeeMessage) {
			results.add(addSalaries(((EmployeeMessage) message).getEmployees()));
		} else if (message instanceof EndMessage) {
			double result = 0.0;
			for (double r : results)
				result += r;
			this.getContext().getChild("total")
					.tell(akka.actor.PoisonPill.getInstance(), this.getSelf());
			back.tell(result, this.getSelf());
		} else
			unhandled(message);
	}

	private double addSalaries(List<Employee> es) {
		double result = 0.0;
		for (Employee e : es) {
			result += e.getSalary();
		}
		return result;
	}
}
