package biz.cit.guice;

import com.google.inject.Inject;

import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.logging.Logger;

public class MqListener implements MessageListener  {

    public void onMessage(Message message)
    {
        System.out.println("Message is "+message);
    }
}