package com.mantichub.agent.eventos.guiadasemana.agent;

import static com.mantichub.agent.eventos.guiadasemana.config.Configuration.BASE_URL;
import static com.mantichub.agent.eventos.guiadasemana.config.Configuration.EVENT_URL_PATTERN;
import static com.mantichub.agent.eventos.guiadasemana.config.Configuration.NEXT_PAGE_URL;
import static com.mantichub.agent.eventos.guiadasemana.config.Configuration.PORTAL_URL;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.mantic.datastore.client.api.DatastoreApi;

import com.google.inject.Inject;
import com.mantichub.agent.core.http.HttpAgent;
import com.mantichub.agent.core.infra.Agent;
import com.mantichub.agent.core.infra.DefaultAgent;
import com.mantichub.agent.core.infra.EventResource;

public class GuiaDaSemanaAgent extends DefaultAgent implements Agent {
	
	private static final Set<String> ignoreUrls = new HashSet<>(); 
	
	@Inject
	public GuiaDaSemanaAgent(final HttpAgent httpAgent, final DatastoreApi datastoreApi) {
		super(httpAgent, datastoreApi);
	}
	
	@Override
	public Model retrieve(final int ammount) throws Exception {
		ignoreUrls.add(PORTAL_URL);
		return retrieveFromUrl(ammount, PORTAL_URL, EVENT_URL_PATTERN);
	}
	
	@Override
	protected Model retrieveFromUrls(final int ammount, final Model model, final Set<String> urls) throws Exception {
		final Set<String> baseUrls = new HashSet<>();
		final Pattern pattern = Pattern.compile(NEXT_PAGE_URL);
		for (final String url : urls) {
			final Matcher matcher = pattern.matcher(url);
			if (matcher.find()) {
				final String nextPageUrl = BASE_URL + matcher.group(1);
				if (!ignoreUrls.contains(nextPageUrl)) {
					ignoreUrls.add(nextPageUrl);
					System.out.println(nextPageUrl);
					retrieveFromUrl(ammount, nextPageUrl, EVENT_URL_PATTERN, model);
				}
			} else {
				baseUrls.add(BASE_URL + url);
			}
		}
		return super.retrieveFromUrls(ammount, model, baseUrls);
	}
	
	@Override
	public EventResource getAdapter(final String html) {
		return new GuiaDaSemanaEventAdapter(html);
	}
	
	@Override
	public Resource resourceFromHtml(final String url, final Model model, final String html) {
		System.out.println(url);
		return null;
	}
	
}
