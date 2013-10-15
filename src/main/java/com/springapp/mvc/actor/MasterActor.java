package com.springapp.mvc.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinRouter;
import com.springapp.mvc.model.TalkyTalkyRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sivakom
 */
public class MasterActor extends UntypedActor {

    private static final Logger log = LoggerFactory.getLogger(MasterActor.class);

    private final ActorRef workerRouter;

    public MasterActor(int nrOfWorkers) {

        workerRouter = this.getContext().actorOf(
                new Props(MessageActor.class).withRouter(new RoundRobinRouter(nrOfWorkers)),
                "workerRouter");
    }

    public void onReceive(Object message) {
        if (message instanceof TalkyTalkyRequest) {

            TalkyTalkyRequest talkyTalkyRequest = (TalkyTalkyRequest) message;
            workerRouter.tell(talkyTalkyRequest, getSender());
        } else {

            unhandled(message);
        }
    }
}
