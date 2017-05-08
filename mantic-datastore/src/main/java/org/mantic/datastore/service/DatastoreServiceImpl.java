package org.mantic.datastore.service;

import static com.mantichub.core.util.ListUtils.isEmpty;
import static com.mantichub.core.util.StringUtils.isBlank;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.mantic.datastore.jms.MessageProducer;
import org.mantic.datastore.query.service.QueryService;
import org.mantic.datastore.repository.DatastoreRepository;

import com.mantichub.commons.domain.DatastoreTriple;
import com.mantichub.commons.domain.QueryResult;
import com.mantichub.commons.resource.ResourceObject;

@Named("datastoreService")
public class DatastoreServiceImpl implements DatastoreService {

	private final DatastoreRepository datastoreRepository;
	private final MessageProducer messageProducer;
	private final QueryService queryService;

	@Inject
	public DatastoreServiceImpl(final DatastoreRepository datastoreRepository, final QueryService queryService, final MessageProducer messageProducer) {
		this.datastoreRepository = datastoreRepository;
		this.queryService = queryService;
		this.messageProducer = messageProducer;
	}

	@Override
	public void create(final List<DatastoreTriple> triples) {
		if (!isEmpty(triples)) {
			for (final DatastoreTriple triple : triples) {
				messageProducer.send(triple);
			}
		}
	}

	@Override
	public QueryResult query(final ResourceObject resource, final Double radius, final Integer limit) {
		final String queryString = queryService.buildQuery(resource, radius, limit);
		return doQuery(queryString);
	}

	@Override
	public void infer(final String url) {
		try {
			if (isBlank(url)) {
				datastoreRepository.infer();
			} else if ("local".equals(url)) {
				datastoreRepository.inferRules();
			} else {
				datastoreRepository.infer(url);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String query(final String query, final String output) {
		final String result = datastoreRepository.query(query, output);
		return result;
	}

	@Override
	public QueryResult query(final String query, final Double radius) {
		return doQuery(query);
	}
	
	private QueryResult doQuery(final String queryString) {
		final String jsonResources = query(queryString, "json");
		final QueryResult result = new QueryResult();
		result.setSparqlQuery(queryString);
		result.setResources(queryService.map(jsonResources));
		return result;
	}	

}
