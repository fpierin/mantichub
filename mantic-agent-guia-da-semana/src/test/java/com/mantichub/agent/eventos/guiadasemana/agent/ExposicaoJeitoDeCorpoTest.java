package com.mantichub.agent.eventos.guiadasemana.agent;

import static com.mantichub.agent.eventos.guiadasemana.infra.TestUtils.fromFile;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import com.mantichub.commons.resource.Event;
import com.mantichub.commons.resource.Resources;

public class ExposicaoJeitoDeCorpoTest {
	
	private final static String arquivo = "src/test/resources/exposicaoJeitoDeCorpo.html";
	private Event crawler;
	
	@Before
	public void prepara() throws Exception {
		crawler = new GuiaDaSemanaEventAdapter(null, fromFile(arquivo));
	}
	
	@Test
	public void verificaTitulo() throws Exception {
		final String valorEsperado = "Jeito de Corpo";
		final String valorEncontrado = crawler.getTitle();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaEndereco() throws Exception {
		final String valorEsperado = "Rua Francisco Leitão, 198";
		final String valorEncontrado = crawler.getStreetAddress();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaLatitude() throws Exception {
		final Double valorEsperado = -23.562814;
		final Double valorEncontrado = crawler.getLatitude();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaLongitude() throws Exception {
		final Double valorEsperado = -46.680045;
		final Double valorEncontrado = crawler.getLongitude();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaTipoEvento() throws Exception {
		final Resources valorEsperado = Resources.ExhibitionEvent;
		final Resources valorEncontrado = crawler.getType();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaPrice() throws Exception {
		final Double valorEsperado = 0.00;
		final Double valorEncontrado = crawler.getPrice();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaDataInicio() throws Exception {
		final Date valorEsperado = new SimpleDateFormat("dd/MM/yy").parse("12/04/17");
		final Date valorEncontrado = crawler.getStartDate();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaDataFim() throws Exception {
		final Date valorEsperado = new SimpleDateFormat("dd/MM/yy").parse("29/04/17");
		final Date valorEncontrado = crawler.getEndDate();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaOverview() throws Exception {
		final String valorEsperado = "O Espaço Paulista de Arte apresenta, entre os dias 12 e 29 de abril, a exposição Jeito de Corpo, dos fotógrafos baianos Caroline Lima e Thiago Borba. Com entrada gratuita, a visitação acontece de segunda a sexta, das 10h às 18h, e aos sábados, das 10h às 17h.     Programação Grátis em São Paulo    A mostra, que nasce da observação da maneira de mover-se dos baianos, sobretudo do corpo negro, é composta por 14 fotografias que desenham os movimentos do corpo sobre o cotidiano, representando o modo de vida do ensolarado e cheio de ritmo estado brasileiro.";
		final String valorEncontrado = crawler.getOverview();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	public static void main(final String[] args) throws Exception {
		final DateFormat df = new SimpleDateFormat("EEE dd MMM", new Locale("pt", "BR"));

		System.out.println(df.format(new Date()));

	}
	
}
