package com.springapp.mvc.model;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Set;

/**
 * @author sivakom
 */
@Data
public class UserList implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(UserList.class);

    Set<String> users;

    public UserList() {

    }

    public UserList(Set<String> userList) {
        this();
        users = userList;
    }
}
