package com.example.microservice.CurrencyConversionService.service;

import java.math.BigDecimal;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.microservice.CurrencyConversionService.Bean.CurrencyConversionBean;

//@FeignClient(name="exchange-service",url= "${feign.client.url}")
@FeignClient("exchange-service")
@RibbonClient("exchange-service") //Added multiple service with the exchange-service name in application.properties file for load balancing
public interface CurrencyExchangeProxy {
	@GetMapping("/exchange-service/from/{from}/to/{to}")
	public CurrencyConversionBean getExchangeValue(@PathVariable("from") String from,@PathVariable("to") String to);
}
