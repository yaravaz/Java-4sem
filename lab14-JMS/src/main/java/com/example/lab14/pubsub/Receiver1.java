package com.example.lab14.pubsub;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Receiver1 {

    public static void main(String[] args) throws Exception {
        Receiver1 consumer = new Receiver1();
        Object messageString = consumer.receiveTextMessage();
        System.out.println("The message content is: " + messageString.toString());
    }

    public Object receiveTextMessage() throws Exception {
        ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
        Connection connection = factory.createConnection();
        connection.setClientID("bbb");
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic destination = session.createTopic("test-topic-MQ");
        MessageConsumer consumer = session.createDurableConsumer(destination, "bbb");
        Message message = consumer.receive();
        Object resultCode = ((ObjectMessage) message).getObject();
        consumer.close();
        session.close();
        connection.close();
        return resultCode;
    }
}
