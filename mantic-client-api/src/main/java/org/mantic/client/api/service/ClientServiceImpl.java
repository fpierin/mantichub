package org.mantic.client.api.service;

import javax.inject.Inject;
import javax.inject.Named;

import org.mantic.datastore.client.api.DatastoreApi;

import com.mantichub.commons.domain.QueryResult;
import com.mantichub.commons.resource.ResourceObject;

@Named("clientService")
public class ClientServiceImpl implements ClientService {
	
	private final DatastoreApi datastoreApi;

	@Inject
	public ClientServiceImpl(final DatastoreApi datastoreApi) {
		this.datastoreApi = datastoreApi;
	}

	@Override
	public QueryResult query(final ResourceObject resource) {
		return datastoreApi.query(resource, 0l);
	}

	@Override
	public QueryResult query(final ResourceObject resource, final Long radius) {
		return datastoreApi.query(resource, radius);
	}

}
