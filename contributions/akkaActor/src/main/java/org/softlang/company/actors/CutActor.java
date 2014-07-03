package org.softlang.company.actors;

import org.softlang.company.messages.CutDepartmentsMessage;
import org.softlang.company.messages.CutMessage;
import org.softlang.company.messages.EndMessage;
import org.softlang.company.messages.TotalDepartmentsMessage;
import org.softlang.company.messages.TotalMessage;
import org.softlang.company.messages.TotalResultMessage;

import akka.actor.ActorRef;
import akka.actor.PoisonPill;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorWithStash;

public class CutActor extends UntypedActor {
	private ActorRef back = null;

	@Override
	public void onReceive(Object message) throws Exception {
		ActorRef child = null;
		if (message instanceof CutMessage) {
			back = this.getSender();
			child = this.context().actorOf(
					Props.create(CutDepartmentsActor.class), "cut");
			child.tell(new CutDepartmentsMessage(((CutMessage) message)
					.getCompany().getDepartments()), getSelf());
		} else if (message instanceof EndMessage) {
			this.getContext().getChild("cut")
					.tell(akka.actor.PoisonPill.getInstance(), this.getSelf());
			back.tell(message, this.getSelf());
		}
	}
}
