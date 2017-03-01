package com.mantichub.agent.eventos.usp.agent;

import static com.mantichub.agent.eventos.usp.infra.TestUtils.fromFile;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.mantichub.agent.core.infra.EventResource;

public class EventoUSPEventCrawlerExemplo3 {

	private final static String arquivo = "src/test/resources/exemplo3.html";

	@Test
	public void verificaPrice() throws Exception {
		final EventResource crawler = new EventoUspEventAdapter(fromFile(arquivo));
		final Double valorEsperado = 0.00;
		final Double valorEncontrado = crawler.getPrice();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaEndereco() throws Exception {
		final EventResource crawler = new EventoUspEventAdapter(fromFile(arquivo));
		final String valorEsperado = "Rua Major Diogo, 353";
		final String valorEncontrado = crawler.getStreetAddress();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaDataInicio() throws Exception {
		// final String fromFile = fromFile(arquivo);
		// System.out.println(fromFile);
		final String fromFile = "  <tr>\n"
				+ "    <td width=\"130\" align=\"right\" class=\"desc-align\">Data<img src=\"http://www.eventos.usp.br/wp-content/themes/eventos2011b/images/evento-data-ico.png\" class=\"img-align\" alt=\"data\" /></td>\n"
				+ "    <td>07/02/17 | 20:00 - 21:00<br /></td>\n" + "  </tr>";
		// // final String regex = ;
		final EventResource crawler = new EventoUspEventAdapter(fromFile);
		EventoUspEventAdapter.START_DATE_PATTERN = "<td>(\\d{2}/\\d{2}/\\d{2})( - \\d{2}/\\d{2}/\\d{2})? \\| \\d{2}:\\d{2} - \\d{2}:\\d{2}<br>";
		final Date valorEsperado = new SimpleDateFormat("dd/MM/yy").parse("07/02/17");
		final Date valorEncontrado = crawler.getStartDate();
		assertThat(valorEncontrado, is(valorEsperado));

		// final String valueByPattern = HTMLUtils.valueByPattern(fromFile, "<td>(\\d{2}/\\d{2}/\\d{2})( -
		// \\d{2}/\\d{2}/\\d{2})? \\| \\d{2}:\\d{2} - \\d{2}:\\d{2}<br");
		// System.out.println(valueByPattern);
	}

	// @Test
	// public void verificaOverview() throws Exception {
	// final EventCrawler crawler = new EventoUSPEventCrawler(fromFile(arquivo));
	// final String valorEsperado = "A curadora Katia Canton, vice-diretora do Museu de Arte Contemporânea (MAC) da USP,
	// se inspira no poema de Vinícius de Moraes para a exposição A Casa que será inaugurada no dia 12 de setembro, a
	// partir das 11 horas. \n"
	// + "A Casa apresenta 18 obras que pertencem ao acervo do MAC, dos artistas Alexander Calder, Leda Catunda, Alex
	// Flemming, Iran do Espírito Santo, Cildo Meireles, Nina Moraes, Alex Vallauri, Flávio Cerqueira, Barrão, Regina
	// Silveira, Maria Tomaselli, Camille Kachani, Ângelo Venosa, José Carratu e Ana Teixeira. Distribuídas não por
	// ordem cronológica ou autoral, mas pela representação dos papéis que cada uma cumpriria em sua função de
	// domesticidade, as obras permitem pensar possibilidades, por exemplo, caso o sofá de Regina Silveira fosse feito
	// para sentar, ou a vitrola de Iran do Espírito Santo fosse pensada para tocar discos. Ou ainda as escumadeiras
	// espetadas no cordeiro, de Alex Flemming, fossem feitas para fritar, utilizando para isso o fogão de Alex
	// Vallauri.\n"
	// + "A exposição integra a pesquisa Temas da Arte Contemporânea, em que Katia discute como as novas propostas de
	// apreciação e aprendizado da arte podem ser realizadas sob uma variedade de recortes, com temporalidades, temas e
	// enunciados diferentes e muitas vezes justapostos.\n"
	// + "O MAC USP Ibirapuera fica localizado na Avenida Pedro Álvares Cabral, 1301 em São Paulo. A exposição será
	// exibida de terça a domingo das 10 às 18 horas. Entrada gratuita.";
	// final String valorEncontrado = crawler.getOverview();
	// assertThat(valorEncontrado, is(valorEsperado));
	// }
	//

	//
	// @Test
	// public void verificaDataFim() throws Exception {
	// final EventCrawler crawler = new EventoUSPEventCrawler(fromFile(arquivo));
	// final Date valorEsperado = new SimpleDateFormat("dd/MM/yy").parse("31/07/16");
	// final Date valorEncontrado = crawler.getEndDate();
	// assertThat(valorEncontrado, is(valorEsperado));
	// }
	//
	// @Test
	// public void verificaHorarioInicio() throws Exception {
	// final EventCrawler crawler = new EventoUSPEventCrawler(fromFile(arquivo));
	// final Date valorEsperado = new SimpleDateFormat("HH:mm").parse("11:00");
	// final Date valorEncontrado = crawler.getStartTime();
	// assertThat(valorEncontrado, is(valorEsperado));
	// }
	//
	// @Test
	// public void verificaHorarioFim() throws Exception {
	// final EventCrawler crawler = new EventoUSPEventCrawler(fromFile(arquivo));
	// final Date valorEsperado = new SimpleDateFormat("HH:mm").parse("18:00");
	// final Date valorEncontrado = crawler.getEndTime();
	// assertThat(valorEncontrado, is(valorEsperado));
	// }
	//
	// @Test
	// public void verificaLatitude() throws Exception {
	// final EventCrawler crawler = new EventoUSPEventCrawler(fromFile(arquivo));
	// final Double valorEsperado = -23.587282;
	// final Double valorEncontrado = crawler.getLatitude();
	// assertThat(valorEncontrado, is(valorEsperado));
	// }
	//
	// @Test
	// public void verificaLongitude() throws Exception {
	// final EventCrawler crawler = new EventoUSPEventCrawler(fromFile(arquivo));
	// final Double valorEsperado = -46.652423;
	// final Double valorEncontrado = crawler.getLongitude();
	// assertThat(valorEncontrado, is(valorEsperado));
	// }
	//
	// @Test
	// public void verificaTitulo() throws Exception {
	// final EventCrawler crawler = new EventoUSPEventCrawler(fromFile(arquivo));
	// final String valorEsperado = "MAC recebe exposição “A Casa”";
	// final String valorEncontrado = crawler.getTitle();
	// assertThat(valorEncontrado, is(valorEsperado));
	// }
	//
	//
	// @Test
	// public void verificaTipoEvento() throws Exception {
	// final EventCrawler crawler = new EventoUSPEventCrawler(fromFile(arquivo));
	// final Resource valorEsperado = SCHEMA.ExhibitionEvent;
	// final Resource valorEncontrado = crawler.getType();
	// assertThat(valorEncontrado, is(valorEsperado));
	// }

}
