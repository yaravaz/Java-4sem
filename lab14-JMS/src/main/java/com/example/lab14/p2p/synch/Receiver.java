package com.example.lab14.p2p.synch;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Receiver {
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static String jmsQueue = "example_QUEUE";

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = (ConnectionFactory) new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue(jmsQueue);
        MessageConsumer consumer = session.createConsumer(destination);
        Message message = consumer.receive();
        if (message instanceof ObjectMessage) {
            Object objectMessage = ((ObjectMessage) message).getObject();
            System.out.println("JMS Message Received successfully: '" + objectMessage.toString() + "'");
        }
        connection.close();
    }
}