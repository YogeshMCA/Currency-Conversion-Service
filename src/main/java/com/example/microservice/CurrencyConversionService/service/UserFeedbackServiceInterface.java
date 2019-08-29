package com.example.microservice.CurrencyConversionService.service;

import java.util.Optional;

import com.example.microservice.CurrencyConversionService.Bean.UserFeedback;

public interface UserFeedbackServiceInterface {
	public UserFeedback getDetails(Long Id);
	public UserFeedback updateUserFeedback(UserFeedback userFeedback);
	public String deleteUserFeedback(Long Id);
	public UserFeedback saveUserFeedback(UserFeedback userFeedback);
}
