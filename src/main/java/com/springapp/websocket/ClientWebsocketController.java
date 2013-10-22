package com.springapp.websocket;

import com.springapp.mvc.AppContext;
import com.springapp.mvc.model.UserJoin;
import com.springapp.mvc.model.UserLeave;
import com.springapp.mvc.model.UserMessage;
import com.springapp.mvc.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * http://hmkcode.com/first-time-java-api-for-websocket/
 *
 * @author sivakom
 */
@ServerEndpoint("/message/{userName}")
public class ClientWebsocketController {

    private static final Logger log = LoggerFactory.getLogger(ClientWebsocketController.class);

    ChatService chatService;

    public ClientWebsocketController() {

        ApplicationContext ctx = AppContext.getApplicationContext();
        this.chatService = (ChatService) ctx.getBean("chatService");
    }

    @OnMessage
    public void onMessage(String message, @PathParam("userName") String userName)
            throws IOException, InterruptedException {

        if (message.equals(".P.")) {
            //do nothing - keep alive
        } else {
            chatService.userMessage(new UserMessage(userName, message));
        }
    }

    @OnOpen
    public void onOpen(@PathParam("userName") String userName, Session session) throws Exception {

        log.info("Client connected");

        //create the actor
        final UserJoin userJoin = new UserJoin(userName, session);
        //session will terminate in 5 seconds if idle for that long
        session.setMaxIdleTimeout(5 * 1000);
        chatService.userJoin(userJoin);
    }

    @OnClose
    public void onClose(@PathParam("userName") String userName) {
        log.info("Connection closed");

        //destroy the actor
        final UserLeave userLeave = new UserLeave(userName);
        chatService.userLeave(userLeave);
    }
}
