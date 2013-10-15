package com.springapp.mvc.service;

import akka.actor.ActorSystem;
import com.springapp.mvc.model.TalkyTalkyRequest;
import com.springapp.mvc.model.TalkyTalkyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sivakom
 */
@Service
public class EchoService {

    private static final Logger log = LoggerFactory.getLogger(EchoService.class);

    @Autowired
    ActorSystem system;

    public TalkyTalkyResponse sendResponse(TalkyTalkyRequest talkyTalkyRequest) throws Exception {
        /*ActorRef actor;
        Await.Awaitable<Object> response = Patterns.ask(actor, talkyTalkyRequest, Timeout.never());
        Await.result(response, akka.util.Duration.Inf());*/

        String status = "ack";
        String message = talkyTalkyRequest.getTarget() + " says " + talkyTalkyRequest.getMessage();

        TalkyTalkyResponse talkyTalkyResponse = new TalkyTalkyResponse(status, message);

        return talkyTalkyResponse;
    }
}
