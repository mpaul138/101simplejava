package org.softlang.company.features;

import org.softlang.company.actors.TestActor;
import org.softlang.company.actors.TotalActor;
import org.softlang.company.model.Company;

import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;

public class Main {
	public static void main(String[] args) throws Exception {
		Company c = CompanyCreator.createCompany();
		ActorSystem system = ActorSystem.create("MySystem");
		System.out.println("org total: " + CompanyCreator.SALARY);
		System.out.println("calculated total: " + Total.total(c, system));
		// ActorRef a = system.actorOf(Props.create(TestActor.class));
		// Timeout timeout = new Timeout(Duration.create(10, "seconds"));
		// Future<Object> b = Patterns.ask(a, "Test", timeout);
		// // Future<Object> c = Patterns.ask(a, "S", 1000);
		// int j = 0;
		// for (int i = 0; i < 10; i++)
		// j += i * i;
		// a.tell("Stop", null);
		// System.out.println((String) Await.result(b, timeout.duration()));
		// a.tell("Test", null);
		system.shutdown();
	}
}
