package org.mantic.datastore.query.service;

import java.util.List;

import org.mantic.datastore.query.domain.Binding;

import com.mantichub.commons.resource.ResourceObject;

public interface QueryService {

	String buildQuery(ResourceObject resource, Double radius);

	List<ResourceObject> map(String jsonResources);

	ResourceObject resourceFrom(Binding b);

}
