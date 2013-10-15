package com.springapp.mvc.controller;

import com.springapp.mvc.model.TalkyTalkyRequest;
import com.springapp.mvc.model.TalkyTalkyResponse;
import com.springapp.mvc.service.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/reactiveprogramming/**")
public class EchoController {

    @Autowired
    EchoService echoService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<TalkyTalkyResponse> printWelcome() {
        return new ResponseEntity<TalkyTalkyResponse>(new TalkyTalkyResponse("ack", "alive"), HttpStatus.OK);
    }

    @RequestMapping(value = "/sendecho",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity sendEcho(@RequestBody TalkyTalkyRequest request) throws Exception {

        final TalkyTalkyResponse talkyTalkyResponse = echoService.sendResponse(request);
        return new ResponseEntity<TalkyTalkyResponse>(talkyTalkyResponse, HttpStatus.OK);
    }
}