package org.mantic.datastore.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.rdf.model.impl.StmtIteratorImpl;
import org.apache.jena.tdb.TDBFactory;

import com.mantichub.commons.domain.DatastoreTriple;
import com.mantichub.commons.domain.TripleNode;

public class DatastoreRepositoryImpl implements DatastoreRepository {

	private final Dataset dataset;
	private final String modelName;

	public DatastoreRepositoryImpl(final String path, final String modelName) {
		this.modelName = modelName;
		dataset = TDBFactory.createDataset(path);
	}

	@Override
	public void create(final Model model) {
		if (model != null) {
			create(model.listStatements());
		}
	}

	@Override
	public void create(final StmtIterator stmts) {
		dataset.begin(ReadWrite.WRITE);
		Model model = null;
		try {
			model = dataset.getNamedModel(modelName);
			while (stmts.hasNext()) {
				model.add(stmts.next());
			}
			dataset.commit();
		} finally {
			if (model != null) {
				model.close();
			}
			dataset.end();
		}
	}

	private void create(final Statement stmt) {
		if (stmt != null) {
			final List<Statement> list = new ArrayList<>();
			list.add(stmt);
			create(new StmtIteratorImpl(list.iterator()));
		}

	}

	@Override
	public void create(final String subject, final String predicate, final String object) {
		dataset.begin(ReadWrite.READ);
		Model model = null;
		Statement stmt = null;
		try {
			model = dataset.getNamedModel(modelName);
			final Resource jenaSubject = model.createResource(subject);
			final Property jenaProperty = model.createProperty(predicate);
			final Resource jenaObject = model.createResource(object);
			stmt = model.createStatement(jenaSubject, jenaProperty, jenaObject);
		} finally {
			if (model != null) {
				model.close();
			}
			dataset.end();
		}
		create(stmt);
	}

	@Override
	public void create(final DatastoreTriple triple) {
		final String subject = triple.getSubject().toString();
		final String predicate = triple.getPredicate().toString();
		final String object = triple.getObject().toString();
		this.create(subject, predicate, object);
	}

	@Override
	public List<DatastoreTriple> find(final DatastoreTriple triple) {
		final List<DatastoreTriple> results = new ArrayList<>();
		final String query = queryFrom(triple);
		final ResultSet resultSet = query(query);
		while (resultSet.hasNext()) {
			final QuerySolution next = resultSet.next();
			final RDFNode resultNode = next.get(next.varNames().next());
			final TripleNode object = new TripleNode();
			if ((resultNode != null) && !resultNode.isLiteral()) {
				final String nameSpace = resultNode.asNode().getNameSpace();
				final String uri = resultNode.asNode().getURI();
				final boolean isNamespace = nameSpace.contains("http://");
				object.setNamespace(isNamespace ? nameSpace : null);
				object.setValue(isNamespace ? uri.replaceAll(nameSpace, "") : nameSpace);
			} else {
				object.setValue(resultNode.asLiteral().toString());
			}
			results.add(new DatastoreTriple(triple.getSubject(), triple.getPredicate(), object));
			System.out.println(next);
		}
		return results;
	}

	@Override
	public String queryFrom(final DatastoreTriple triple) {
		final StringBuilder stringBuilder = new StringBuilder();
		addPrefix("s1", triple.getSubject(), stringBuilder);
		addPrefix("p1", triple.getPredicate(), stringBuilder);
		addPrefix("o1", triple.getObject(), stringBuilder);
		stringBuilder.append("SELECT * WHERE { ");
		stringBuilder.append(addParam("?s", "s1", triple.getSubject()) + " ");
		stringBuilder.append(addParam("?p", "p1", triple.getPredicate()) + " ");
		stringBuilder.append(addParam("?o", "o1", triple.getObject()) + " ");
		stringBuilder.append("}");
		return stringBuilder.toString();
	}

	private String addParam(final String arg, final String namespace, final TripleNode node) {
		if (node != null) {
			String returnValue = node.getValue().replaceAll("[^A-Za-z0-9]", "");
			if (!node.isLiteral()) {
				returnValue = namespace + ":" + returnValue;
			}
			return returnValue;
		}
		return arg;
	}

	private void addPrefix(final String prefixName, final TripleNode node, final StringBuilder stringBuilder) {
		if ((node != null) && !node.isLiteral()) {
			stringBuilder.append("PREFIX " + prefixName + ":<" + node.getNamespace() + ">\n");
		}
	}

	@Override
	public void remove(final DatastoreTriple triple) {
		Model model = null;
		try {
			dataset.begin(ReadWrite.WRITE);
			model = dataset.getNamedModel(modelName);
			final Resource jenaSubject = model.createResource(triple.getSubject().toString());
			final Property jenaProperty = model.createProperty(triple.getPredicate().toString());
			final Resource jenaObject = model.createResource(triple.getObject().toString());
			final Statement stmt = model.createStatement(jenaSubject, jenaProperty, jenaObject);
			model.remove(stmt);
			dataset.commit();
		} finally {
			if (model != null) {
				model.close();
			}
			dataset.end();
		}
	}

	@Override
	public void infer() {
		Model infModel = null;
		dataset.begin(ReadWrite.READ);
		try {
			final Model model = dataset.getNamedModel(modelName);
			infModel = ModelFactory.createRDFSModel(model);
		} finally {
			dataset.end();
		}
		create(infModel);
	}

	@Override
	public ResultSet query(final String queryString) {
		try {
			dataset.begin(ReadWrite.READ);
			final Query query = QueryFactory.create(queryString);
			final QueryExecution qexec = QueryExecutionFactory.create(query, dataset.getNamedModel(modelName));
			final ResultSet execSelect = qexec.execSelect();
			return execSelect;
		} finally {
			dataset.end();
		}
	}

}