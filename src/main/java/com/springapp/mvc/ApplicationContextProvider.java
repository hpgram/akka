package com.springapp.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author sivakom
 */
@Component
public class ApplicationContextProvider implements ApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(ApplicationContextProvider.class);


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AppContext.setApplicationContext(applicationContext);
    }
}
