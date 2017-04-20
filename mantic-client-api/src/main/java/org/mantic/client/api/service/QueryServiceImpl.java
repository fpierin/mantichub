package org.mantic.client.api.service;

import javax.inject.Inject;
import javax.inject.Named;

import org.mantic.datastore.client.api.DatastoreApi;

@Named("queryService")
public class QueryServiceImpl implements QueryService {
	
	private DatastoreApi datastoreApi;

	@Inject
	public QueryServiceImpl(final DatastoreApi datastoreApi) {
		this.datastoreApi = datastoreApi;
	}

	@Override
	public void query(final String query) {
		datastoreApi.query(query);
		
	}

}
