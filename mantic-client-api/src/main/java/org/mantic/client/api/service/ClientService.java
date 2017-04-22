package org.mantic.client.api.service;

import com.mantichub.commons.domain.QueryResult;
import com.mantichub.commons.resource.ResourceObject;

public interface ClientService {

	QueryResult query(ResourceObject resource);

	QueryResult query(ResourceObject resource, Long radius);

}
