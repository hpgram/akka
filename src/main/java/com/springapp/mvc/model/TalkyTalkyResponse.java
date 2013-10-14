package com.springapp.mvc.model;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @author sivakom
 */
@Data
public class TalkyTalkyResponse implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(TalkyTalkyResponse.class);

    private String status;

    private String message;

    public TalkyTalkyResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
