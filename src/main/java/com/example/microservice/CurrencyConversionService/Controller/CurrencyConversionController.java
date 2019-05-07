package com.example.microservice.CurrencyConversionService.Controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.microservice.CurrencyConversionService.Bean.CurrencyConversionBean;
import com.example.microservice.CurrencyConversionService.service.CurrencyExchangeProxy;

@RestController
public class CurrencyConversionController {

	@Autowired
	private CurrencyExchangeProxy proxy;
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convert(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity){
		
		Map<String,String> val = new  HashMap<>();
		val.put("from", from);
		val.put("to", to);
		ResponseEntity<CurrencyConversionBean> response = new RestTemplate().getForEntity(
				"http://localhost:8000/exchange-service/from/{from}/to/{to}",CurrencyConversionBean.class,val);
		CurrencyConversionBean ccBean = response.getBody();
		
		return new CurrencyConversionBean(ccBean.getId(), ccBean.getFrom(),ccBean.getTo(),quantity,ccBean.getConversionMultiple(),ccBean.getConversionMultiple().multiply(quantity),0);
	}
	
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean feignConvert(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity){
		
		CurrencyConversionBean ccBean = proxy.getExchangeValue(from, to);
		return new CurrencyConversionBean(ccBean.getId(), ccBean.getFrom(),ccBean.getTo(),quantity,ccBean.getConversionMultiple(),ccBean.getConversionMultiple().multiply(quantity),0);
	}
}
