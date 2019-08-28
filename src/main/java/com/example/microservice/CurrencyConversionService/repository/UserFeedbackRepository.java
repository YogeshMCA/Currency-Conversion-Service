package com.example.microservice.CurrencyConversionService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.microservice.CurrencyConversionService.Bean.UserFeedback;

public interface UserFeedbackRepository extends JpaRepository<UserFeedback, Long>{
	public UserFeedback findById(Long Id);
}
