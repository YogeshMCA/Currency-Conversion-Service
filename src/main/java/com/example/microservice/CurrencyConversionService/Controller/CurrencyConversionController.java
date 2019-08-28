package com.example.microservice.CurrencyConversionService.Controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.microservice.CurrencyConversionService.Bean.CurrencyConversionBean;
import com.example.microservice.CurrencyConversionService.Bean.UserFeedback;
import com.example.microservice.CurrencyConversionService.service.CurrencyExchangeProxy;
import com.example.microservice.CurrencyConversionService.service.RabbitMQSender;
import com.example.microservice.CurrencyConversionService.service.UserFeedbackServiceInterface;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@RestController
@CrossOrigin(origins="*",allowedHeaders="*",allowCredentials="true")
public class CurrencyConversionController {

	@Autowired
	private CurrencyExchangeProxy proxy;
	
	@Autowired
	private RabbitMQSender rabbitMQSender;
	
	@Autowired
	private UserFeedbackServiceInterface userFeedbackService;
	
	//RestTemplate - To communicate other services
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convert(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity){
		
		Map<String,String> val = new  HashMap<>();
		val.put("from", from);
		val.put("to", to);
		/*ResponseEntity<CurrencyConversionBean> response = new RestTemplate().getForEntity(
				"http://localhost:8000/exchange-service/from/{from}/to/{to}",CurrencyConversionBean.class,val);*/
		CurrencyConversionBean ccBean = new RestTemplate().getForObject(
				"https://currexchange.cfapps.io/exchange-service/from/{from}/to/{to}",CurrencyConversionBean.class,val);
			
		//CurrencyConversionBean ccBean = response.getBody();
		
		return new CurrencyConversionBean(ccBean.getId(), ccBean.getFrom(),ccBean.getTo(),quantity,ccBean.getConversionMultiple(),ccBean.getConversionMultiple().multiply(quantity),0);
	}
	
	//Feign - To communicate other services in an easiest way
	@HystrixCommand(fallbackMethod="fallBack")
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean feignConvert(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity){
		
		CurrencyConversionBean ccBean = proxy.getExchangeValue(from, to);
		return new CurrencyConversionBean(ccBean.getId(), ccBean.getFrom(),ccBean.getTo(),quantity,ccBean.getConversionMultiple(),ccBean.getConversionMultiple().multiply(quantity),ccBean.getPort());
	}
	
	public CurrencyConversionBean fallBack(String from,String to, BigDecimal quantity) {
		return new CurrencyConversionBean();
	}
	
	@GetMapping("/currency-converter-rabbit/{message}")
	public String aSynchCall(@PathVariable String message){
		return rabbitMQSender.send(message);
		
	}
	
	@GetMapping("/user-feedback/id/{id}")
	public UserFeedback getUserFeedbackDetails(@PathVariable String id) {
		return userFeedbackService.getDetails(Long.getLong(id));
	
	}
}
