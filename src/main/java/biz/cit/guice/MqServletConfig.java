package biz.cit.guice;

import biz.cit.guice.web.MqServlet;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Scopes;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

public class MqServletConfig extends GuiceServletContextListener {
    protected Injector getInjector() {
        return Guice.createInjector(new MqModule(), new ServletModule(){
            @Override
            protected void configureServlets() {
                serve("/mq/*").with(MqServlet.class);
                bind(MqServlet.class).in(Scopes.SINGLETON);
            }
        });
    }
}
