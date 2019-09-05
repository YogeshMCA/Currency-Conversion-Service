package com.example.microservice.CurrencyConversionService.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.microservice.CurrencyConversionService.Bean.UserFeedback;

public interface UserFeedbackServiceInterface {
	public UserFeedback getDetails(Long Id);
	public List<UserFeedback> getDetails();
	public UserFeedback updateUserFeedback(UserFeedback userFeedback);
	public String deleteUserFeedback(Long Id);
	public UserFeedback saveUserFeedback(UserFeedback userFeedback);
	public Page<UserFeedback> getDetailsByPagination(int page,int size);
	
}
