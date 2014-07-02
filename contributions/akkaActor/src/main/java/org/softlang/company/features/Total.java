package org.softlang.company.features;

import org.softlang.company.actors.TotalActor;
import org.softlang.company.messages.TotalMessage;
import org.softlang.company.model.Company;
import org.softlang.company.model.Department;
import org.softlang.company.model.Employee;

import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;

public class Total {

	public static double total(Company c,ActorSystem system) throws Exception {
		double result = 0.0;
		
		ActorRef myActor = system.actorOf(Props.create(TotalActor.class),
				  "totalactor");
		Timeout timeout = new Timeout(Duration.create(5, "seconds"));
		Future<Object> future = Patterns.ask(myActor, new TotalMessage(c), timeout);
		result = (Double) Await.result(future, timeout.duration());
		return result;
	}

	private static double total(Department d) {
		double result = 0.0;
		if (d.getManager() != null)
			result = d.getManager().getSalary();
		if (d.getEmployees() != null)
			for (Employee e : d.getEmployees())
				result += e.getSalary();
		if (d.getDepartments() != null)
			for (Department d2 : d.getDepartments())
				result += total(d2);
		return result;
	}

}
