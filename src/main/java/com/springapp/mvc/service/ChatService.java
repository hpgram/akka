package com.springapp.mvc.service;

import com.springapp.mvc.actor.AkkaWebInitializer;
import com.springapp.mvc.model.UserJoin;
import com.springapp.mvc.model.UserLeave;
import com.springapp.mvc.model.UserMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sivakom
 */
@Service
public class ChatService {

    private static final Logger log = LoggerFactory.getLogger(ChatService.class);

    @Autowired
    AkkaWebInitializer akkaWebInitializer;

    public void userJoin(UserJoin userJoin) throws Exception {

        akkaWebInitializer.getMaster().tell(userJoin);
    }

    public void userLeave(UserLeave userLeave) {

        akkaWebInitializer.getMaster().tell(userLeave);
    }

    public void userMessage(UserMessage userMessage) {

        akkaWebInitializer.getMaster().tell(userMessage);
    }

}
