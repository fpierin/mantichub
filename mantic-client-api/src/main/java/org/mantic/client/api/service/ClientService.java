package org.mantic.client.api.service;

import com.mantichub.commons.domain.QueryResult;
import com.mantichub.commons.resource.Resource;

public interface ClientService {

	QueryResult query(Resource resource);

	QueryResult query(Resource resource, Long radius);

}
