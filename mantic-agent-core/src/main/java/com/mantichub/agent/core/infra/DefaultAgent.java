package com.mantichub.agent.core.infra;

import static com.mantichub.agent.core.constant.AgentConfiguration.REQUEST_TIMEOUT;
import static com.mantichub.agent.core.constant.AgentConfiguration.THREAD_AMOUNT;
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

import org.apache.jena.ext.com.google.common.util.concurrent.Futures;
import org.apache.jena.ext.com.google.common.util.concurrent.ListenableFuture;
import org.apache.jena.ext.com.google.common.util.concurrent.ListeningExecutorService;
import org.apache.jena.ext.com.google.common.util.concurrent.MoreExecutors;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.mantic.datastore.client.api.DatastoreApi;

import com.mantichub.agent.core.http.HttpAgent;

public abstract class DefaultAgent implements Agent {
	
	protected final HttpAgent httpAgent;
	protected final DatastoreApi datastoreApi;

	protected static final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_AMOUNT);
	protected static final ListeningExecutorService service = MoreExecutors.listeningDecorator(executorService);
	
	public DefaultAgent(final HttpAgent httpAgent, final DatastoreApi datastoreApi) {
		this.httpAgent = httpAgent;
		this.datastoreApi = datastoreApi;
	}
	
	@Override
	public Model retrieve() throws Exception {
		return retrieve(0);
	}
	
	@Override
	public Resource resourceFromHtml(final String url, final Model model, final String html) {
		return fromHtml(model, url, html);
	}
	
	protected Model retrieveFromUrl(final int ammount, final String portalUrl, final String eventUrlPattern)
			throws Exception {
		final Model model = getRDFXMLFastWriter();
		return retrieveFromUrl(ammount, portalUrl, eventUrlPattern, model);
	}
	
	protected Model retrieveFromUrl(final int ammount, final String portalUrl, final String eventUrlPattern,
			final Model model) throws Exception {
		final Set<String> urls = objectUrls(portalUrl, eventUrlPattern);
		return retrieveFromUrls(ammount, model, urls);
	}

	protected Set<String> objectUrls(final String portalUrl, final String eventUrlPattern) {
		final String htmlFromURL = httpAgent.htmlFromURL(portalUrl);
		final Set<String> urls = setByPattern(htmlFromURL, eventUrlPattern);
		return urls;
	}
	
	protected Model retrieveFromUrls(final int ammount, final Model model, final Set<String> urls) throws Exception {
		System.out.println(MessageFormat.format("Foram encontrados {0} eventos", urls.size()));
		final List<ListenableFuture<Resource>> resources = createCallables(model, urls, ammount);
		final ListenableFuture<List<Resource>> successfulResources = Futures.successfulAsList(resources);
		final List<Resource> list = successfulResources.get(REQUEST_TIMEOUT, MILLISECONDS);
		System.out.println("Foram recuperados " + list.size() + " objetos");
		return model;
	}
	
	protected Resource resourceFromURI(final String url, final Model model) {
		System.out.println("Buscando recursos da URI " + url);
		final String html = httpAgent.unescapeHtmlFromURL(url);
		return resourceFromHtml(url, model, html);
	}
	
	protected List<ListenableFuture<Resource>> createCallables(final Model model, final Set<String> urls,
			final int limit) {
		int amount = 0;
		final Iterator<String> iterator = urls.iterator();
		final List<ListenableFuture<Resource>> resources = new ArrayList<>();
		while (iterator.hasNext() && ((limit == 0) || (amount < limit))) {
			amount++;
			final ListenableFuture<Resource> lf = service.submit(new Callable<Resource>() {
				@Override
				public Resource call() throws Exception {
					final Resource resource = resourceFromURI(iterator.next(), model);
					datastoreApi.create(resource);
					return resource;
				}
			});
			resources.add(lf);
		}
		return resources;
	}
	
	public abstract Resource fromHtml(Model model, String url, String html);
	
}
