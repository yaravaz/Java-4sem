package com.example.lab14.p2p.asynch;

import com.example.lab14.Programmer;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

public class Sender{

    public static void main(String[] args) throws Exception{

        Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY,"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        env.put(Context.PROVIDER_URL, "tcp://localhost:61616");
        env.put("queue.queueSampleQueue", "MyNewQueue");

        InitialContext ctx = new InitialContext(env);
        Queue queue = (Queue) ctx.lookup("queueSampleQueue");
        QueueConnectionFactory connFactory = (QueueConnectionFactory) ctx.lookup("QueueConnectionFactory");

        QueueConnection queueConn = connFactory.createQueueConnection();
        QueueSession queueSession = queueConn.createQueueSession(false,Session.DUPS_OK_ACKNOWLEDGE);

        QueueSender queueSender = queueSession.createSender(queue);
        queueSender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        Programmer prog = new Programmer("Ivan", "Bikov", "middle", 123.4);
        ObjectMessage message = queueSession.createObjectMessage(prog.toString());
        queueSender.send(message);
        System.out.println("sent: " + message.toString());

        queueConn.close();
    }
}