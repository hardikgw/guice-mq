package biz.cit.guice;

import com.ibm.mq.*;
import com.ibm.mq.constants.CMQC;
import com.ibm.mq.constants.MQConstants;
import com.ibm.mq.jms.MQQueueReceiver;
import com.ibm.msg.client.jms.JmsConnectionFactory;
import com.ibm.msg.client.jms.JmsConstants;
import com.ibm.msg.client.jms.JmsFactoryFactory;
import com.ibm.msg.client.wmq.WMQConstants;

import javax.inject.Inject;
import javax.jms.*;
import java.io.IOException;

public class MqConfig {

    public MqConfig() {
        try {
            startListening();
        } catch (MQException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private void startListening() throws MQException, JMSException {
        boolean transacted = false;

        JmsFactoryFactory ff = JmsFactoryFactory.getInstance(JmsConstants.WMQ_PROVIDER);
        JmsConnectionFactory factory = ff.createQueueConnectionFactory();
        factory.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_BINDINGS_THEN_CLIENT);
        factory.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER, "QM1");
        factory.setStringProperty(WMQConstants.WMQ_HOST_NAME, "localhost");
        factory.setIntProperty(WMQConstants.WMQ_PORT, 1414);
        factory.setStringProperty(WMQConstants.WMQ_CHANNEL, "DEV.ADMIN.SVRCONN");
        factory.setStringProperty(WMQConstants.WMQ_APPLICATIONNAME, "GuiceApp");
        factory.setStringProperty(WMQConstants.USERID,"admin");
        factory.setStringProperty(WMQConstants.PASSWORD, "passw0rd");
        factory.setStringProperty(WMQConstants.DESTINATION_NAME, "DEV.QUEUE.1");

        Connection connection = factory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("DEV.QUEUE.1");
        MessageConsumer receiver = session.createConsumer(queue);
        receiver.setMessageListener(new MqListener());
        //session.setMessageListener(new MqListener());
        connection.start();

    }

}
