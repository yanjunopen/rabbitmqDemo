package com.example.rabbitmq.client;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Created by Administrator on 2017/11/12 0012.
 */
public class Client {

    private final static String HOST = "192.168.46.128";
    private final static int PORT = 5672;
    private final static String userName = "test";
    private final static String password = "test";
    //队列名称
    private final static String QUEUE_NAME = "workqueue";

    public static void main(String[] args) throws Exception {
        //创建连接和频道
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(userName);
        factory.setPassword(password);
        factory.setHost(HOST);
        factory.setVirtualHost("/");
        factory.setPort(PORT);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        sendMessageRandom(channel);

//        //关闭频道和资源
//        channel.close();
//        connection.close();

    }


    public static void sendMessageRandom(Channel channel) throws IOException, InterruptedException {
        int count = 0;
        String message;
        while (true){
            count++;
            Thread.sleep(2000);
            message = "message: " + count + ", time: "+ System.currentTimeMillis();
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }

    }
}
