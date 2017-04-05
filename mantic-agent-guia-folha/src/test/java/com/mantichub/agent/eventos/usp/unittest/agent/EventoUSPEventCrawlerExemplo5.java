package com.mantichub.agent.eventos.usp.unittest.agent;

import static com.mantichub.agent.eventos.usp.infra.TestUtils.fromFile;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.jena.rdf.model.Resource;
import org.junit.Test;

import com.mantichub.agent.core.infra.EventResource;
import com.mantichub.agent.eventos.guiafolha.agent.EventoUspEventAdapter;
import com.mantichub.core.vocabulary.SCHEMA;

public class EventoUSPEventCrawlerExemplo5 {

	private final static String arquivo = "src/test/resources/exemplo5.html";

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
		final String valorEsperado = "Da Assessoria de Imprensa do Museu Paulista\nNo dia 27 de agosto, às 10 horas, às 10 horas, o Museu Republicano “Convenção de Itu” inaugura sua nova&nbsp;exposição, Maria Antonia: Uma vida, muitas histórias.\nMaria Antonia Luporini Sampaio nasceu em Itu aos 22 de agosto de 1916 e, aos 23 anos, a convite do Diretor do Museu Paulista, Affonso d’Escragnolle Taunay, passou a ocupar o cargo de conservadora do Museu Republicano “Convenção de Itu”, que estava vago por ocasião do falecimento de seu pai Arthur, permanecendo na instituição até 1975.\nA exposição em homenagem ao centenário de seu nascimento se espalha pelas salas do Museu Republicano, onde Maria Antonia trabalhou por 36 anos, cuidando do acervo, atendendo ao público, zelando pelo edifício e pela instituição.\nA exposição é de longa duração. O Museu Republicano está aberto à visitação&nbsp;de terça a domingo, das 10 às 17 horas.";
		final String valorEncontrado = crawler.getOverview();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaDataInicio() throws Exception {
		final EventResource crawler = new EventoUspEventAdapter(fromFile(arquivo));
		final Date valorEsperado = new SimpleDateFormat("dd/MM/yy").parse("29/08/17");
		final Date valorEncontrado = crawler.getStartDate();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaDataFim() throws Exception {
		final EventResource crawler = new EventoUspEventAdapter(fromFile(arquivo));
		final Date valorEsperado = new SimpleDateFormat("dd/MM/yy").parse("27/08/17");
		final Date valorEncontrado = crawler.getEndDate();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaHorarioInicio() throws Exception {
		final EventResource crawler = new EventoUspEventAdapter(fromFile(arquivo));
		final Date valorEsperado = new SimpleDateFormat("HH:mm").parse("10:00");
		final Date valorEncontrado = crawler.getStartTime();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaHorarioFim() throws Exception {
		final EventResource crawler = new EventoUspEventAdapter(fromFile(arquivo));
		final Date valorEsperado = new SimpleDateFormat("HH:mm").parse("17:00");
		final Date valorEncontrado = crawler.getEndTime();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaLatitude() throws Exception {
		final EventResource crawler = new EventoUspEventAdapter(fromFile(arquivo));
		final Double valorEsperado = -23.264057;
		final Double valorEncontrado = crawler.getLatitude();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaLongitude() throws Exception {
		final EventResource crawler = new EventoUspEventAdapter(fromFile(arquivo));
		final Double valorEsperado = null;
		final Double valorEncontrado = crawler.getLongitude();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaTitulo() throws Exception {
		final EventResource crawler = new EventoUspEventAdapter(fromFile(arquivo));
		final String valorEsperado = "Exposição Maria Antonia: uma vida, muitas histórias será inaugurada em Itu";
		final String valorEncontrado = crawler.getTitle();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaEndereco() throws Exception {
		final EventResource crawler = new EventoUspEventAdapter(fromFile(arquivo));
		final String valorEsperado = "Rua Barão do Itaim, 67";
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
