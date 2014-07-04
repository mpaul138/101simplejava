package org.softlang.company.actors;

import java.util.List;

import org.softlang.company.messages.CutMessage;
import org.softlang.company.messages.DepartmentsMessage;
import org.softlang.company.messages.EmployeeMessage;
import org.softlang.company.messages.EndMessage;
import org.softlang.company.messages.TotalMessage;
import org.softlang.company.model.Department;
import org.softlang.company.model.Employee;

import akka.actor.ActorRef;
import akka.actor.PoisonPill;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorWithStash;

public class CutActor extends UntypedActor {
	private ActorRef back = null;

	@Override
	public void onReceive(Object message) throws Exception {
		ActorRef child = null;
		if (message instanceof CutMessage) {
			back = this.getSender();
			child = this.context().actorOf(
					Props.create(DepartmentsActor.class), "cut");
			List<Department> ds = ((CutMessage) message).getCompany()
					.getDepartments();
			child.tell(new DepartmentsMessage(ds), getSelf());
		} else if (message instanceof EndMessage) {
			this.getContext().getChild("cut")
					.tell(akka.actor.PoisonPill.getInstance(), this.getSelf());
			back.tell(message, this.getSelf());
		} else if (message instanceof EmployeeMessage) {
			cutSalaries(((EmployeeMessage) message).getEmployees());
		} else
			unhandled(message);
	}

	private void cutSalaries(List<Employee> es) {
		for (Employee e : es)
			e.setSalary(e.getSalary() / 2);

	}
}
