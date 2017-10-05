package biz.cit.guice;

import biz.cit.guice.web.MqServlet;
import com.google.inject.AbstractModule;

public class MqModule extends AbstractModule {

    protected void configure() {
        bind(MqConfig.class).asEagerSingleton();
    }


}
