package com.bossware.app.api.shared.models.request;

import com.bossware.app.api.shared.entities.BaseEntity;
import com.bossware.app.api.shared.models.request.abstracts.RequestGenericBase;

public class RequestBaseModel<T extends BaseEntity> implements RequestGenericBase<T> {
	private T t;

	public RequestBaseModel(T t) {
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
