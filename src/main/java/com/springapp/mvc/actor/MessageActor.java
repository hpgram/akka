package com.springapp.mvc.actor;

import akka.actor.UntypedActor;
import com.springapp.mvc.model.TalkyTalkyRequest;
import com.springapp.mvc.model.TalkyTalkyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sivakom
 */
public class MessageActor extends UntypedActor {

    private static final Logger log = LoggerFactory.getLogger(MessageActor.class);

    public void onReceive(Object object) throws Exception {

        if (object instanceof TalkyTalkyRequest) {

            TalkyTalkyRequest talkyTalkyRequest = (TalkyTalkyRequest) object;

            String status = "ack";
            String message = talkyTalkyRequest.getTarget() + " says " + talkyTalkyRequest.getMessage();

            TalkyTalkyResponse talkyTalkyResponse = new TalkyTalkyResponse(status, message);

            getSender().tell(talkyTalkyResponse, getSender());
        } else
            unhandled(object);
    }
}
