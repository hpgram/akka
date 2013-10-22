package com.springapp.mvc.model;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @author sivakom
 */
@Data
public class UserMessage extends User implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(UserMessage.class);

    String message;

    public UserMessage() {

    }

    public UserMessage(String userName, String message) {
        setUserName(userName);
        this.message = message;
    }
}
