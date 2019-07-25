package com.example.microservice.CurrencyConversionService.service;


import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.microservice.CurrencyConversionService.Bean.CurrencyConversionBean;

/*
 * Spring Cloud integrates Ribbon and Eureka to provide a load balanced http client when using Feign.
 * In the @FeignClient annotation the String value ("curr-exchange" above) is an arbitrary client name, which is used to create a Ribbon load balancer
 * The Ribbon client above will want to discover the physical addresses for the "curr-exchange" service. If your application is a Eureka client then it will resolve the service in the Eureka service registry. 
 * If you don’t want to use Eureka, you can simply configure a list of servers
 */
//@FeignClient(name="exchange-service",url= "${feign.client.url}")
@FeignClient("curr-exchange")
@RibbonClient("curr-exchange") //Added multiple service with the exchange-service name in application.properties file for load balancing
public interface CurrencyExchangeProxy {
	@GetMapping("/exchange-service/from/{from}/to/{to}")
	public CurrencyConversionBean getExchangeValue(@PathVariable("from") String from,@PathVariable("to") String to);
}
