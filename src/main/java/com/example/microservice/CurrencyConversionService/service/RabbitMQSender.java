package com.example.microservice.CurrencyConversionService.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	public String send(String message){
		rabbitTemplate.convertAndSend("Test-Exchange","test",message);
		return "Message Sent";
	}
}
