package com.example.microservice.CurrencyConversionService.Bean;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

@Entity(name="USER_FEEDBACK")
public class UserFeedback implements Serializable{
	
	@Id
	@GeneratedValue
	private Long ID;
	
	@Column(name="USER_NAME")
	private String uName;
	@Column(name="EMAIL_T")
	private String email;
	@Column(name="MOBILE_T")
	private String mobile;
	@Column(name="FOOD_T")
	private String food;
	@Column(name="LIKED_T")
	private String liked;
	@Column(name="N_IMPMT_T")
	private boolean nImp;
	@Column(name="CREATE_DATE")
	private Date createDate;
	
	public UserFeedback() {
		
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getFood() {
		return food;
	}
	public void setFood(String food) {
		this.food = food;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getLiked() {
		return liked;
	}
	public void setLiked(String liked) {
		this.liked = liked;
	}
	public boolean isnImp() {
		return nImp;
	}
	public void setnImp(boolean nImp) {
		this.nImp = nImp;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	

}
