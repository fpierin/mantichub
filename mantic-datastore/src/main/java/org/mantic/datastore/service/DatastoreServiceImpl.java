package org.mantic.datastore.service;

import static com.mantichub.core.util.ListUtils.isEmpty;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.jena.ext.com.google.common.util.concurrent.ListeningExecutorService;
import org.apache.jena.ext.com.google.common.util.concurrent.MoreExecutors;
import org.apache.jena.graph.Node;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.sparql.resultset.JSONOutput;
import org.apache.jena.util.FileManager;
import org.mantic.datastore.jms.MessageProducer;
import org.mantic.datastore.repository.DatastoreRepository;

import com.mantichub.commons.domain.DatastoreQuery;
import com.mantichub.commons.domain.DatastoreTriple;
import com.mantichub.commons.domain.TripleNode;

@Named("datastoreService")
public class DatastoreServiceImpl implements DatastoreService {

	protected static final ExecutorService executorService = Executors.newFixedThreadPool(10);
	protected static final ListeningExecutorService service = MoreExecutors.listeningDecorator(executorService);

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

	@Override
	public void infer() {
		try {
			final Model m = FileManager.get().loadModel("http://topbraid.org/schema/schema.rdf");
			final InfModel infModel = ModelFactory.createRDFSModel(m);
			infer(infModel.listStatements());
			infer(datastoreRepository.infer());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void infer(final StmtIterator stmts) throws Exception {
		while (stmts != null && stmts.hasNext()) {
			final Statement stmt = stmts.next();

			final Node subjectNode = stmt.asTriple().getSubject();
			final Node predicateNode = stmt.asTriple().getPredicate();
			final Node objectNode = stmt.asTriple().getObject();

			final TripleNode subject = buildTripleNode(subjectNode);
			final TripleNode predicate = buildTripleNode(predicateNode);
			final TripleNode object = buildTripleNode(objectNode);

			if (subject != null && predicate != null && object != null) {
				final Serializable triple = new DatastoreTriple(subject, predicate, object);
				messageProducer.send(triple);
			}
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void infer2() {
		final Model m = FileManager.get().loadModel("http://topbraid.org/schema/schema.rdf");
		Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
		PelletReasonerFactory.theInstance().create();
		final InfModel infModel = ModelFactory.createInfModel(reasoner, m);
		datastoreRepository.create(infModel);
		
//		Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
//		reasoner = reasoner.bindSchema(schema);
//		InfModel infmodel = ModelFactory.createInfModel(reasoner, data)
		
//		final Model m2 = FileManager.get().loadModel("https://www.w3.org/TR/rdf-schema/");
//		final InfModel infModel2 = ModelFactory.createRDFSModel(m2);
//		datastoreRepository.create(infModel2);
		
//		
			
//		datastoreRepository.infer2();
		
		
	}

}
