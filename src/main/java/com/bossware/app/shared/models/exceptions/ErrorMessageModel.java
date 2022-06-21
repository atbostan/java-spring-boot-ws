package com.bossware.app.shared.models.exceptions;

import java.util.Date;

public class ErrorMessageModel {
	private Date timeStamp;
	private String message;
	private String classInfo;
	

	public ErrorMessageModel(Date date, String message, String classInfo) {

		this.timeStamp = date;
		this.message = message;
		this.classInfo= classInfo;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getClassInfo() {
		return classInfo;
	}
	public void setClassInfo(String classInfo) {
		this.classInfo = classInfo;
	}

}
