package com.example.lab14.p2p.asynch;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

public class Receiver implements MessageListener, ExceptionListener {

    public static void main(String[] args) throws Exception{

        Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY,"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        env.put(Context.PROVIDER_URL, "tcp://localhost:61616");
        env.put("queue.queueSampleQueue","MyNewQueue");

        InitialContext ctx = new InitialContext(env);
        Queue queue = (Queue) ctx.lookup("queueSampleQueue");
        QueueConnectionFactory connFactory = (QueueConnectionFactory) ctx.lookup("QueueConnectionFactory");
        QueueConnection queueConn = connFactory.createQueueConnection();
        QueueSession queueSession = queueConn.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);

        QueueReceiver queueReceiver = queueSession.createReceiver(queue);
        Receiver asyncReceiver = new Receiver();
        queueReceiver.setMessageListener(asyncReceiver);
        queueConn.setExceptionListener(asyncReceiver);
        queueConn.start();

        System.out.print("waiting for messages\n");
        Thread.sleep(20000);

        queueConn.close();
    }

    public void onMessage(Message message){
        ObjectMessage msg = (ObjectMessage) message;
        System.out.println("received: " + msg.toString());
    }

    public void onException(JMSException exception){
        System.err.println("an error occurred: " + exception);
    }
}