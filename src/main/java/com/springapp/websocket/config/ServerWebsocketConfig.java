package com.springapp.websocket.config;

import com.springapp.websocket.ClientWebsocketController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;
import java.util.HashSet;
import java.util.Set;

/**
 * @author sivakom
 */
@Component
public class ServerWebsocketConfig implements ServerApplicationConfig {

    private static final Logger log = LoggerFactory.getLogger(ServerWebsocketConfig.class);

    @Override
    public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> endpointClasses) {
        return new HashSet<ServerEndpointConfig>();
    }

    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
        Set<Class<?>> set = new HashSet<Class<?>>();
        set.add(ClientWebsocketController.class);
        return set;
    }
}