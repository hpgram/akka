package com.springapp.mvc.service;

import akka.actor.ActorSystem;
import akka.dispatch.Await;
import akka.pattern.Patterns;
import akka.util.Timeout;
import com.springapp.mvc.actor.AkkaWebInitializer;
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

    @Autowired
    AkkaWebInitializer akkaWebInitializer;

    public TalkyTalkyResponse sendResponse(TalkyTalkyRequest talkyTalkyRequest) throws Exception {

        log.info("Within service...");

        //ActorRef actorRef = system.actorSelection("/user/master").target();
        Await.Awaitable<Object> response = Patterns.ask(akkaWebInitializer.getMaster(), talkyTalkyRequest, Timeout.intToTimeout(5000));
        return (TalkyTalkyResponse) Await.result(response, akka.util.Duration.Inf());
    }
}
