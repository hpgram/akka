package com.springapp.websocket.config;

import org.glassfish.tyrus.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.DeploymentException;

/**
 * @author sivakom
 */
@Component
public class TyrusServer extends Server {

    private static final Logger log = LoggerFactory.getLogger(TyrusServer.class);

    public TyrusServer(java.lang.String hostName, int port, java.lang.String rootPath, java.lang.Class<?>... configuration) {
        super(hostName, port, rootPath, configuration);
    }

    @PostConstruct
    public void postConstruct() throws DeploymentException {
        this.start();
    }
}
