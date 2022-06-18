package com.bossware.app.api.shared.models.request;

import java.net.http.HttpResponse;

import org.springframework.http.HttpStatus;

import com.bossware.app.api.shared.entities.BaseEntity;
import com.bossware.app.api.shared.models.request.abstracts.ResponseGenericBase;

public class ResponseBaseModel <T> implements ResponseGenericBase<T> {

	
	private T t;
	private Exception e;
	private HttpStatus status;
	private String description;

	public ResponseBaseModel(T t, HttpStatus status) {
		this.t = t;
		this.status = status;
	}
	
	public ResponseBaseModel(T t, HttpStatus status,Exception e,String description) {
		this.t = t;
		this.e = e;
		this.status = status;
		this.description=description;
	}

	@Override
	public T getData() {
		return t;
	}

	@Override
	public String getDataWithString() {
		return t.toString();
	}
	
	@Override
	public boolean isSuccess() {
		if (status.value() == 200 || status.value() == 201 || status.value()== 202
				|| status.value() == 203 || status.value() == 204 || status.value() == 205)
			return true;
		else
			return false;
	}

	@Override
	public int StatusCode() {
		return status.value();
	}

	@Override
	public Exception getException() {
		return e;
	}

	@Override
	public String getDescription() {
		return description;
	}
}
