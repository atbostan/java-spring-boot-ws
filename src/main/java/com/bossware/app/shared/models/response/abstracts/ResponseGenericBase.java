package com.bossware.app.shared.models.response.abstracts;

import java.io.Serializable;

public interface ResponseGenericBase<T>  extends Serializable{

	T getData();
	T setData(T t);
	String getDataWithString();
	
	
	boolean isSuccess();
	int StatusCode();
	
	Exception getException();
	String	getDescription();
	
}
