package org.softlang.company.actors;

import java.util.ArrayList;
import java.util.List;

import org.softlang.company.messages.TotalDepartmentsMessage;
import org.softlang.company.messages.TotalEndResultMessage;
import org.softlang.company.messages.TotalMessage;
import org.softlang.company.messages.TotalResultMessage;
import org.softlang.company.model.Department;

import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.pattern.Patterns;
import akka.util.Timeout;

public class TotalControllActor extends UntypedActor {
	private ActorRef back;
	private ArrayList<Future<Object>> futures = new ArrayList<Future<Object>>();

	@Override
	public void onReceive(Object message) throws Exception {
		ActorRef child;
		Timeout timeout = new Timeout(Duration.create(5, "seconds"));
		if (message instanceof TotalMessage) {
			back = this.getSender();
			child = this.context().actorOf(
					Props.create(CollectTotalActor.class));
			futures.add(Patterns.ask(child, new TotalDepartmentsMessage(
					((TotalMessage) message).getCompany().getDepartments()),
					timeout));
		} else {
			if (message instanceof TotalDepartmentsMessage) {
				child = this.context().actorOf(
						Props.create(CollectTotalActor.class));
				futures.add(Patterns.ask(child,
						new TotalDepartmentsMessage(((TotalMessage) message)
								.getCompany().getDepartments()), timeout));

			} else {
				if (message instanceof TotalResultMessage)
					;
				else {

				}
			}

			// back.tell(new Double(addTotals()), self());
		}

	}
}