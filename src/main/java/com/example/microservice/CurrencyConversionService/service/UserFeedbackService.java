package com.example.microservice.CurrencyConversionService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	
	public List<UserFeedback> getDetails() {
		return userFeedbackRepository.findAll();
	}
	
	public Page<UserFeedback> getDetailsByPagination(int page,int size) {
		Pageable pagable = new PageRequest(page, size);
		return userFeedbackRepository.findAll(pagable);
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
