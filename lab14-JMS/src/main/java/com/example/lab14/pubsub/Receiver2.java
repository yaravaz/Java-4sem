package com.example.lab14.pubsub;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Receiver2 {

    public static void main(String[] args) throws Exception {
        Receiver2 consumer = new Receiver2();
        Object messageString = consumer.receiveTextMessage();
        System.out.println("The message content is: " + messageString.toString());
    }

    public Object receiveTextMessage() throws Exception {

        ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        Destination destination = session.createTopic("test-topic-MQ");
        String selector = "type = 'important'";
        MessageConsumer consumer = session.createConsumer(destination, selector);
        Message message = consumer.receive();
        Object resultCode = ((ObjectMessage) message).getObject();
        consumer.close();
        session.close();
        connection.close();
        return resultCode;
    }
}