package com.example.microservice.CurrencyConversionService.service;

import java.math.BigDecimal;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.microservice.CurrencyConversionService.Bean.CurrencyConversionBean;

//@FeignClient(name="exchange-service",url= "${feign.client.url}")
@FeignClient("https://currexchange")
//@RibbonClient("CurrExchange") //Added multiple service with the exchange-service name in application.properties file for load balancing
public interface CurrencyExchangeProxy {
	@GetMapping("/exchange-service/from/{from}/to/{to}")
	public CurrencyConversionBean getExchangeValue(@PathVariable("from") String from,@PathVariable("to") String to);
}
