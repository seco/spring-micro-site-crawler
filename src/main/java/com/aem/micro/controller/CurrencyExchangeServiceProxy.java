package com.aem.micro.controller;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.aem.micro.RibbonConfiguration;
//this way wecan call only one specific instance of spring-micro-business-remoting running on 8000 port 
//if multipe instances are running on different ports the ribbon is used along with FeignClient
//@FeignClient(name = "spring-micro-business-remoting", url = "http://localhost:8000")

//this way we were directly communicating with spring-micro-business-remoting noth through api proxy
//@FeignClient(name = "spring-micro-business-remoting")

@FeignClient(name = "spring-micro-netflix-zuul-api-gateway-server")
@RibbonClient(name = "spring-micro-business-remoting", configuration = RibbonConfiguration.class)
public interface CurrencyExchangeServiceProxy {

	//@GetMapping("/currency-exchange/from/{from}/to/{to}")
	@GetMapping("/spring-micro-business-remoting/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from,
			@PathVariable("to") String to);
}
