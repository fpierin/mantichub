package org.mantic.datastore.service;

import static com.mantichub.core.util.ListUtils.isNotEmpty;

import java.util.List;

import javax.inject.Named;

import org.apache.jena.query.ResultSet;
import org.apache.jena.sparql.resultset.JSONOutput;
import org.mantic.datastore.repository.DatastoreRepository;
import org.mantic.datastore.repository.DatastoreRepositoryImpl;
import org.mantichub.commons.domain.DatastoreQuery;
import org.mantichub.commons.domain.DatastoreTriple;

@Named("datastoreService")
public class DatastoreServiceImpl implements DatastoreService {

	private final DatastoreRepository datastoreRepository;

	public DatastoreServiceImpl() {
		final String path = "/opt/apps/mantichub/datastore";
		final String modelname = "teste";
		datastoreRepository = new DatastoreRepositoryImpl(path, modelname);
	}

	@Override
	public void create(final List<DatastoreTriple> triples) {
		if (isNotEmpty(triples)) {
			for (final DatastoreTriple triple : triples) {
				final String subject = triple.getSubject();
				final String predicate = triple.getPredicate();
				final String object = triple.getObject();
				System.out.println("Criando " + triple);
				datastoreRepository.create(subject, predicate, object);
				System.out.println("Tripla criada com sucesso");
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
