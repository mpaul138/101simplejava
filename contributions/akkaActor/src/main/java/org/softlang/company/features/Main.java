package org.softlang.company.features;

import org.softlang.company.model.Company;

import akka.actor.ActorSystem;

public class Main {
	public static void main(String[] args) throws Exception {
		Company c = CompanyCreator.createCompany();
		ActorSystem system = ActorSystem.create("MySystem");
		System.out.println("org total: " + CompanyCreator.SALARY);
		System.out.println("calculated total: " + Total.total(c));
		Cut.cut(c);
		for (int i = 0; i < 1000000; i++) {
			int j;
			j = i % 5;
		}
		System.out.println("calculated new total: " + Total.total(c));
		System.out.println(2 * Total.total(c) == CompanyCreator.SALARY);
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
