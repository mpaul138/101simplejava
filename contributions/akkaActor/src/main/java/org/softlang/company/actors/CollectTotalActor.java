package org.softlang.company.actors;

import java.util.ArrayList;
import java.util.List;

import org.softlang.company.messages.AcceptActorMessage;
import org.softlang.company.messages.EndMessage;
import org.softlang.company.messages.RegisterActorMessage;
import org.softlang.company.messages.TotalDepartmentsMessage;
import org.softlang.company.messages.TotalEndResultMessage;
import org.softlang.company.messages.TotalMessage;
import org.softlang.company.messages.TotalResultMessage;
import org.softlang.company.model.Department;
import org.softlang.company.model.Employee;

import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.pattern.Patterns;
import akka.util.Timeout;

public class CollectTotalActor extends UntypedActor {
	private ActorRef back = null;
	private boolean end = false;
	private Future<Object> childResult;

	@Override
	public void onReceive(Object message) throws Exception {
		// TODO Auto-generated method stub
		if (message instanceof TotalDepartmentsMessage) {
			back = this.getSender();
			Double result = getTotal(getEmployees(((TotalDepartmentsMessage) message)
					.getDepartments()));
			if (!end) {
				Timeout timeout = new Timeout(Duration.create(5, "seconds"));
				result += ((TotalResultMessage) Await.result(childResult,
						timeout.duration())).getResult();
			}
			back.tell(new TotalResultMessage(result), getSelf());
		} else if (message instanceof EndMessage)
			back.forward(message, this.context());
		else {

		}
	}

	/**
	 * collect employees from departments ds
	 * 
	 * @param ds
	 * @return collected employees
	 */
	private List<Employee> getEmployees(List<Department> ds) {
		ArrayList<Employee> employees = new ArrayList<Employee>();
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
					Props.create(CollectTotalActor.class));
			Timeout timeout = new Timeout(Duration.create(5, "seconds"));
			childResult = Patterns.ask(child, new TotalDepartmentsMessage(
					nextDepartments), timeout);
		} else {
			end = true;
		}
	}

	/**
	 * calculate total of employees es
	 * 
	 * @param es
	 *            list of employees
	 * @return total of employees es
	 */
	private double getTotal(List<Employee> es) {
		double result = 0.0;
		for (Employee e : es)
			result += e.getSalary();
		System.out.println(result);
		return result;
	}

}