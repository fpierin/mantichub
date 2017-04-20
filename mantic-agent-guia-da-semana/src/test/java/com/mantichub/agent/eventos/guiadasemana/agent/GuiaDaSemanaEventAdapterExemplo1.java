package com.mantichub.agent.eventos.guiadasemana.agent;

import static com.mantichub.agent.eventos.guiadasemana.infra.TestUtils.fromFile;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.mantichub.commons.resource.Event;
import com.mantichub.commons.resource.Resources;

public class GuiaDaSemanaEventAdapterExemplo1 {
	
	private Event crawler = null;
	private String html;
	
	@Before
	public void init() throws Exception {
		final String arquivo = "src/test/resources/evento1.html";
		html = fromFile(arquivo);
		crawler = new GuiaDaSemanaEventAdapter("", html);
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
		final Resources valorEsperado = Resources.TheaterEvent;
		final Resources valorEncontrado = crawler.getType();
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
		final String valorEsperado = "Vencedor de mais de 125 prêmios internacionais e agora o musical mais antigo do mundo, Les Misérables chega ao Teatro Renault no dia 10 de março para uma temporada até o dia 30 de julho. Baseado no clássico romance de Victor Hugo, o espetáculo, em contraste com o cenário da França do século XIX, conta uma história fascinante de sonhos, amor, paixão, sacrifício e redenção – um testemunho atemporal para a sobrevivência do espírito humano.    Confira a agenda de musicais em São Paulo em 2017    7 teatros alternativos em SP que você precisa conhecer  PUBLICIDADEinRead invented by Teads   Filmes e séries musicais para ver na Netflix    Apresentada pelo Ministério da Cultura, a produção brasileira tem letras em português e os atores Daniel Diges (Jean Valjean), Nando Pradho (Javert), Kacau Gomes (Fantine), Clara Verdier (Cosette), Laura Lobo (Eponine), Filipe Bragança (Marius); Pedro Caetano (Enjolras), Ivan Parente (Thenardier) e Andrezza Massei (Madame Thenardier) entre os personagens principais.  Dentre as canções clássicas, estão \"I Dreamed a Dream\", \"On My Own\", \"Stars\", \"Bring Him Home\", \"Do You Hear The People Sing?\", \"One Day More\", \"Empty Chairs at Empty Tables\", \"Masters of the House” e muito mais.  INGRESSOS: Os ingressos começam a ser vendidos a partir de 19 de janeiro na bilheteria do Teatro Renault, pela internet e pontos de venda espalhados pelo país. Os valores variam de R$ 25,00 a R$ 330,00. Confira a tabelas de preços do musical Les Misérables de março a julho:      QUINTAS E SEXTAS – 21H      SETOR   MEIA-ENTRADA   INTEIRA     BALCÃO B   R$ 25   R$ 50     VISÃO PARCIAL – BALCÃO B   R$ 25   R$ 50     BALCÃO A   R$ 70   R$ 140     CAMAROTE ZZ   R$ 70   R$ 140     PLATEIA B   R$ 110   R$ 220     PLATEIA A   R$ 140   R$ 280     CAMAROTE   R$ 140   R$ 280     VIP   R$ 155   R$ 310           SÁBADOS – 16H E 21H | DOMINGOS – 15H E 20H     SETOR   MEIA-ENTRADA   INTEIRA     BALCÃO B   R$ 25   R$ 50     VISÃO PARCIAL – BALCÃO B   R$ 25   R$ 50     BALCÃO A   R$ 85   R$ 170     CAMAROTE ZZ   R$ 85   R$ 170     PLATEIA B   R$ 120   R$ 240     PLATEIA A   R$ 150   R$ 300     CAMAROTE   R$ 150   R$ 300     VIP   R$ 165   R$ 330";
		final String valorEncontrado = crawler.getOverview();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	
}
