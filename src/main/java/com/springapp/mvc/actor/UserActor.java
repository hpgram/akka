package com.springapp.mvc.actor;

import akka.actor.UntypedActor;
import com.springapp.mvc.model.UserJoin;
import com.springapp.mvc.model.UserLeave;
import com.springapp.mvc.model.UserList;
import com.springapp.mvc.model.UserMessage;
import com.springapp.mvc.util.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.Session;
import java.io.IOException;

/**
 * @author sivakom
 */
public class UserActor extends UntypedActor {

    private static final Logger log = LoggerFactory.getLogger(UserActor.class);

    String userName;

    Session session;

    public UserActor(String userName, Session session) {
        this.userName = userName;
        this.session = session;
    }

    /**
     * User overridable callback.
     * <p/>
     * Is called asynchronously after 'actor.stop()' is invoked.
     * Empty default implementation.
     */
    @Override
    public void postStop() {
        super.postStop();
        try {
            this.session.close();
        } catch (IOException e) {
            log.warn("Closing session", e);
        }
    }

    /**
     * To be implemented by concrete UntypedActor. Defines the message handler.
     */
    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof UserJoin) {

            sendText(message);
        } else if (message instanceof UserMessage) {

            sendText(message);
        } else if (message instanceof UserList) {

            sendText(message);
        } else if (message instanceof UserLeave) {

            sendText(message);
        }
    }

    private void sendText(Object message) throws IOException {
        if (session.isOpen()) {

            final String jsonString = Utilities.toPrettyJsonString(message);
            session.getBasicRemote().sendText(jsonString);
        } else {

            log.warn("Session has been closed for: " + userName);
            getSender().tell(new UserLeave(userName));
        }
    }
}
