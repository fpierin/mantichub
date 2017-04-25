package com.mantichub.agent.guiadafolha.unittest.agent;

import static com.mantichub.agent.guiadafolha.infra.TestUtils.fromFile;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.mantichub.agent.guiafolha.agent.GuiaDaFolhaRestaurantAdapter;
import com.mantichub.commons.resource.Resources;
import com.mantichub.commons.resource.FoodEstablishment;

public class ButtinaTest {
	
	private final static String arquivo = "src/test/resources/buttina.html";
	private FoodEstablishment resource;
	
	@Before
	public void prepara() throws Exception {
		resource = new GuiaDaFolhaRestaurantAdapter(null, fromFile(arquivo));
	}
	
	@Test
	public void verificaCozinha() throws Exception {
		final String valorEsperado = "Italiana";
		final String valorEncontrado = resource.getCuisine();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaDescricao() throws Exception {
		final String valorEsperado = "Esta é a primeira filial do restaurante cuja matriz, em Pinheiros, já soma 20 anos. Com salão amplo e pé direito alto, tem cardápio elaborado pela chef Filomena Chiarella ---ela foca a cozinha do sul da Itália, sua região de origem. Entre os pratos, prepara opções como o Sspaghettini di Cacao, massa artesanal a base de cacau com molho de mascarpone e presunto cru, ou o Gamberi alla Birra, camarões salteados no alho, azeite, cerveja e pimenta vermelha, acompanhados de nhoque de semolina e salada.";
		final String valorEncontrado = resource.getDescription();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaEndereco() throws Exception {
		final String valorEsperado = "Avenida Giovanni Gronchi, 5930";
		final String valorEncontrado = resource.getStreetAddress();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaHorarioAbertura() throws Exception {
		final List<String> valorEncontrado = resource.getOpeningHours();
		assertThat(valorEncontrado.get(0), is("We 12:00-22:00"));
		assertThat(valorEncontrado.get(1), is("Th 12:00-22:00"));
		assertThat(valorEncontrado.get(2), is("Fr 12:00-23:00"));
		assertThat(valorEncontrado.get(3), is("Sa 12:00-23:00"));
		assertThat(valorEncontrado.get(4), is("Su 12:00-21:00"));
		assertThat(valorEncontrado.get(5), is("Mo 12:00-22:00"));
		assertThat(valorEncontrado.get(6), is("Tu 12:00-22:00"));
	}

	@Test
	public void verificaLatitude() throws Exception {
		final Double valorEsperado = -23.6315938;
		final Double valorEncontrado = resource.getLatitude();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaLongitude() throws Exception {
		final Double valorEsperado = -46.7377384;
		final Double valorEncontrado = resource.getLongitude();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaTelefone() throws Exception {
		final String valorEsperado = "5063-4091";
		final String valorEncontrado = resource.getTelephone();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaTipo() throws Exception {
		final Resources valorEsperado = Resources.Restaurant;
		final Resources valorEncontrado = resource.getType();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaTitulo() throws Exception {
		final String valorEsperado = "Buttina";
		final String valorEncontrado = resource.getTitle();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaVariacaoPreco() throws Exception {
		final String valorEsperado = "$$";
		final String valorEncontrado = resource.getPriceRange();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
}
