package com.mantichub.agent.usp.unittest.agent;

import static com.mantichub.agent.usp.infra.TestUtils.fromFile;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.apache.jena.rdf.model.Resource;
import org.junit.Before;
import org.junit.Test;

import com.mantichub.agent.core.infra.Restaurant;
import com.mantichub.agent.guiafolha.agent.GuiaDaFolhaRestaurantAdapter;
import com.mantichub.core.vocabulary.SCHEMA;

public class ButtinaTest {
	
	private final static String arquivo = "src/test/resources/buttina.html";
	private Restaurant resource;
	
	@Before
	public void prepara() throws Exception {
		resource = new GuiaDaFolhaRestaurantAdapter(fromFile(arquivo), null);
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
		final Resource valorEsperado = SCHEMA.Restaurant;
		final Resource valorEncontrado = resource.getType();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaTitulo() throws Exception {
		final String valorEsperado = "Buttina";
		final String valorEncontrado = resource.getTitle();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
}