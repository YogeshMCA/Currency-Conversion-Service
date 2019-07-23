package com.example.microservice.CurrencyConversionService.service;

import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.microservice.CurrencyConversionService.Bean.SampleMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RabbitMQReceiver {

	//@RabbitListener(queues = "Test-Queue")
	public void getMessageFromQueue(String jsonString) throws IOException{
		SampleMessage msg = new ObjectMapper().readValue(jsonString, SampleMessage.class);
		System.out.println("=========Message Received from Queue=========>"+msg.getName());
	}
}
