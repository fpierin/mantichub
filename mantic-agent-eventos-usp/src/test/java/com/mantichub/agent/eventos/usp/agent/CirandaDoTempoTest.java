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

public class CirandaDoTempoTest {

	private final static String arquivo = "src/test/resources/cirandaDoTempo.html";
	private Event crawler;
	
	@Before
	public void prepara() throws Exception {
		crawler = new EventoUspEventAdapter(null, fromFile(arquivo));
	}	

	@Test
	public void verificaPrice() throws Exception {
		final Double valorEsperado = null;
		final Double valorEncontrado = crawler.getPrice();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaOverview() throws Exception {
		final String valorEsperado = "Da Assessoria de Comunicação do PUSP-RP Estão abertas as inscrições para o Simpósio da Liga de Pediatria e Puericultura da Faculdade de Medicina de Ribeirão Preto (FMRP) da USP com o tema Ciranda do Tempo. Serão abordados assuntos como Os Primeiros 1000 dias; Do Nascimento à Puberdade: características e necessidades psicológicas; e Mudanças do Perfil de Moléstias na Infância. O evento conta com apresentação de trabalhos científicos, sociais e de inclusão desenvolvidos para e com crianças. Os professores Sonir Antonini e Heloísa Bettiol, ambos da FMRP, e Alexandre Archanjo Ferraro da Faculdade de Medicina da Universidade de São Paulo (FMUSP) estão entre os convidados.   Podem participar alunos da graduação, residentes, pós-graduandos e profissionais da área da saúde. As inscrições custam entre R$ 15,00 e R$ 50,00 e devem ser feitas online. As atividades acontecem nos dias 4 e 5 de maio, no Espaço de Eventos do Bloco Didático da FMRP. Mais informações neste link.";
		final String valorEncontrado = crawler.getOverview();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaDataInicio() throws Exception {
		final Date valorEsperado = new SimpleDateFormat("dd/MM/yy").parse("04/05/2017");
		final Date valorEncontrado = crawler.getStartDate();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaDataFim() throws Exception {
		final Date valorEsperado = new SimpleDateFormat("dd/MM/yy").parse("05/05/17");
		final Date valorEncontrado = crawler.getEndDate();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaHorarioInicio() throws Exception {
		final Date valorEsperado = new SimpleDateFormat("HH:mm").parse("09:00");
		final Date valorEncontrado = crawler.getStartTime();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaHorarioFim() throws Exception {
		final Date valorEsperado = new SimpleDateFormat("HH:mm").parse("18:00");
		final Date valorEncontrado = crawler.getEndTime();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaLatitude() throws Exception {
		final Double valorEsperado = -21.171446;
		final Double valorEncontrado = crawler.getLatitude();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaLongitude() throws Exception {
		final Double valorEsperado = -47.860565;
		final Double valorEncontrado = crawler.getLongitude();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaTitulo() throws Exception {
		final String valorEsperado = "Ciranda do Tempo é tema de simpósio sobre pediatria";
		final String valorEncontrado = crawler.getTitle();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaEndereco() throws Exception {
		final String valorEsperado = "Avenida Bandeirantes, 3900";
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
