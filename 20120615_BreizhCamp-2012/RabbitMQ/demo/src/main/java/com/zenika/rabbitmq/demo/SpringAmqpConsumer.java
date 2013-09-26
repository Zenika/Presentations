package com.zenika.rabbitmq.demo;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author queinnec
 */
public class SpringAmqpConsumer implements MessageListener {

	private final Logger logger = Logger.getLogger(SpringAmqpConsumer.class);

	public void onMessage(Message message) {
		logger.info(">>> RECEIVING : " + message.getMessageProperties().getReceivedRoutingKey() + " " + message);
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-context-receiver.xml");
	}

}
