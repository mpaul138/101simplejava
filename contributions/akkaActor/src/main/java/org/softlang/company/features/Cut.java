package org.softlang.company.features;

import java.util.concurrent.TimeoutException;

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
	public static void cut(Company c) {
		ActorSystem system = ActorSystem.create("MySystem");
		ActorRef myActor = system.actorOf(Props.create(CutActor.class));
		Timeout timeout = new Timeout(Duration.create(10, "seconds"));
		Future<Object> future = Patterns.ask(myActor, new CutMessage(c),
				timeout);
		try {
			Await.ready(future, timeout.duration());
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
