package org.mantic.datastore.query.service;

import java.util.Set;

import org.mantic.datastore.query.domain.Binding;

import com.mantichub.commons.resource.ResourceObject;

public interface QueryService {

	String buildQuery(ResourceObject resource, Double radius, Integer limit);

	Set<ResourceObject> map(String jsonResources);

	ResourceObject resourceFrom(Binding b);


}
