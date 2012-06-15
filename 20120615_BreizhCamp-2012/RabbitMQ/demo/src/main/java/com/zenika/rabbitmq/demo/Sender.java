package com.zenika.rabbitmq.demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import com.zenika.rabbitmq.utils.Cotation;
import com.zenika.rabbitmq.utils.CotationBuilder;

public class Sender {

	private final static int NB_MSG_TO_SEND = 100 * 1000;
	private final static String SERVER_HOST = "127.0.0.1";
	private final static String EXCHANGE_NAME = "zenika.xch";
	private final static String ROUTING_KEY = "test";

	public void sendMessages() throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		// factory.setHost("192.168.0.2");
		factory.setHost(SERVER_HOST);
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		int index = 0;
		while (index < NB_MSG_TO_SEND) {
			index++;

			Cotation c = CotationBuilder.buildCotation();
			// key c.getMarche()
			channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, false, false, MessageProperties.TEXT_PLAIN, c.toString()
					.getBytes());

			// Thread.sleep(1);

			System.out.print('.');
			if ((index % 100) == 0) {
				System.out.println();
			}
		}

		channel.close();
		connection.close();
	}

	public static void main(String[] args) throws Exception {
		Sender rabbitMQProducer = new Sender();
		rabbitMQProducer.sendMessages();
	}

}
