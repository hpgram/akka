package com.springapp.mvc.model;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.Session;
import java.io.Serializable;

/**
 * @author sivakom
 */
@JsonIgnoreProperties("session")
@Data
public class UserJoin extends User implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(UserJoin.class);

    Session session;

    public UserJoin() {
    }

    public UserJoin(String userName, Session session) {
        setUserName(userName);
        this.session = session;
    }
}
