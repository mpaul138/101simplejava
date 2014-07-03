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

import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.pattern.Patterns;
import akka.util.Timeout;

public class TotalActor extends UntypedActor {
	// private ArrayList<Double> results = new ArrayList<Double>();
	private ActorRef back;
	private ArrayList<Future<Object>> futures = new ArrayList<Future<Object>>();
	private Timeout timeout = new Timeout(Duration.create(5, "seconds"));

	@Override
	public void onReceive(Object message) throws Exception {
		ActorRef child;
		if (message instanceof TotalMessage) {
			back = this.getSender();
			child = this.context().actorOf(
					Props.create(CollectTotalActor.class));
			child.tell(new TotalDepartmentsMessage(((TotalMessage) message)
					.getCompany().getDepartments()), getSelf());
		} else if (message instanceof TotalResultMessage) {
			back.tell(new Double(((TotalResultMessage) message).getResult()),
					getSelf());
		} else
			unhandled(message);
	}

	private double addTotals(List<Double> results) {
		double result = 0.0;
		for (double r : results)
			result += r;
		return result;
	}
}
