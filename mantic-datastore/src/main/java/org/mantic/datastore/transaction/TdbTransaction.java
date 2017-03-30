package org.mantic.datastore.transaction;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;

public abstract class TdbTransaction<T> {

	private final Dataset dataset;
	private final ReadWrite readWrite;

	public TdbTransaction(final Dataset dataset, final ReadWrite readWrite) {
		this.dataset = dataset;
		this.readWrite = readWrite;
	}

	public abstract T routine();
	
	public T execute() {
		if (!dataset.isInTransaction()) {
			dataset.begin(readWrite);
		}
		try {
			T result = routine();
			if (dataset.isInTransaction()) {
				dataset.commit();
			}
			return result; 
		} catch (final Exception e) {
			if (dataset.isInTransaction()) {
				dataset.abort();
			}
			throw e;
		} finally {
			if (dataset.isInTransaction()) {
				dataset.end();
			}
		}
	}

}
