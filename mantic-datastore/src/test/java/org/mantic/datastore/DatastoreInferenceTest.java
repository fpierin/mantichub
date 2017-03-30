package org.mantic.datastore;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.tdb.base.file.Location;
import org.apache.jena.tdb.setup.StoreParams;
import org.apache.jena.tdb.setup.StoreParamsBuilder;
import org.apache.jena.util.FileManager;

public class DatastoreInferenceTest {
	
	public static void main(final String[] args) {
		final Dataset dataset = loadDataset();
//		createData(dataset);
		query(dataset);
		
	}

	private static void query(final Dataset dataset) {
		dataset.begin(ReadWrite.READ);
		final Model namedModel = dataset.getNamedModel("teste2");
		final Query query = QueryFactory.create(
//				"SELECT * { ?s ?p ?o }"
		"PREFIX rdfs:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
		"PREFIX mantichub:<http://www.wemantic.com/events#>\n" +
		"PREFIX schema:<http://schema.org/>\n" +
		"SELECT * WHERE { ?s rdfs:type schema:Event }"
		);
		final QueryExecution qexec = QueryExecutionFactory.create(query, namedModel);
		final ResultSet rs = qexec.execSelect();
		ResultSetFormatter.outputAsJSON(System.out, rs);
		dataset.end();
	}

	protected static Model createData(final Dataset dataset) {
		dataset.begin(ReadWrite.WRITE);
		final Model namedModel = dataset.getNamedModel("teste2");

		makeDefaultInferences(namedModel);
		addStatement(namedModel, "http://schema.org/ExhibitionEvent", "http://www.w3.org/2000/01/rdf-schema#subClassOf", "http://schema.org/Event");
		addStatement(namedModel, 
				"http://www.wemantic.com/events#OqueaFisicaeosfisicospodemfazerparaasuasaude", 
				"http://www.w3.org/1999/02/22-rdf-syntax-ns#type", 
				"http://schema.org/ExhibitionEvent");
		makeModelInferences(namedModel);
		
		dataset.commit();
		dataset.end();
		return namedModel;
	}

	private static Model makeModelInferences(final Model namedModel) {
		final InfModel infModel2 = ModelFactory.createRDFSModel(namedModel);
		namedModel.add(infModel2);
		return namedModel;
	}

	private static void addStatement(final Model namedModel, final String subject, final String predicate,
			final String object) {
		final Resource jenaSubject = namedModel.createResource(subject);
		final Property jenaProperty = namedModel.createProperty(predicate);
		final Resource jenaObject = namedModel.createResource(object);
		final Statement s = namedModel.createStatement(jenaSubject, jenaProperty, jenaObject);
		namedModel.add(s);
	}

	private static Model makeDefaultInferences(final Model namedModel) {
		final Model m = FileManager.get().loadModel("http://topbraid.org/schema/schema.rdf");
		final InfModel infModel3 = ModelFactory.createRDFSModel(m);
		namedModel.add(infModel3);
		return namedModel;
	}

	private static Dataset loadDataset() {
		final String path = "/opt/apps/mantichub/datastore";
		final Location location = Location.create(path);
		final StoreParams params = StoreParamsBuilder.create().build();
		TDBFactory.setup(location, params);
		final Dataset dataset = TDBFactory.createDataset(location);
		return dataset;
	}

}
