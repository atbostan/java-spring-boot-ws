package com.bossware.app.shared.models.request.abstracts;

import java.io.Serializable;

public interface ResponseGenericBase<T> extends Serializable {

	T getData();
	T setData(T t);
	String getDataWithString();
	
	
	boolean isSuccess();
	int StatusCode();
	
	String	getDescription();
	
}
