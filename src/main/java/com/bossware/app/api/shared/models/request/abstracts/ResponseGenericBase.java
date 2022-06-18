package com.bossware.app.api.shared.models.request.abstracts;

import java.util.Optional;

public interface ResponseGenericBase<T> {

	T getData();
	String getDataWithString();
	
	
	boolean isSuccess();
	int StatusCode();
	
	Exception getException();
	String	getDescription();
	
}
