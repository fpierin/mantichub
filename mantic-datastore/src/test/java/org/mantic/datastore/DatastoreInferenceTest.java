package org.mantic.datastore;

import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import org.mantic.datastore.repository.DatastoreRepository;
import org.mantic.datastore.repository.DatastoreRepositoryImpl;

public class DatastoreInferenceTest {
	
	private static final String path = "/opt/apps/mantichub/datastore";
	private static final DatastoreRepository datastoreRepository = new DatastoreRepositoryImpl(path, "teste");
	
	public static void main(final String[] args) {
//		addSchemaOrgRdfModel();
//		doInferences();
//		doSimpleQuery();
//		doNoInferenceQuery();
		doInferenceQuery();
	}
	
	public static void doInferenceQuery() {
		doQuery(
		"PREFIX rdfs:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
		"PREFIX mantichub:<http://www.wemantic.com/events#>\n" +
		"PREFIX schema:<http://schema.org/>\n" +
		"SELECT * WHERE { ?s rdfs:type schema:Event }");
	}

	public static void doNoInferenceQuery() {
		doQuery(
		"PREFIX rdfs:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
		"PREFIX mantichub:<http://www.wemantic.com/events#>\n" +
		"PREFIX schema:<http://schema.org/>\n" +
		"SELECT * WHERE { ?s rdfs:type schema:ExhibitionEvent }");
	}
	
	public static void doInferences() {
		datastoreRepository.infer();		
	}

	public static void addSchemaOrgRdfModel() {
		final Model m = FileManager.get().loadModel("http://topbraid.org/schema/schema.rdf");
		final InfModel infModel = ModelFactory.createRDFSModel(m);
		datastoreRepository.create(infModel);
	}
	
	public static void doSimpleQuery() {
//		SelectBuilder sb = new SelectBuilder()
//			    .addVar( "*" )
//			    .addWhere( "?s", "?p", "?o" );
		doQuery("SELECT * { ?s ?p ?o }");
	}

	private static void doQuery(final String queryStr) {
		final ResultSet query = datastoreRepository.query(queryStr);
		ResultSetFormatter.outputAsJSON(System.out, query);
	}	

}
