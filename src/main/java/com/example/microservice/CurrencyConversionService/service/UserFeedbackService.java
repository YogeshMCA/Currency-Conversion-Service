package com.example.microservice.CurrencyConversionService.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.microservice.CurrencyConversionService.Bean.UserFeedback;
import com.example.microservice.CurrencyConversionService.repository.UserFeedbackRepository;

@Service
public class UserFeedbackService implements UserFeedbackServiceInterface{
	
	@Autowired
	public UserFeedbackRepository userFeedbackRepository; 
	
	public UserFeedback getDetails(Long id) {
		return userFeedbackRepository.findById(id).get();
	}

	@Override
	public UserFeedback updateUserFeedback(UserFeedback userFeedback) {
		return userFeedbackRepository.save(userFeedback);
	}

	@Override
	public String deleteUserFeedback(Long Id) {
		String result ="Deleted";
		try{
			userFeedbackRepository.deleteById(Id);
		}catch(Exception e){
			result = "Error whie deleting";
		}
		return result;
	}

	@Override
	public UserFeedback saveUserFeedback(UserFeedback userFeedback) {
		return userFeedbackRepository.save(userFeedback);
	}
	
}
