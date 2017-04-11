package com.mantichub.agent.guiafolha.agent;

import static java.text.MessageFormat.format;

import java.util.Set;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.mantic.datastore.client.api.DatastoreApi;

import com.google.inject.Inject;
import com.mantichub.agent.core.http.HttpAgent;
import com.mantichub.agent.core.infra.Agent;
import com.mantichub.agent.core.infra.DefaultAgent;
import com.mantichub.agent.core.infra.ResourceCreator;

public class GuiaDaFolhaAgent extends DefaultAgent implements Agent {
	
	public static final String GUIA_FOLHA_URL = "http://guia.folha.uol.com.br";
	public static final String GUIA_FOLHA_PAGINACAO = GUIA_FOLHA_URL + "/busca/{0}/?page={1}";
	public static final String OBJETO_URL = "(" + GUIA_FOLHA_URL + "/{0}/[^/]*/[^\\.]*.shtml)";
	
	@Inject
	public GuiaDaFolhaAgent(final HttpAgent httpAgent, final DatastoreApi datastoreApi) {
		super(httpAgent, datastoreApi);
	}
	
	@Override
	public Model retrieve(final int ammount) throws Exception {
		Model m = null;
		boolean hasNext = true;
		int i = 1;
		do {
			final String objeto = "restaurantes";
			final String galleryUrl = format(GUIA_FOLHA_PAGINACAO, objeto, i);
			final String objectUrlPattern = format(OBJETO_URL, objeto);
			if (m == null) {
				m = retrieveFromUrl(ammount, galleryUrl, objectUrlPattern);
				i++;
			} else {
				final Set<String> objectUrls = objectUrls(galleryUrl, objectUrlPattern);
				m = retrieveFromUrls(ammount, m, objectUrls);
				hasNext = !objectUrls.isEmpty();
			}
		} while (hasNext);
		
		return m;
	}

	@Override
	public Resource fromHtml(final Model model, final String url, final String html) {
		try {
			return ResourceCreator.build(model, new GuiaDaFolhaRestaurantAdapter(url, html));
		} catch (final Exception e) {
			System.out.println("Falha ao recuperar dados do dominio: " + url);
			e.printStackTrace();
			return null;
		}
	}

}
