package org.mantic.datastore;

import java.util.Iterator;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.tdb.TDBFactory;

public class TdbRepositoryImpl implements TdbRepository {

	private final Dataset dataset;

	public TdbRepositoryImpl(final String path) {
		dataset = TDBFactory.createDataset(path);
	}

	@Override
	public void create(final Model model) {
		dataset.begin(ReadWrite.WRITE);
		try {
			dataset.setDefaultModel(model);
			dataset.commit();
		} finally {
			dataset.end();
		}
	}

	@Override
	public void read() {
        Iterator<Quad> iter = dataset.asDatasetGraph().find();
        while ( iter.hasNext() ) {
            Quad quad = iter.next();
            System.out.println(quad);
        }
	}

	@Override
	public void update() {
	}

	@Override
	public void delete() {
	}

	public static void main(String[] args) {
		final TdbRepository tdbRepository = new TdbRepositoryImpl("/opt/apps/mantichub/datastore");
		tdbRepository.read();
	}

}
