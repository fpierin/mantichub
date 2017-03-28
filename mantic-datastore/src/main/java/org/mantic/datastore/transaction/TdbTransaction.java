package org.mantic.datastore.transaction;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;

public abstract class TdbTransaction {

	private final Dataset dataset;
	private final ReadWrite readWrite;

	public TdbTransaction(final Dataset dataset, final ReadWrite readWrite) {
		this.dataset = dataset;
		this.readWrite = readWrite;
	}

	public abstract void execute();
	
	public void start() {
		if (!dataset.isInTransaction()) {
			dataset.begin(readWrite);
		}
		try {
			execute();
		} finally {
			if (dataset.isInTransaction()) {
				dataset.end();
			}
		}
	}

}
