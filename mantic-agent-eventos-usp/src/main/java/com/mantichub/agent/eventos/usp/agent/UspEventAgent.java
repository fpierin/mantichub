package com.mantichub.agent.eventos.usp.agent;

import static com.mantichub.agent.eventos.usp.config.Configuration.EVENT_URL_PATTERN;
import static com.mantichub.agent.eventos.usp.config.Configuration.PORTAL_URL;
import static com.mantichub.agent.eventos.usp.config.Configuration.REQUEST_TIMEOUT;
import static com.mantichub.agent.eventos.usp.config.Configuration.THREAD_AMOUNT;
import static com.mantichub.core.util.HTMLUtils.setByPattern;
import static com.mantichub.core.util.ModelUtils.getRDFXMLFastWriter;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.jena.ext.com.google.common.util.concurrent.Futures;
import org.apache.jena.ext.com.google.common.util.concurrent.ListenableFuture;
import org.apache.jena.ext.com.google.common.util.concurrent.ListeningExecutorService;
import org.apache.jena.ext.com.google.common.util.concurrent.MoreExecutors;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

import com.google.inject.Inject;
import com.mantichub.agent.eventos.usp.config.Configuration;
import com.mantichub.agent.eventos.usp.http.EventosUspHttpClient;
import com.mantichub.core.agent.Agent;
import com.mantichub.core.agent.EventCrawler;
import com.mantichub.core.builder.EventBuilder;

public class UspEventAgent implements Agent {

	private final EventosUspHttpClient eventosUspHttpClient;

	private final static String projectNS = "http://www.wemantic.com/events#";

	@Inject
	public UspEventAgent(final EventosUspHttpClient eventosUspHttpClient) {
		this.eventosUspHttpClient = eventosUspHttpClient;
	}

	public Model retrieve() throws Exception {
		final Model model = getRDFXMLFastWriter();
		final String htmlFromURL = eventosUspHttpClient.htmlFromURL(PORTAL_URL);
		final Set<String> urls = setByPattern(htmlFromURL, EVENT_URL_PATTERN);
		System.out.println(MessageFormat.format("Foram encontrados {0} eventos", urls.size()));
		final List<ListenableFuture<Resource>> resources = createCallables(model, urls);
		final ListenableFuture<List<Resource>> successfulResources = Futures.successfulAsList(resources);
		successfulResources.get(REQUEST_TIMEOUT, MILLISECONDS);
		return model;
	}

	private List<ListenableFuture<Resource>> createCallables(final Model model, final Set<String> urls) {
		final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_AMOUNT);
		final ListeningExecutorService service = MoreExecutors.listeningDecorator(executorService);
		final Iterator<String> iterator = urls.iterator();
		final List<ListenableFuture<Resource>> resources = new ArrayList<ListenableFuture<Resource>>();
		final int i = 0;
		while (i < 100 & iterator.hasNext()) {
			service.submit(new Callable<Resource>() {
				public Resource call() throws Exception {
					return resourceFromURI(iterator.next(), model);
				}
			});
		}
		return resources;
	}

	private Resource resourceFromURI(final String url, final Model model) {
		final String html = eventosUspHttpClient.unescapeHtmlFromURL(url);
		final EventCrawler event = new EventoUSPEventCrawler(html);
		try {
			return new EventBuilder(model, projectNS)
					.price(event.getPrice())
					.endDate(event.getEndDate())
					.endTime(event.getEndTime())
					.latitude(event.getLatitude())
					.longitude(event.getLongitude())
					.overview(event.getOverview())
					.serviceUrl(url)
					.startDate(event.getStartDate())
					.startTime(event.getStartTime())
					.streetAddress(event.getStreetAddress())
					.type(event.getType())
					.title(event.getTitle())
					.create();
		} catch (final Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
