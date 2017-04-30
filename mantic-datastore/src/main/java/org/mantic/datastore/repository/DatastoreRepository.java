package org.mantic.datastore.repository;

import java.util.List;

import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.StmtIterator;
import org.mantic.datastore.jms.MessageProducer;

import com.mantichub.commons.domain.DatastoreTriple;

public interface DatastoreRepository {
	
	void create(Model model);

	void create(DatastoreTriple triple);
	
	void create(String subject, String predicate, String object);
	
	ResultSet query(String query);

	List<DatastoreTriple> find(DatastoreTriple triple);

	String queryFrom(DatastoreTriple triple);
	
	void remove(DatastoreTriple datastoreTriple);

	void create(StmtIterator stmts);

	void infer();

	void infer(String string);

	void infer(MessageProducer messageProducer, String... urls) throws Exception;

	String query(String query, String output);
	
}
