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

public class GuiaDaSemanaEventAdapterExemplo4 {
	
	private Event crawler = null;
	private String html;
	
	@Before
	public void init() throws Exception {
		final String arquivo = "src/test/resources/evento4.html";
		html = fromFile(arquivo);
		crawler = new GuiaDaSemanaEventAdapter("", html);
	}
	
	@Test
	public void verificaTitulo() throws Exception {
		final String valorEsperado = "Jantar às Cegas: Pratos Harmonizados com Cerveja";
		final String valorEncontrado = crawler.getTitle();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaEndereco() throws Exception {
		final String valorEsperado = "Rua Agostinho Cantu, 47";
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
		final Resources valorEsperado = Resources.ExhibitionEvent;
		final Resources valorEncontrado = crawler.getType();
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
		final String valorEsperado = "No dia 21 de junho, das 20h às 23h, o Duas Terezas, bistrô localizado no espaço gastronômico Vila Butantan, realiza um Jantar às Cegas com o tema Pratos Harmonizados com Cerveja. São 18 vagas para o evento e cada ingressos custa R$ 120, podendo ser comprados pelo site Foodpass.    Bistrô em São Paulo realiza série de jantares às cegas    Veja também: Jantar às Cegas - Carnes Exóticas    Veja também: Jantar às Cegas - Pratos O Feminismo em Pratos    A experiência, preparada pela chef Mariana Pelozio, acontece em cinco atos: entrada, salada, dois pratos e sobremesa. O participante nunca sabe o que será servido. Restrições e preferências alimentares podem ser indicadas no ato da compra do ingresso. Na preparação do prato e também para acompanhar a refeição os participantes do evento são convidados a fechar os olhos. Em cada jantar o participante será surpreendido em como é possível aguçar e descobrir novas sensações em sabores até então escondidos. O participante só descobrirá o cardápio na hora do jantar.";
		final String valorEncontrado = crawler.getOverview();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	public static void main(final String[] args) throws Exception {
		final DateFormat df = new SimpleDateFormat("EEE dd MMM", new Locale("pt", "BR"));

		System.out.println(df.format(new Date()));

	}
	
}
