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

	public static double total(Company c) {
		double result = 0.0;
		ActorSystem system = ActorSystem.create("MySystem");
		ActorRef myActor = system.actorOf(Props.create(TotalActor.class));
		Timeout timeout = new Timeout(Duration.create(10, "seconds"));
		Future<Object> future = Patterns.ask(myActor, new TotalMessage(c),
				timeout);
		try {
			result = (Double) Await.result(future, timeout.duration());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
