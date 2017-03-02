package com.mantichub.agent.eventos.guiadasemana.agent;

import static com.mantichub.agent.eventos.guiadasemana.infra.TestUtils.fromFile;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.jena.rdf.model.Resource;
import org.junit.Before;
import org.junit.Test;

import com.mantichub.agent.core.infra.EventResource;
import com.mantichub.core.vocabulary.SCHEMA;

public class GuiaDaSemanaEventAdapterExemplo1 {

	private EventResource crawler = null;
	private String html;

	@Before
	public void init() throws Exception {
		final String arquivo = "src/test/resources/evento1.html";
		html = fromFile(arquivo);
		crawler = new GuiaDaSemanaEventAdapter(html);
	}

	@Test
	public void verificaTitulo() throws Exception {
		final String valorEsperado = "Les Misérables";
		final String valorEncontrado = crawler.getTitle();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaEndereco() throws Exception {
		final String valorEsperado = "Avenida Brigadeiro Luís Antônio, 411";
		final String valorEncontrado = crawler.getStreetAddress();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaLatitude() throws Exception {
		final Double valorEsperado = -23.554232;
		final Double valorEncontrado = crawler.getLatitude();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaLongitude() throws Exception {
		final Double valorEsperado = -46.638588;
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
		final Double valorEsperado = 330.00;
		final Double valorEncontrado = crawler.getPrice();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaDataInicio() throws Exception {
		final Date valorEsperado = new SimpleDateFormat("dd/MM/yy").parse("10/03/17");
		final Date valorEncontrado = crawler.getStartDate();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaDataFim() throws Exception {
		final Date valorEsperado = new SimpleDateFormat("dd/MM/yy").parse("30/07/17");
		final Date valorEncontrado = crawler.getEndDate();
		assertThat(valorEncontrado, is(valorEsperado));		
	}

	@Test
	public void verificaOverview() throws Exception {
		final String valorEsperado = "Vencedor de mais de 125 prêmios internacionais e agora o musical mais antigo do mundo, Les Misérables chega ao Teatro Renault no dia 10 de março para uma temporada até o dia 30 de julho. Baseado no clássico romance de Victor Hugo, o espetáculo, em contraste com o cenário da França do século XIX, conta uma história fascinante de sonhos, amor, paixão, sacrifício e redenção – um testemunho atemporal para a sobrevivência do espírito humano.\n    \n          \n         Confira a agenda de musicais em São Paulo em 2017\n      \n          \n         7 teatros alternativos em SP que você precisa conhecer \n      PUBLICIDADEinRead invented by Teads\n          \n         Filmes e séries musicais para ver na Netflix\n      \n        \n  \n\nApresentada pelo Ministério da Cultura, a produção brasileira tem letras em português e os atores Daniel Diges (Jean Valjean), Nando Pradho (Javert), Kacau Gomes (Fantine), Clara Verdier (Cosette), Laura Lobo (Eponine), Filipe Bragança (Marius); Pedro Caetano (Enjolras), Ivan Parente (Thenardier) e Andrezza Massei (Madame Thenardier) entre os personagens principais.\n\n\n\nDentre as canções clássicas, estão \"I Dreamed a Dream\", \"On My Own\", \"Stars\", \"Bring Him Home\", \"Do You Hear The People Sing?\", \"One Day More\", \"Empty Chairs at Empty Tables\", \"Masters of the House” e muito mais.\n&nbsp;\n\nINGRESSOS:\n\nOs ingressos começam a ser vendidos a partir de 19 de janeiro na bilheteria do Teatro Renault, pela internet&nbsp;e pontos de venda espalhados pelo país. Os valores variam de R$ 25,00 a R$ 330,00.\n\nConfira a tabelas de preços do musical Les Misérables de março a julho:\n&nbsp;\n\n\n\t\n\t\t\n\t\t\t\n\t\t\tQUINTAS E SEXTAS&nbsp;– 21H&nbsp;\n\t\t\t\n\t\t\n\t\t\n\t\t\t\n\t\t\tSETOR\n\t\t\t\n\t\t\t\n\t\t\tMEIA-ENTRADA\n\t\t\t\n\t\t\t\n\t\t\tINTEIRA\n\t\t\t\n\t\t\n\t\t\n\t\t\t\n\t\t\tBALCÃO B\n\t\t\t\n\t\t\t\n\t\t\tR$ 25\n\t\t\t\n\t\t\t\n\t\t\tR$ 50\n\t\t\t\n\t\t\n\t\t\n\t\t\t\n\t\t\tVISÃO PARCIAL – BALCÃO B\n\t\t\t\n\t\t\t\n\t\t\tR$ 25\n\t\t\t\n\t\t\t\n\t\t\tR$ 50\n\t\t\t\n\t\t\n\t\t\n\t\t\t\n\t\t\tBALCÃO A\n\t\t\t\n\t\t\t\n\t\t\tR$ 70\n\t\t\t\n\t\t\t\n\t\t\tR$ 140\n\t\t\t\n\t\t\n\t\t\n\t\t\t\n\t\t\tCAMAROTE ZZ\n\t\t\t\n\t\t\t\n\t\t\tR$ 70\n\t\t\t\n\t\t\t\n\t\t\tR$ 140\n\t\t\t\n\t\t\n\t\t\n\t\t\t\n\t\t\tPLATEIA B\n\t\t\t\n\t\t\t\n\t\t\tR$ 110\n\t\t\t\n\t\t\t\n\t\t\tR$ 220\n\t\t\t\n\t\t\n\t\t\n\t\t\t\n\t\t\tPLATEIA A\n\t\t\t\n\t\t\t\n\t\t\tR$ 140\n\t\t\t\n\t\t\t\n\t\t\tR$ 280\n\t\t\t\n\t\t\n\t\t\n\t\t\t\n\t\t\tCAMAROTE\n\t\t\t\n\t\t\t\n\t\t\tR$ 140\n\t\t\t\n\t\t\t\n\t\t\tR$ 280\n\t\t\t\n\t\t\n\t\t\n\t\t\t\n\t\t\tVIP\n\t\t\t\n\t\t\t\n\t\t\tR$ 155\n\t\t\t\n\t\t\t\n\t\t\tR$ 310\n\t\t\t\n\t\t\n\t\n\n\n&nbsp;\n\n\n\t\n\t\t\n\t\t\t\n\t\t\tSÁBADOS &nbsp;– 16H E 21H | DOMINGOS – 15H E 20H\n\t\t\t\n\t\t\n\t\t\n\t\t\t\n\t\t\tSETOR\n\t\t\t\n\t\t\t\n\t\t\tMEIA-ENTRADA\n\t\t\t\n\t\t\t\n\t\t\tINTEIRA\n\t\t\t\n\t\t\n\t\t\n\t\t\t\n\t\t\tBALCÃO B\n\t\t\t\n\t\t\t\n\t\t\tR$ 25\n\t\t\t\n\t\t\t\n\t\t\tR$ 50\n\t\t\t\n\t\t\n\t\t\n\t\t\t\n\t\t\tVISÃO PARCIAL – BALCÃO B\n\t\t\t\n\t\t\t\n\t\t\tR$ 25\n\t\t\t\n\t\t\t\n\t\t\tR$ 50\n\t\t\t\n\t\t\n\t\t\n\t\t\t\n\t\t\tBALCÃO A\n\t\t\t\n\t\t\t\n\t\t\tR$ 85\n\t\t\t\n\t\t\t\n\t\t\tR$ 170\n\t\t\t\n\t\t\n\t\t\n\t\t\t\n\t\t\tCAMAROTE ZZ\n\t\t\t\n\t\t\t\n\t\t\tR$ 85\n\t\t\t\n\t\t\t\n\t\t\tR$ 170\n\t\t\t\n\t\t\n\t\t\n\t\t\t\n\t\t\tPLATEIA B\n\t\t\t\n\t\t\t\n\t\t\tR$ 120\n\t\t\t\n\t\t\t\n\t\t\tR$ 240\n\t\t\t\n\t\t\n\t\t\n\t\t\t\n\t\t\tPLATEIA A\n\t\t\t\n\t\t\t\n\t\t\tR$ 150\n\t\t\t\n\t\t\t\n\t\t\tR$ 300\n\t\t\t\n\t\t\n\t\t\n\t\t\t\n\t\t\tCAMAROTE\n\t\t\t\n\t\t\t\n\t\t\tR$ 150\n\t\t\t\n\t\t\t\n\t\t\tR$ 300\n\t\t\t\n\t\t\n\t\t\n\t\t\t\n\t\t\tVIP\n\t\t\t\n\t\t\t\n\t\t\tR$ 165\n\t\t\t\n\t\t\t\n\t\t\tR$ 330\n\t\t\t\n\t\t\n\t\n\n\n\n&nbsp;\n\n        ";
		final String valorEncontrado = crawler.getOverview();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	

}
