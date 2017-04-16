package com.mantichub.agent.eventos.usp.agent;

import static com.mantichub.agent.eventos.usp.infra.TestUtils.fromFile;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.jena.rdf.model.Resource;
import org.junit.Before;
import org.junit.Test;

import com.mantichub.agent.core.infra.Event;
import com.mantichub.agent.eventos.usp.agent.EventoUspEventAdapter;
import com.mantichub.core.vocabulary.SCHEMA;

public class UspTalksFazReflexoesSobreOAborto {

	private final static String arquivo = "src/test/resources/uspTalksFazReflexoesSobreOAborto.html";
	private Event crawler;
	
	@Before
	public void prepara() throws Exception {
		crawler = new EventoUspEventAdapter(null, fromFile(arquivo));
	}	

	@Test
	public void verificaPrice() throws Exception {
		final Double valorEsperado = 0.0;
		final Double valorEncontrado = crawler.getPrice();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaOverview() throws Exception {
		final String valorEsperado = "A legalização do aborto é um dos assuntos que mais divide opiniões ao redor do mundo e o tema do próximo USP Talks, que acontece no dia 26 de abril. No Brasil, a interrupção da gravidez é permitida apenas em casos de estupro, anencefalia do feto, ou quando a gestação coloca em risco a vida da mãe. Sabe-se, porém, que milhares de mulheres realizam abortos ilegalmente no país todos os anos, com ou sem a ajuda de médicos, e há um debate permanente na sociedade — assim como no Congresso e no Judiciário — sobre a descriminalização da prática. Em uma decisão recente, a primeira turma do Superior Tribunal Federal (STF) decidiu que o aborto não representa crime quando realizado nos primeiros três meses de gestação; e uma ação protocolada no mês passado, no mesmo tribunal, pede que o abortamento seja descriminalizado em definitivo para esse período, seja qual for a motivação para ele. Projetos de lei que tramitam no Congresso, por outro lado, propõem regras ainda mais restritivas, incluindo a criminalização total da prática. O que fazer? Quem fala sobre isso é a médica sanitarista Ana Maria Costa, diretora-executiva do Centro Brasileiro de Estudos da Saúde (Cebes), e o advogado Leonardo Massud, professor de direito penal da Faculdade de Direito da Pontifícia Universidade Católica de São Paulo (PUC-SP). A realização é da Pró-Reitoria de Pesquisa da USP e Estadão, com apoio da Faculdade Cásper Líbero. O debate acontece das 18h30 às 19h30, no Edifício Gazeta da Faculdade Casper Líbero. Haverá transmissão ao vivo pelo IPTV USP. Mais informações neste link.";
		final String valorEncontrado = crawler.getOverview();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaDataInicio() throws Exception {
		final Date valorEsperado = new SimpleDateFormat("dd/MM/yy").parse("26/04/2017");
		final Date valorEncontrado = crawler.getStartDate();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaDataFim() throws Exception {
		final Date valorEsperado = new SimpleDateFormat("dd/MM/yy").parse("26/04/17");
		final Date valorEncontrado = crawler.getEndDate();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaHorarioInicio() throws Exception {
		final Date valorEsperado = new SimpleDateFormat("HH:mm").parse("18:30");
		final Date valorEncontrado = crawler.getStartTime();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaHorarioFim() throws Exception {
		final Date valorEsperado = new SimpleDateFormat("HH:mm").parse("19:30");
		final Date valorEncontrado = crawler.getEndTime();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaLatitude() throws Exception {
		final Double valorEsperado = -23.563368;
		final Double valorEncontrado = crawler.getLatitude();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaLongitude() throws Exception {
		final Double valorEsperado = -46.654092;
		final Double valorEncontrado = crawler.getLongitude();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaTitulo() throws Exception {
		final String valorEsperado = "USP Talks faz Reflexões sobre o Aborto";
		final String valorEncontrado = crawler.getTitle();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaEndereco() throws Exception {
		final String valorEsperado = "Avenida Paulista, 900";
		final String valorEncontrado = crawler.getStreetAddress();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaTipoEvento() throws Exception {
		final Resource valorEsperado = SCHEMA.ExhibitionEvent;
		final Resource valorEncontrado = crawler.getType();
		assertThat(valorEncontrado, is(valorEsperado));
	}

}
