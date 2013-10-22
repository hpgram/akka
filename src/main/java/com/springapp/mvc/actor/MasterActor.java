package com.springapp.mvc.actor;

import akka.actor.ActorRef;
import akka.actor.InvalidActorNameException;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorContext;
import akka.actor.UntypedActorFactory;
import com.springapp.mvc.model.UserJoin;
import com.springapp.mvc.model.UserLeave;
import com.springapp.mvc.model.UserList;
import com.springapp.mvc.model.UserMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * @author sivakom
 */
public class MasterActor extends UntypedActor {

    private static final Logger log = LoggerFactory.getLogger(MasterActor.class);

    Set<String> userList = new HashSet<String>();

    public void onReceive(Object message) {

        final UntypedActorContext actorRef = this.getContext();
        if (message instanceof UserJoin) {

            final UserJoin userJoin = (UserJoin) message;

            log.info("User joined: " + userJoin.getUserName());

            ActorRef userActor;
            try {
                userActor = actorRef.actorOf(new Props(new UntypedActorFactory() {
                    public UntypedActor create() {
                        return new UserActor(userJoin.getUserName(), userJoin.getSession());
                    }
                }), userJoin.getUserName());
            } catch (InvalidActorNameException e) {

                userActor = actorRef.actorFor(userJoin.getUserName());
                userActor.tell(userJoin, getSelf());
            }

            //TODO How to tell everybody but this actor
            this.getContext().actorSelection("*").tell(userJoin, getSelf());

            userList.add(userJoin.getUserName());
            //send the list of users to the recently joined user
            userActor.tell(new UserList(userList), getSelf());
        } else if (message instanceof UserLeave) {

            //and terminate the sender
            final UserLeave userLeave = (UserLeave) message;

            log.info("User leaving: " + userLeave.getUserName());

            final ActorRef userActor = actorRef.actorFor(userLeave.getUserName());

            //Should not restarted by the default supervision strategy - need to verify
            this.getContext().stop(userActor);

            //remove from the userList
            userList.remove(userLeave.getUserName());

            //Broadcast that this user has left the chat
            this.getContext().actorSelection("*").tell(userLeave, getSelf());
        } else if (message instanceof UserMessage) {

            UserMessage userMessage = (UserMessage) message;

            log.info("Message: " + userMessage.getUserName() + ":" + userMessage.getMessage());

            //actorRef.system().eventStream().publish(userMessage);
            this.getContext().actorSelection("*").tell(userMessage, getSelf());
        } else {

            unhandled(message);
        }
    }
}
