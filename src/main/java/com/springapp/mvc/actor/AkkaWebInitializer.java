package com.springapp.mvc.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorFactory;
import akka.util.Duration;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

/**
 * Source: http://blog.nemccarthy.me/?p=295
 *
 * @author sivakom
 */
@Component
public class AkkaWebInitializer {

    private static final Logger log = LoggerFactory.getLogger(AkkaWebInitializer.class);

    @Autowired
    private ActorSystem system;

    @Getter
    private ActorRef master;

    @PostConstruct
    public void contextInitialized() {
        //Get the actor system from the spring context
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

        //TODO supervision strategy
        master = system.actorOf(new Props(new UntypedActorFactory() {
            public UntypedActor create() {
                return new MasterActor(10);
            }
        }), "master");

        log.info("ActorSystem has been initialized...");
    }

    @PreDestroy
    public void contextDestroyed() {
        if (system != null) {
            log.info("Killing ActorSystem as a part of web application ctx destruction.");
            system.shutdown();
            system.awaitTermination(Duration.create(15, TimeUnit.SECONDS));
        } else {
            log.warn("No actor system loaded, yet trying to shut down. Check AppContext config and consider if you need this listener.");
        }
    }
}
