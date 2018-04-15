package com.example.rabbitmq.server;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Created by Administrator on 2017/11/12 0012.
 */
public class Server {

    private final static String HOST = "192.168.46.128";
    private final static int PORT = 5672;
    private final static String userName = "test";
    private final static String password = "test";
    //队列名称
    private final static String QUEUE_NAME = "workqueue";

    static boolean isBreak =false;

    public static void main(String[] args) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(userName);
        factory.setPassword(password);
        factory.setHost(HOST);
        factory.setVirtualHost("/");
        factory.setPort(PORT);

        Connection connection = factory.newConnection();
        Channel channel =  connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
                isBreak=true;
            }
        };

        channel.basicConsume(QUEUE_NAME, true, consumer);


//        channel.close();
//        connection.close();
    }

//    public static void consumer(Consumer consumer) throws IOException, InterruptedException {
//
//        while (true)
//        {
//            Delivery delivery = consumer.han.nextDelivery();
//            String message = new String(delivery.getBody());
//
//            System.out.println(hashCode + " [x] Received '" + message + "'");
//            doWork(message);
//            System.out.println(hashCode + " [x] Done");
//
//        }
//
//
//    }
}
