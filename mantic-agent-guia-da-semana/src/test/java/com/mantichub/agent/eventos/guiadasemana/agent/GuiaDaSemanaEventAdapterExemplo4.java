package com.mantichub.agent.eventos.guiadasemana.agent;

import static com.mantichub.agent.eventos.guiadasemana.infra.TestUtils.fromFile;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.jena.rdf.model.Resource;
import org.junit.Before;
import org.junit.Test;

import com.mantichub.agent.core.infra.EventResource;
import com.mantichub.core.vocabulary.SCHEMA;

public class GuiaDaSemanaEventAdapterExemplo4 {

	private EventResource crawler = null;
	private String html;

	@Before
	public void init() throws Exception {
		final String arquivo = "src/test/resources/evento4.html";
		html = fromFile(arquivo);
		crawler = new GuiaDaSemanaEventAdapter(html);
	}

	@Test
	public void verificaTitulo() throws Exception {
		final String valorEsperado = "Jantar às Cegas: Pratos Harmonizados com Cerveja";
		final String valorEncontrado = crawler.getTitle();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaEndereco() throws Exception {
		final String valorEsperado = "Avenida das Nações Unidas, 17955";
		final String valorEncontrado = crawler.getStreetAddress();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaLatitude() throws Exception {
		final Double valorEsperado = -23.56913;
		final Double valorEncontrado = crawler.getLatitude();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaLongitude() throws Exception {
		final Double valorEsperado = -46.705299;
		final Double valorEncontrado = crawler.getLongitude();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaTipoEvento() throws Exception {
		final Resource valorEsperado = SCHEMA.ExhibitionEvent;
		final Resource valorEncontrado = crawler.getType();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaPrice() throws Exception {
		final Double valorEsperado = 230.00;
		final Double valorEncontrado = crawler.getPrice();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaDataInicio() throws Exception {
		final Date valorEsperado = new SimpleDateFormat("dd/MM/yy").parse("21/06/17");
		final Date valorEncontrado = crawler.getStartDate();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaDataFim() throws Exception {
		final Date valorEsperado = new SimpleDateFormat("dd/MM/yy").parse("21/06/17");
		final Date valorEncontrado = crawler.getEndDate();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaOverview() throws Exception {
		final String valorEsperado = "O cantor Fábio Jr. se apresenta no dia 16 de setembro de 2017, sábado às 22h, no palco do Citibank Hall, em São Paulo.&nbsp;Os ingressos custam de R$ 80 a R$ 230 e podem ser comprados pelo site Tickets for Fun.\n    \n          \n         Shows da semana em São Paulo\n      \n          \n         Shows internacionais no Brasil em 2017\n      \n          \n         Programação Grátis em São Paulo\n      \n        \n  \n\nFábio Jr. chega com a turnê que iniciou no segundo semestre de 2014 e que já passou pela maior parte das grandes cidades brasileiras. “O Que Importa é a Gente Ser Feliz” é nome da música que batiza essa temporada de apresentações do artista. Também fazem parte do repertório grandes sucessos da carreira do artista, entre eles “Só Você”, “O Que Que Há”, “Alma Gêmea” e “Caça e Caçador”.\n\n        ";
		final String valorEncontrado = crawler.getOverview();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	public static void main(final String[] args) throws Exception {
		final DateFormat df = new SimpleDateFormat("EEE dd MMM", new Locale("pt", "BR"));
		
		System.out.println(df.format(new Date()));
		
	}

}
