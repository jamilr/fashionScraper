package org.fscraper.config;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by jr on 3/22/2015.
 */

public class SpringContextProvider {

    private static ClassPathXmlApplicationContext context;

    public static void setContext(ClassPathXmlApplicationContext ctx) {
        context = ctx;
    }

    public static Object getBean(String name){
        return context.getBean(name);
    }
}
