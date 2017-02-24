package com.mantichub.agent.eventos.usp.unittest.agent;

import static com.mantichub.agent.eventos.usp.infra.TestUtils.fromFile;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.jena.rdf.model.Resource;
import org.junit.Test;

import com.mantichub.agent.core.infra.EventResource;
import com.mantichub.agent.eventos.usp.agent.EventoUspEventAdapter;
import com.mantichub.core.vocabulary.SCHEMA;

public class EventoUSPEventCrawlerExemplo1 {

	private final static String arquivo = "src/test/resources/exemplo2.html";

	@Test
	public void verificaPrice() throws Exception {
		final EventResource crawler = new EventoUspEventAdapter(fromFile(arquivo));
		final Double valorEsperado = 0.00;
		final Double valorEncontrado = crawler.getPrice();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaOverview() throws Exception {
		final EventResource crawler = new EventoUspEventAdapter(fromFile(arquivo));
		final String valorEsperado = "Entre os dias 1 de fevereiro e 27 de abril, a Biblioteca Brasiliana Guita e José Mindlin (BBM) abre para o público a exposição O Mundo ao Redor: o olhar de viajantes em relatos de volta ao mundo.\n"
				+ "Com registros de viagens de volta ao mundo realizadas entre os séculos 16 e 19, a exposição O Mundo ao Redor traz alguns dos relatos e gravuras do acervo da Biblioteca Mindlin para mostrar um pouco do olhar dos viajantes sobre as terras exploradas.\n"
				+ "A mostra é gratuita e pode ser visitada de segunda a sexta-feira das 8h30 às 18h30. Para mais informações, consulte a programação.&nbsp;A mostra ocorrerá na&nbsp;Sala Multiuso da BBM.";
		final String valorEncontrado = crawler.getOverview();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaDataInicio() throws Exception {
		final EventResource crawler = new EventoUspEventAdapter(fromFile(arquivo));
		final Date valorEsperado = new SimpleDateFormat("dd/MM/yy").parse("11/02/16");
		final Date valorEncontrado = crawler.getStartDate();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaDataFim() throws Exception {
		final EventResource crawler = new EventoUspEventAdapter(fromFile(arquivo));
		final Date valorEsperado = new SimpleDateFormat("dd/MM/yy").parse("27/04/16");
		final Date valorEncontrado = crawler.getEndDate();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaHorarioInicio() throws Exception {
		final EventResource crawler = new EventoUspEventAdapter(fromFile(arquivo));
		final Date valorEsperado = new SimpleDateFormat("HH:mm").parse("08:30");
		final Date valorEncontrado = crawler.getStartTime();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaHorarioFim() throws Exception {
		final EventResource crawler = new EventoUspEventAdapter(fromFile(arquivo));
		final Date valorEsperado = new SimpleDateFormat("HH:mm").parse("18:30");
		final Date valorEncontrado = crawler.getEndTime();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaLatitude() throws Exception {
		final EventResource crawler = new EventoUspEventAdapter(fromFile(arquivo));
		final Double valorEsperado = -23.560653;
		final Double valorEncontrado = crawler.getLatitude();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaLongitude() throws Exception {
		final EventResource crawler = new EventoUspEventAdapter(fromFile(arquivo));
		final Double valorEsperado = -46.722107;
		final Double valorEncontrado = crawler.getLongitude();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaTitulo() throws Exception {
		final EventResource crawler = new EventoUspEventAdapter(fromFile(arquivo));
		final String valorEsperado = "Biblioteca Brasiliana apresenta exposição “O Mundo ao Redor”";
		final String valorEncontrado = crawler.getTitle();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaEndereco() throws Exception {
		final EventResource crawler = new EventoUspEventAdapter(fromFile(arquivo));
		final String valorEsperado = "Rua da Biblioteca, s/n";
		final String valorEncontrado = crawler.getStreetAddress();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaTipoEvento() throws Exception {
		final EventResource crawler = new EventoUspEventAdapter(fromFile(arquivo));
		final Resource valorEsperado = SCHEMA.ExhibitionEvent;
		final Resource valorEncontrado = crawler.getType();
		assertThat(valorEncontrado, is(valorEsperado));
	}

}
