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

public class DecadaDeArrombaMusicalTest {
	
	private final static String arquivo = "src/test/resources/60decadadearromba.html";
	private Event crawler;
	
	@Before
	public void prepara() throws Exception {
		crawler = new GuiaDaSemanaEventAdapter(null, fromFile(arquivo));
	}
	
	@Test
	public void verificaTitulo() throws Exception {
		final String valorEsperado = "60! Década de Arromba – Doc. Musical";
		final String valorEncontrado = crawler.getTitle();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaEndereco() throws Exception {
		final String valorEsperado = "Rua Olimpíadas, 360 - 5º andar (Shopping Vila Olímpia)";
		final String valorEncontrado = crawler.getStreetAddress();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaLatitude() throws Exception {
		final Double valorEsperado = -23.6;
		final Double valorEncontrado = crawler.getLatitude();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaLongitude() throws Exception {
		final Double valorEsperado = -46.6833;
		final Double valorEncontrado = crawler.getLongitude();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaTipoEvento() throws Exception {
		final Resources valorEsperado = Resources.TheaterEvent;
		final Resources valorEncontrado = crawler.getType();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaPrice() throws Exception {
		final Double valorEsperado = 200.00;
		final Double valorEncontrado = crawler.getPrice();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaDataInicio() throws Exception {
		final Date valorEsperado = new SimpleDateFormat("dd/MM/yy").parse("13/04/17");
		final Date valorEncontrado = crawler.getStartDate();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaDataFim() throws Exception {
		final Date valorEsperado = new SimpleDateFormat("dd/MM/yy").parse("23/04/17");
		final Date valorEncontrado = crawler.getEndDate();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaOverview() throws Exception {
		final String valorEsperado = "60! Década de Arromba – Doc. Musical chega ao Theatro NET São Paulo no dia 13 de abril para uma temporada até o dia 23 do mesmo mês. A cantora Wanderléa, representante maior da Jovem Guarda, um dos principais movimentos musicais da década de 1960, participa do espetáculo, interpretando ela mesma. O musical começa com um prólogo, em 1922, contando a chegada do Rádio no Brasil, para em seguida mostrar o início da Televisão e aí sim, sua popularização na década de 1960. A partir desse ponto, a peça narra os principais acontecimentos, apresentando mais de cem canções dos mais diversos gêneros – de Roberto e Erasmo, passando por Dalva de Oliveira, Cauby Peixoto, Elvis Presley, Beatles, Tony e Celly Campello, Bibi Ferreira, Edith Piaf, Tom e Vinicius, Milton Nascimento, Gil e Caetano, Maysa, Geraldo Vandré e tantos outros nomes importantes na música.       Peças de teatro em cartaz em São Paulo em março de 2017    Confira os musicais que estreiam em São Paulo em 2017    7 teatros alternativos em SP que você precisa conhecer";
		final String valorEncontrado = crawler.getOverview();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	public static void main(final String[] args) throws Exception {
		final DateFormat df = new SimpleDateFormat("EEE dd MMM", new Locale("pt", "BR"));

		System.out.println(df.format(new Date()));

	}
	
}
