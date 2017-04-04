package org.mantic.datastore.repository;

import static org.apache.jena.query.ReadWrite.READ;
import static org.apache.jena.query.ReadWrite.WRITE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.jena.graph.Node;
import org.apache.jena.graph.Triple;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.RSIterator;
import org.apache.jena.rdf.model.ReifiedStatement;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.rdf.model.impl.StmtIteratorImpl;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.tdb.base.file.Location;
import org.apache.jena.tdb.setup.StoreParams;
import org.apache.jena.tdb.setup.StoreParamsBuilder;
import org.apache.jena.util.FileManager;
import org.mantic.datastore.jms.MessageProducer;
import org.mantic.datastore.transaction.TdbTransaction;

import com.mantichub.commons.domain.DatastoreTriple;
import com.mantichub.commons.domain.TripleNode;

public class DatastoreRepositoryImpl implements DatastoreRepository {

	private final Dataset dataset;
	private final String modelName;

	public DatastoreRepositoryImpl(final String path, final String modelName) {
		this.modelName = modelName;
		final Location location = Location.create(path);
		final StoreParams params = StoreParamsBuilder.create().build();
		TDBFactory.setup(location, params);
		dataset = TDBFactory.createDataset(location);
	}

	@Override
	public void create(final Model model) {
		new TdbTransaction<Void>(dataset, WRITE) {
			@Override
			public Void routine() {
				if (model != null) {
					create(model.listStatements());
				}
				return null;
			}
		}.execute();
	}

	@Override
	public void create(final StmtIterator stmts) {
		new TdbTransaction<Void>(dataset, WRITE) {
			@Override
			public Void routine() {
				if (stmts != null) {
					final Model model = dataset.getNamedModel(modelName);
					while (stmts.hasNext()) {
						final Statement statement = stmts.next();
						model.add(statement);
					}
				}
				return null;
			}
		}.execute();
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
		create(new TdbTransaction<Statement>(dataset, READ) {
			@Override
			public Statement routine() {
				final Model model = dataset.getNamedModel(modelName);
				final Resource jenaSubject = model.createResource(subject);
				final Property jenaProperty = model.createProperty(predicate);
				final Resource jenaObject = model.createResource(object);
				return model.createStatement(jenaSubject, jenaProperty, jenaObject);
			}
		}.execute());
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
		if (triple != null && triple.isValid()) {
			new TdbTransaction<Void>(dataset, WRITE) {
				@Override
				public Void routine() {
					final Model model = dataset.getNamedModel(modelName);
					final Resource jenaSubject = model.createResource(triple.getSubject().toString());
					final Property jenaProperty = model.createProperty(triple.getPredicate().toString());
					final Resource jenaObject = model.createResource(triple.getObject().toString());
					final Statement stmt = model.createStatement(jenaSubject, jenaProperty, jenaObject);
					model.remove(stmt);
					return null;
				}
			}.execute();
		}
	}

	@Override
	public ResultSet query(final String queryString) {
		return new TdbTransaction<ResultSet>(dataset, READ) {
			@Override
			public ResultSet routine() {
				final Query query = QueryFactory.create(queryString);
				final QueryExecution qexec = QueryExecutionFactory.create(query, dataset.getNamedModel(modelName));
				final ResultSet execSelect = qexec.execSelect();
				return execSelect;
			}
		}.execute();
	}

	@Override
	public void infer() {
		infer(dataset.getNamedModel(modelName));
	}

	@Override
	public void infer(final String url) {
		infer(modelFrom(url));
	}

	private Model modelFrom(final String url) {
		return FileManager.get().loadModel(url);
	}

	private void infer(final Model model) {
		new TdbTransaction<Void>(dataset, WRITE) {
			@Override
			public Void routine() {
				final Model namedModel = dataset.getNamedModel(modelName);
				final InfModel inferences = ModelFactory.createRDFSModel(model);
				namedModel.add(inferences);
				return null;
			}
		}.execute();
	}

	@Override
	public void infer(final MessageProducer messageProducer, final String... urls) throws Exception {
		if (urls != null) {
			for (final String url : urls) {
				final Model model = modelFrom(url);
				infer(messageProducer, model);
			}
		}
		infer(messageProducer, dataset.getNamedModel(modelName));
	}

	private void infer(final MessageProducer messageProducer, final Model model) throws Exception {
		final InfModel inferences = ModelFactory.createRDFSModel(model);
		final StmtIterator stmts = inferences.listStatements();
		doCreate(messageProducer, stmts);
	}

	private void doCreate(final MessageProducer messageProducer, final StmtIterator stmts) throws Exception {
		while (stmts != null && stmts.hasNext()) {
			final Statement stmt = stmts.next();
			if (stmt.isReified()) {
				RSIterator listReifiedStatements = stmt.listReifiedStatements();
				while (listReifiedStatements.hasNext()) {
					ReifiedStatement next = listReifiedStatements.next();
					System.out.println(next);
				}
			}
			
			if (stmt.getSubject().isAnon()) {
				final StmtIterator listProperties = stmt.getBag().listProperties();
				doCreate(messageProducer, listProperties);
			} else {
				createStatement(messageProducer, stmt);
			}
		}
	}

	private void createStatement(final MessageProducer messageProducer, final Statement stmt) {
		final Triple asTriple = stmt.asTriple();
		final Node subjectNode = asTriple.getSubject();
		final Node predicateNode = asTriple.getPredicate();
		final Node objectNode = asTriple.getObject();

		final TripleNode subject = buildTripleNode(subjectNode);
		final TripleNode predicate = buildTripleNode(predicateNode);
		final TripleNode object = buildTripleNode(objectNode);

		if (subject != null && predicate != null && object != null) {
			final Serializable triple = new DatastoreTriple(subject, predicate, object);
			messageProducer.send(triple);
		}
	}

	private TripleNode buildTripleNode(final Node node) {
		try {
			if (node == null) {
				return null;
			}
			if (!node.isURI()) {
				return new TripleNode(null, node.toString());
			} else {
				final String nameSpace = node.getNameSpace();
				final String uri = node.getURI();
				return new TripleNode(nameSpace, uri.replaceAll(nameSpace, ""));
			}
		} catch (final Exception e) {
			System.out.println("Falha no node -> " + node);
			e.printStackTrace();
		}
		return null;
	}

}