package com.springapp.mvc.model;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @author sivakom
 */
@Data
public class UserLeave extends User implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(UserLeave.class);

    public UserLeave() {
    }

    public UserLeave(String userName) {
        setUserName(userName);
    }
}
