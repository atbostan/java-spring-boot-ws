package com.bossware.app.shared.models.request;

import com.bossware.app.shared.models.request.abstracts.RequestGenericBase;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestBaseModel<T> implements RequestGenericBase<T> {
	private T t;

	@JsonCreator
	public RequestBaseModel(@JsonProperty("t") T t) {
		this.t = t;
	}

	@Override
	public T getData() {
		return t;
	}

	@Override
	public String getDataWithString() {
		return t.toString();
	}

	

}
