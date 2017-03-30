package org.mantic.datastore.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.mantic.datastore.repository.DatastoreRepository;

import com.mantichub.commons.domain.DatastoreTriple;

@Named("createDataServiceListener")
public class CreateDataServiceListener implements MessageListener {

	private final DatastoreRepository datastoreRepository;

	@Inject
	public CreateDataServiceListener(final DatastoreRepository datastoreRepository) {
		this.datastoreRepository = datastoreRepository;
	}

	@Override
	public void onMessage(final Message message) {
		try {
			final Serializable object = getObject(message);
			if (object instanceof DatastoreTriple) {
				final DatastoreTriple triple = DatastoreTriple.class.cast(object);
				create(triple);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	private Serializable getObject(final Message message) {
		try {
			final ObjectMessage objectMessage = ObjectMessage.class.cast(message);
			final Serializable object = objectMessage.getObject();
			return object;
		} catch (final JMSException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void create(final DatastoreTriple triple) {
		if (triple == null) {
			return;
		}

		if (shouldCreate(triple)) {
			System.out.println("Criando " + triple);
			datastoreRepository.create(triple);
			System.out.println("Tripla criada com sucesso");
		} else {
			System.out.println("Tripla ja existe " + triple);
		}
	}

	private boolean shouldCreate(final DatastoreTriple triple) {
		if (triple == null) {
			return false;
		}

		final DatastoreTriple tripleFilter = triple.clone();
		tripleFilter.setObject(null);
		final List<DatastoreTriple> filterResults = datastoreRepository.find(tripleFilter);

		if (filterResults.size() == 1) {
			if (filterResults.get(0).equals(triple)) {
				return false;
			}
		}

		for (final DatastoreTriple datastoreTriple : filterResults) {
			datastoreRepository.remove(datastoreTriple);
		}

		return true;
	}

}
