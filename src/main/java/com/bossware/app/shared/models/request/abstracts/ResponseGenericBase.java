package com.bossware.app.shared.models.request.abstracts;

public interface ResponseGenericBase<T> {

	T getData();
	T setData(T t);
	String getDataWithString();
	
	
	boolean isSuccess();
	int StatusCode();
	
	Exception getException();
	String	getDescription();
	
}