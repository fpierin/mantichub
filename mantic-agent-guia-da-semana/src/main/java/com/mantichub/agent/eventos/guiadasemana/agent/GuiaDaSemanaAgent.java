package com.mantichub.agent.eventos.guiadasemana.agent;

import static com.mantichub.agent.eventos.guiadasemana.config.Configuration.USE_PARALLELL_CALLS;
import static com.mantichub.core.util.ModelUtils.getRDFXMLFastWriter;
import static com.mantichub.core.util.StringUtils.isNotBlank;
import static java.text.MessageFormat.format;

import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.jena.ext.com.google.common.util.concurrent.ListeningExecutorService;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.mantic.datastore.client.api.DatastoreApi;
import org.mantic.datastore.client.api.DatastoreApiImpl;

import com.google.inject.Inject;
import com.mantichub.agent.core.http.HttpAgent;
import com.mantichub.agent.core.http.HttpAgentImpl;
import com.mantichub.agent.core.infra.Agent;
import com.mantichub.agent.core.infra.DefaultAgent;
import com.mantichub.agent.core.infra.ResourceCreator;
import com.mantichub.commons.resource.Event;
import com.mantichub.core.http.HttpClientFactory;
import com.mantichub.core.serialization.JsonSerializationServiceImpl;

public class GuiaDaSemanaAgent extends DefaultAgent implements Agent {

	public static final String GUIA_SEMANA_URL = "http://www.guiadasemana.com.br";
	public static final String GUIA_SEMANA_PAGINACAO = GUIA_SEMANA_URL + "/{0}/agenda?palavra=&tipo=&canal=&data=&page={1}";
	public static final String OBJETO_URL = "href=\"(/sao-paulo/[^/]+/evento/[^\"]+)";

	@Inject
	public GuiaDaSemanaAgent(final HttpAgent httpAgent, final DatastoreApi datastoreApi,
			final ListeningExecutorService service) {
		super(httpAgent, datastoreApi, service);
	}
	
	public static void main(final String[] args) throws Exception {
		final Model model = getRDFXMLFastWriter();
		final JsonSerializationServiceImpl serializationService = new JsonSerializationServiceImpl();
		final HttpClient httpClient = HttpClientFactory.get(10, 10, 1);
		final DatastoreApiImpl datastoreApi = new DatastoreApiImpl(httpClient, serializationService);
		final HttpAgent httpAgent = new HttpAgentImpl(httpClient, serializationService);
		final String url = "https://www.guiadasemana.com.br/sao-paulo/agenda?canal=teatro";
		System.out.println("Buscando dados da url " + url);
		final String html = httpAgent.htmlFromURL(url);
		final GuiaDaSemanaAdapter guiaDaSemanaAdapter = new GuiaDaSemanaAdapter(url, html);
		final List<String> resources = guiaDaSemanaAdapter.getResources();
		for (final String string : resources) {
			final Event event = new GuiaDaSemanaResourceAdapter(string);
			if (isNotBlank(event.getTitle())) {
				datastoreApi.create(ResourceCreator.build(model, event));
			}
		}
		
	}	

	@Override
	public Model retrieve(final int ammount) throws Exception {
		final Model model = getModel();
		recupera(model, "aracaju");
		recupera(model, "belem");
		recupera(model, "belo-horizonte");
		recupera(model, "blumenau");
		recupera(model, "brasilia");
		recupera(model, "campinas");
		recupera(model, "campo-grande");
		recupera(model, "caxias-do-sul");
		recupera(model, "cuiaba");
		recupera(model, "curitiba");
		recupera(model, "florianopolis");
		recupera(model, "fortaleza");
		recupera(model, "goiania");
		recupera(model, "joao-pessoa");
		recupera(model, "joinville");
		recupera(model, "litoral-paulista");
		recupera(model, "londrina");
		recupera(model, "maceio");
		recupera(model, "manaus");
		recupera(model, "maringa");
		recupera(model, "natal");
		recupera(model, "porto-alegre");
		recupera(model, "recife");
		recupera(model, "ribeirao-preto");
		recupera(model, "rio-de-janeiro");
		recupera(model, "salvador");
		recupera(model, "sao-luis");
		recupera(model, "sao-paulo");
		recupera(model, "vitoria");
		return model;
	}

	private void recupera(final Model model, final String cidade) throws Exception {
		for (int i = 0; i < 12; i++) {
			final String url = format(GUIA_SEMANA_PAGINACAO, cidade, i);
			System.out.println("Buscando dados da url " + url);
			final String html = httpAgent.htmlFromURL(url);
			final GuiaDaSemanaAdapter guiaDaSemanaAdapter = new GuiaDaSemanaAdapter(url, html);
			final List<String> resources = guiaDaSemanaAdapter.getResources();
			for (final String string : resources) {
				final Event event = new GuiaDaSemanaResourceAdapter(string);
				if (isNotBlank(event.getTitle())) {
					datastoreApi.create(ResourceCreator.build(model, event));
				}
			}
		}
	}

	@Override
	public Resource fromHtml(final Model model, final String url, final String html) {
		return null;
	}

	@Override
	protected boolean useParallelCalls() {
		return USE_PARALLELL_CALLS;
	}

}


