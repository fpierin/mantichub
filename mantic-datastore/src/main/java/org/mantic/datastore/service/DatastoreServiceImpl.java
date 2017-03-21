package org.mantic.datastore.service;

import static com.mantichub.core.util.ListUtils.isEmpty;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.jena.query.ResultSet;
import org.apache.jena.sparql.resultset.JSONOutput;
import org.mantic.datastore.jms.MessageProducer;
import org.mantic.datastore.repository.DatastoreRepository;

import com.mantichub.commons.domain.DatastoreQuery;
import com.mantichub.commons.domain.DatastoreTriple;

@Named("datastoreService")
public class DatastoreServiceImpl implements DatastoreService {

	private final DatastoreRepository datastoreRepository;
	private final MessageProducer messageProducer;
	
	@Inject
	public DatastoreServiceImpl(final DatastoreRepository datastoreRepository, final MessageProducer messageProducer) {
		this.datastoreRepository = datastoreRepository;
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
	public String query(final DatastoreQuery datastoreQuery) {
		final ResultSet resultSet = datastoreRepository.query(datastoreQuery.getQuery());
		final JSONOutput jOut = new JSONOutput();
		final String result = jOut.asString(resultSet);
		return result;
	}
	
}
