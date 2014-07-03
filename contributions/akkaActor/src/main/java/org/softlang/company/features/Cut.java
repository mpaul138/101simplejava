package org.softlang.company.features;

import org.softlang.company.actors.CutActor;
import org.softlang.company.actors.TotalActor;
import org.softlang.company.messages.CutMessage;
import org.softlang.company.messages.EndMessage;
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

public class Cut {
	public static void cut(Company c, ActorSystem system) throws Exception {

		ActorRef myActor = system.actorOf(Props.create(CutActor.class));
		// myActor.tell(new CutMessage(c), null);
		Timeout timeout = new Timeout(Duration.create(10, "seconds"));
		Future<Object> future = Patterns.ask(myActor, new CutMessage(c),
				timeout);
		Await.ready(future, timeout.duration());
	}

	private static void cut(Department d) {
		if (d.getManager() != null)
			d.getManager().setSalary(d.getManager().getSalary() / 2);
		if (d.getEmployees() != null)
			for (Employee e : d.getEmployees())
				e.setSalary(e.getSalary() / 2);
		if (d.getDepartments() != null)
			for (Department d2 : d.getDepartments())
				cut(d2);
	}
}
