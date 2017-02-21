package org.mantic.datastore.repository;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.tdb.TDBFactory;

public class DatastoreRepositoryImpl implements DatastoreRepository {

	private final Dataset dataset;
	private final String modelName;

	public DatastoreRepositoryImpl(final String path, final String modelName) {
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
	public ResultSet query(final String queryString) {
		dataset.begin(ReadWrite.READ);
		try {
			final Query query = QueryFactory.create(queryString);
			final QueryExecution qexec = QueryExecutionFactory.create(query, dataset.getNamedModel(modelName));
			final ResultSet execSelect = qexec.execSelect();
			return execSelect;
		} finally {
			dataset.end();
		}
	}

	@Override
	public void create(final String subject, final String property, final String object) {
		Model model = null;
		dataset.begin(ReadWrite.WRITE);
		try {
			model = dataset.getNamedModel(modelName);
			final Resource jenaSubject = model.createResource(subject);
			final Property jenaProperty = model.createProperty(property);
			final Resource jenaObject = model.createResource(object);
			final Statement stmt = model.createStatement(jenaSubject, jenaProperty, jenaObject);
			model.add(stmt);
			dataset.commit();
		} finally {
			if (model != null) {
				model.close();
			}
			dataset.end();
		}

	}

}
