package com.springapp.mvc.model;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @author sivakom
 */
@Data
public class TalkyTalkyRequest implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(TalkyTalkyRequest.class);

    private String target;

    private String message;

    public TalkyTalkyRequest() {
    }
}
