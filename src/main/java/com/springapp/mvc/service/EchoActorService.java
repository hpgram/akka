package com.springapp.mvc.service;

import akka.dispatch.Await;
import akka.pattern.Patterns;
import akka.util.Timeout;
import com.springapp.mvc.actor.AkkaWebInitializer;
import com.springapp.mvc.model.attic.TalkyTalkyRequest;
import com.springapp.mvc.model.attic.TalkyTalkyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sivakom
 */
@Service
public class EchoActorService {

    private static final Logger log = LoggerFactory.getLogger(EchoActorService.class);

    @Autowired
    AkkaWebInitializer akkaWebInitializer;

    public TalkyTalkyResponse sendResponse(TalkyTalkyRequest talkyTalkyRequest) throws Exception {

        Await.Awaitable<Object> response = Patterns.ask(akkaWebInitializer.getMaster(), talkyTalkyRequest, Timeout.intToTimeout(5000));
        return (TalkyTalkyResponse) Await.result(response, akka.util.Duration.Inf());
    }
}
