package org.mantic.datastore;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.tdb.TDBFactory;

public class TdbRepositoryImpl implements TdbRepository {

	private final Dataset dataset;
	private String modelName;

	public TdbRepositoryImpl(final String path, String modelName) {
		this.modelName = modelName;
		dataset = TDBFactory.createDataset(path);
	}

	@Override
	public void create(final Model model) {
		dataset.begin(ReadWrite.WRITE);
		try {
			dataset.addNamedModel(modelName, model);
			dataset.commit();
		} finally {
			dataset.end();
		}
	}

	@Override
	public void read() {
	}

	@Override
	public void update() {
	}

	@Override
	public void delete() {
	}

	@Override
	public ResultSet query(final String queryString) {
		dataset.begin(ReadWrite.READ);
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, dataset.getNamedModel(modelName));
		ResultSet execSelect = qexec.execSelect();
		dataset.close();
		return execSelect;

	}

}
