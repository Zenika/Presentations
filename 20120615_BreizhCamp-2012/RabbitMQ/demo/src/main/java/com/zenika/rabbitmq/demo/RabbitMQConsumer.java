package com.zenika.rabbitmq.demo;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 
 * @author queinnec
 */
public class RabbitMQConsumer {

	private final static int NB_MSG_TO_POP = 100 * 1000;
	private final static String SERVER_HOST = "127.0.0.1";
	private final static String QUEUE_NAME = "zenika.testha";
	private final static boolean AUTO_ACK = true;

	public static void main(String[] args) throws InterruptedException, IOException {
		// final CountDownLatch latch = new CountDownLatch(NB_MSG_TO_POP);
		// final Logger logger = Logger.getLogger(RabbitMQConsumer.class);

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(SERVER_HOST);

		Connection conn = factory.newConnection();
		Channel channel = conn.createChannel();

		int index = 0;
		while (index < NB_MSG_TO_POP) {
			index++;

			channel.basicGet(QUEUE_NAME, AUTO_ACK);
			// Thread.sleep(1);

			System.out.print('.');
			if ((index % 100) == 0) {
				System.out.println();
			}
		}

		// channel.basicConsume("zenika.ha", true, "myConsumerTag", new DefaultConsumer(channel) {
		// @Override
		// public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties,
		// byte[] body) throws IOException {
		//
		// if (latch.getCount() == 0)
		// return;
		//
		// long deliveryTag = envelope.getDeliveryTag();
		// String routingKey = envelope.getRoutingKey();
		//
		// logger.info(">>> RECEIVING : " + routingKey + " " + body);
		//
		// // this.getChannel().basicAck(deliveryTag, false);
		// latch.countDown();
		// }
		// });

		// latch.await();

		conn.close();
	}

}
