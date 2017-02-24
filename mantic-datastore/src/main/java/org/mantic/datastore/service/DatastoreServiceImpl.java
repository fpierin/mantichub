package org.mantic.datastore.service;

import static com.mantichub.core.util.ListUtils.isEmpty;

import java.util.List;

import javax.inject.Named;

import org.apache.jena.query.ResultSet;
import org.apache.jena.sparql.resultset.JSONOutput;
import org.mantic.datastore.repository.DatastoreRepository;
import org.mantic.datastore.repository.DatastoreRepositoryImpl;

import com.mantichub.commons.domain.DatastoreQuery;
import com.mantichub.commons.domain.DatastoreTriple;

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
		if (isEmpty(triples)) {
			return;
		}

		for (final DatastoreTriple triple : triples) {
			if (shouldCreate(triple)) {
				System.out.println("Criando " + triple);
				datastoreRepository.create(triple);
				System.out.println("Tripla criada com sucesso");
			} else {
				System.out.println("Tripla ja existe " + triple);
			}
		}
	}
	
	private boolean shouldCreate(final DatastoreTriple triple) {
		final DatastoreTriple tripleFilter = triple.clone();
		tripleFilter.setObject(null);
		final List<DatastoreTriple> filterResults = datastoreRepository.find(tripleFilter);

		if (filterResults.size() == 1) {
			if (filterResults.get(0).equals(triple)) {
				return false;
			}
		}

		for (final DatastoreTriple datastoreTriple : filterResults) {
			datastoreRepository.remove(datastoreTriple);
		}
		
		return true;

	}

	@Override
	public String query(final DatastoreQuery datastoreQuery) {
		final ResultSet resultSet = datastoreRepository.query(datastoreQuery.getQuery());
		final JSONOutput jOut = new JSONOutput();
		final String result = jOut.asString(resultSet);
		return result;
	}

}
