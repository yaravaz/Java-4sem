package com.example.lab14.pubsub;

import com.example.lab14.Programmer;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Sender {

    public static void main(String[] args) throws Exception {
        Sender producer = new Sender();
        Programmer prog = new Programmer("Ivan", "Bikov", "middle", 123.4);
        producer.sendTextMessage(prog);
    }

    public void sendTextMessage(Object datas) throws Exception {
        ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        Destination destination = session.createTopic("test-topic-MQ");
        MessageProducer producer = session.createProducer(destination);
        producer.setPriority(4);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        //producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        Message message = session.createObjectMessage(datas.toString());
        producer.send(message);
        System.out.println("Message sent");
        producer.close();
        session.close();
        connection.close();
    }
}
