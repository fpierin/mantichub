package org.mantic.client.api.service;

import javax.inject.Inject;
import javax.inject.Named;

import org.mantic.datastore.client.api.DatastoreApi;

import com.mantichub.commons.domain.QueryResult;
import com.mantichub.commons.resource.Resource;

@Named("clientService")
public class ClientServiceImpl implements ClientService {
	
	private final DatastoreApi datastoreApi;

	@Inject
	public ClientServiceImpl(final DatastoreApi datastoreApi) {
		this.datastoreApi = datastoreApi;
	}

	@Override
	public QueryResult query(final Resource resource) {
		return datastoreApi.query(resource, null);
	}

	@Override
	public QueryResult query(final Resource resource, final Long radius) {
		return datastoreApi.query(resource, radius);
	}

}
