package com.example.microservice.CurrencyConversionService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.microservice.CurrencyConversionService.Bean.UserFeedback;
import com.example.microservice.CurrencyConversionService.repository.UserFeedbackRepository;

@Service
public class UserFeedbackService implements UserFeedbackServiceInterface{
	
	@Autowired
	public UserFeedbackRepository userFeedbackRepository; 
	
	public UserFeedback getDetails(Long id) {
		return userFeedbackRepository.findById(id);
	}
}
