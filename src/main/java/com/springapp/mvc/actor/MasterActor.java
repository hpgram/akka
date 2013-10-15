package com.springapp.mvc.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinRouter;
import com.springapp.mvc.model.TalkyTalkyRequest;
import com.springapp.mvc.model.TalkyTalkyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sivakom
 */
public class MasterActor extends UntypedActor {

    private static final Logger log = LoggerFactory.getLogger(MasterActor.class);

    ActorRef sender;

    private final ActorRef workerRouter;

    public MasterActor(int nrOfWorkers) {

        workerRouter = this.getContext().actorOf(new Props(MessageActor.class).withRouter(new RoundRobinRouter(nrOfWorkers)),
                "workerRouter");
    }

    public void onReceive(Object message) {
        if (message instanceof TalkyTalkyRequest) {

            sender = getSender();
            TalkyTalkyRequest talkyTalkyRequest = (TalkyTalkyRequest) message;
            workerRouter.tell(talkyTalkyRequest, getSelf());
        } else if (message instanceof TalkyTalkyResponse) {

            log.info("Message Received: " + message.toString());
            sender.tell(message);
        } else {

            unhandled(message);
        }
    }
}
