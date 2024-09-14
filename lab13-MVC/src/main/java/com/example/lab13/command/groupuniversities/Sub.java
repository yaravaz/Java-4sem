package com.example.lab13.command.groupuniversities;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

//14lab
public class Sub implements MessageListener, ExceptionListener {

    public void startListen() throws NamingException, JMSException, InterruptedException {

        /*Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY,"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        env.put(Context.PROVIDER_URL, "tcp://localhost:61616");
        env.put("queue.queueSampleQueue2","MyNewQueue2");

        InitialContext ctx = new InitialContext(env);
        Queue queue = (Queue) ctx.lookup("queueSampleQueue2");
        QueueConnectionFactory connFactory = (QueueConnectionFactory) ctx.lookup("QueueConnectionFactory");*/

        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:comp/env");
        QueueConnectionFactory connFactory = (QueueConnectionFactory) envContext.lookup("jms/ConnectionFactory");
        Queue queue = (Queue) envContext.lookup("jms/queue/MyQueue");

        QueueConnection queueConn = connFactory.createQueueConnection();
        QueueSession queueSession = queueConn.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);

        QueueReceiver queueReceiver = queueSession.createReceiver(queue);
        Sub asyncReceiver = new Sub();
        queueReceiver.setMessageListener(asyncReceiver);
        queueConn.setExceptionListener(asyncReceiver);
        queueConn.start();

        System.out.print("waiting for messages\n");
        Thread.sleep(20000);

        queueConn.close();
    }

    public void onMessage(Message message){
        TextMessage msg = (TextMessage) message;
        try {
            System.out.println("received: " + msg.getText());
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }

    public void onException(JMSException exception){
        System.err.println("an error occurred: " + exception);
    }
}