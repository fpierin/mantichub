package com.mantichub.agent.guiafolha.agent;

import static com.mantichub.core.util.HTMLUtils.setByPattern;
import static java.text.MessageFormat.format;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

import org.apache.jena.ext.com.google.common.util.concurrent.ListeningExecutorService;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.mantic.datastore.client.api.DatastoreApi;

import com.google.inject.Inject;
import com.mantichub.agent.core.http.HttpAgent;
import com.mantichub.agent.core.infra.Agent;
import com.mantichub.agent.core.infra.DefaultAgent;
import com.mantichub.agent.core.infra.ResourceCreator;
import com.mantichub.agent.guiafolha.config.Configuration;
import com.mantichub.commons.resource.Event;
import com.mantichub.commons.resource.ResourceInterface;
import com.mantichub.commons.resource.Resources;
import com.mantichub.commons.resource.FoodEstablishment;

public class GuiaDaFolhaAgent extends DefaultAgent implements Agent {

	public static final String GUIA_FOLHA_URL = "http://guia.folha.uol.com.br";
	public static final String PAGINACAO = "/busca/{0}/?page={1}";
	public static final String GUIA_FOLHA_PAGINACAO = GUIA_FOLHA_URL + PAGINACAO;
	public static final String OBJETO_URL = "href=\"(/[^/]+/[^/]+/[^\\.]+.shtml)";
	public static final Set<String> urlsAntigas = new HashSet<>();

	@Inject
	public GuiaDaFolhaAgent(final HttpAgent httpAgent, final DatastoreApi datastoreApi,
			final ListeningExecutorService service) {
		super(httpAgent, datastoreApi, service);
	}

	@Override
	public Model retrieve(final int ammount) throws Exception {
		final Model m = getModel();
		extrair(m, "restaurantes", 1);
		extrair(m, "bares", 1);
		extrair(m, "shows", 1);
		extrair(m, "teatro", 1);
		extrair(m, "cinema", 1);
		extrair(m, "exposicoes", 1);
		extrair(m, "danca", 1);
		return m;
	}

	private Model extrair(Model m, final String objeto, final int index) throws Exception {
		final String galleryUrl = format(GUIA_FOLHA_PAGINACAO, objeto, index);
		final String objectUrlPattern = format(OBJETO_URL, objeto);
		final String galleryHtml = httpAgent.htmlFromURL(galleryUrl);
		final Set<String> urlsDeObjetos = setByPattern(galleryHtml, objectUrlPattern);

		final Set<String> novasUrls = filtra(urlsDeObjetos);
		if (!novasUrls.isEmpty()) {
			m = retrieveFromUrls(0, m, novasUrls);
		}
		
		if (galleryHtml.contains(format("class=\"pagination__page\">{0}</a>", index +1))) {
			return extrair(m, objeto, index + 1);
		}
		System.out.println(galleryHtml);
		return m;
	}

	private Set<String> filtra(final Set<String> objectUrls) {
		final Set<String> urls = new HashSet<>();
		objectUrls.forEach(url -> {
			final String fullUrl = GUIA_FOLHA_URL + url;
			if (!urlsAntigas.contains(fullUrl)) {
				urls.add(fullUrl);
			}
			urlsAntigas.add(fullUrl);
		});
		return urls;
	}

	@Override
	public Resource fromHtml(final Model model, final String url, final String html) {
		try {
			final ResourceInterface ri = new GuiaDaFolhaRestaurantAdapter(url, html);
			final Resources type = ri.getType();
			if (Resources.Restaurant.equals(type)) {
				return ResourceCreator.build(model, (FoodEstablishment) ri);
			}
			return ResourceCreator.build(model, (Event) ri);
		} catch (final Exception e) {
			System.out.println("Falha ao recuperar dados do dominio: " + url);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	protected boolean useParallelCalls() {
		return Configuration.USE_PARALLELL_CALLS;
	}

}