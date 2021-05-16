package com.leeosilva;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("hello-world", false, false, false, null);

        channel.basicConsume("hello-world", true, (consumerTag, delivery)  -> {
            String m = new String (delivery.getBody(), "UTF-8");
            System.out.println("I just received a message = " + m);
        }, consumerTag -> { });

    }

}
