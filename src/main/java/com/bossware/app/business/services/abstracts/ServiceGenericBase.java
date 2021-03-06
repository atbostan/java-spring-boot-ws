package com.bossware.app.business.services.abstracts;

import java.util.List;

import com.bossware.app.shared.models.response.ResponseBaseModel;

public interface ServiceGenericBase<T> {
	ResponseBaseModel<T> create(T t);

	ResponseBaseModel<T> getEntityById(String id);

	ResponseBaseModel<T> update(String id, T t);

	void delete(String id);

	ResponseBaseModel<List<T>> getAll(int page, int limit);

}
