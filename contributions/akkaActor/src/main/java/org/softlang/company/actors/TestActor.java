package org.softlang.company.actors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class TestActor extends UntypedActor {

	@Override
	public void onReceive(Object arg0) throws Exception {
		ActorRef back = null;
		if (arg0 == "Test") {
			back = getSender();
			while (true) {

			}
		} else if (arg0 == "Stop")
			back.tell("Stopped", getSelf());
	}
}
