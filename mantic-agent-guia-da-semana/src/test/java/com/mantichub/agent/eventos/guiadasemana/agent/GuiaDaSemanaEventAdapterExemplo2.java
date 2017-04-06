package com.mantichub.agent.eventos.guiadasemana.agent;

import static com.mantichub.agent.eventos.guiadasemana.infra.TestUtils.fromFile;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.jena.rdf.model.Resource;
import org.junit.Before;
import org.junit.Test;

import com.mantichub.agent.core.infra.Event;
import com.mantichub.core.vocabulary.SCHEMA;

public class GuiaDaSemanaEventAdapterExemplo2 {
	
	private Event crawler = null;
	private String html;
	
	@Before
	public void init() throws Exception {
		final String arquivo = "src/test/resources/evento2.html";
		html = fromFile(arquivo);
		crawler = new GuiaDaSemanaEventAdapter(html, "");
	}
	
	@Test
	public void verificaTitulo() throws Exception {
		final String valorEsperado = "Ed Sheeran em São Paulo em 2017";
		final String valorEncontrado = crawler.getTitle();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaEndereco() throws Exception {
		final String valorEsperado = "Rua Turiassu, 1840";
		final String valorEncontrado = crawler.getStreetAddress();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaLatitude() throws Exception {
		final Double valorEsperado = -23.528283;
		final Double valorEncontrado = crawler.getLatitude();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaLongitude() throws Exception {
		final Double valorEsperado = -46.679596;
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
		final Double valorEsperado = 680.00;
		final Double valorEncontrado = crawler.getPrice();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaDataInicio() throws Exception {
		final Date valorEsperado = new SimpleDateFormat("dd/MM/yy").parse("28/05/17");
		final Date valorEncontrado = crawler.getStartDate();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaDataFim() throws Exception {
		final Date valorEsperado = new SimpleDateFormat("dd/MM/yy").parse("28/05/17");
		final Date valorEncontrado = crawler.getEndDate();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaOverview() throws Exception {
		final String valorEsperado = "O cantor britânico Ed Sheeran se apresenta em São Paulo no dia 28 de maio, domingo, às 20h, no Allianz Parque. Os ingressos custam de R$ 220 a R$ 680 e podem ser adquiridos pelo site Livepass.\n    \n          \n         Ed Sheeran no Brasil em 2017\n      \n          \n         Shows internacionais no Brasil em 2017\n      \n          \n         Enquete: qual é o show internacional mais aguardado de 2017?\n      \n        \n  \n\nA pré-venda Ourocard acontece&nbsp;de 10 de fevereiro (sexta-feira, às 00h01) a 12 de fevereiro (domingo, às 23h00). Já a venda para o público geral acontece no dia 13 de fevereiro (segunda-feira, às 00h01).\n\nEd Sheeran conta com&nbsp;mais de 22 milhões de álbuns vendidos no mundo inteiro, quase 5 milhões de&nbsp;downloads&nbsp;no Spotify e 3.5 bilhões de visualizações em seu canal do YouTube. Na turnê, o&nbsp;cantor e compositor britânico lança&nbsp;seu novo disco&nbsp;“÷”,&nbsp;o terceiro de sua carreira.&nbsp;Esta é a segunda passagem do astro pelo Brasil. Em abril de 2015, o cantor fez três apresentações em São Paulo e no Rio de Janeiro com ingressos esgotados.\n\n        ";
		final String valorEncontrado = crawler.getOverview();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	
}
