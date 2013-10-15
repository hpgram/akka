package com.springapp.mvc.util;

import com.springapp.mvc.model.TalkyTalkyRequest;
import com.springapp.mvc.model.TalkyTalkyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sivakom
 */
public class Utilities {

    private static final Logger log = LoggerFactory.getLogger(Utilities.class);


    public static TalkyTalkyResponse getTalkyTalkyResponse(TalkyTalkyRequest talkyTalkyRequest) {

        String status = "ack";
        String message = String.format("%s says %s", talkyTalkyRequest.getTarget(), talkyTalkyRequest.getMessage());

        return new TalkyTalkyResponse(status, message);
    }
}
