package com.springapp.mvc.actor;

import akka.actor.UntypedActor;
import com.springapp.mvc.model.TalkyTalkyRequest;
import com.springapp.mvc.model.TalkyTalkyResponse;
import com.springapp.mvc.util.Utilities;
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
            log.info(self() + "::" + talkyTalkyRequest.getTarget());

            //TalkyTalkyResponse talkyTalkyResponse = staticProcess(talkyTalkyRequest);

            TalkyTalkyResponse talkyTalkyResponse = process(talkyTalkyRequest);

            getSender().tell(talkyTalkyResponse, getSender());
        } else
            unhandled(object);
    }

    private TalkyTalkyResponse staticProcess(TalkyTalkyRequest talkyTalkyRequest) {
        return Utilities.getTalkyTalkyResponse(talkyTalkyRequest);
    }

    private TalkyTalkyResponse process(TalkyTalkyRequest talkyTalkyRequest) {
        String status = "ack";
        String message = String.format("%s says %s", talkyTalkyRequest.getTarget(), talkyTalkyRequest.getMessage());

        return new TalkyTalkyResponse(status, message);
    }
}
