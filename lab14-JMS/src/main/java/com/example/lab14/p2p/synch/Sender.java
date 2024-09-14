package com.example.lab14.p2p.synch;

import javax.jms.*;

import com.example.lab14.Programmer;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Sender {
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static String jmsQueue = "example_QUEUE";

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(jmsQueue);
        MessageProducer producer = session.createProducer(destination);
        Programmer prog = new Programmer("Ivan", "Bikov", "middle", 123.4);
        ObjectMessage message = session
                .createObjectMessage(prog.toString());
        producer.send(message);

        System.out.println("JMS Message Sent successfuly:: " + message.getObject());
        connection.close();
    }
}
