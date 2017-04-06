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

public class BiozoneTest {
	
	private final static String arquivo = "src/test/resources/biozone.html";
	private Restaurant resource;
	
	@Before
	public void prepara() throws Exception {
		resource = new GuiaDaFolhaRestaurantAdapter(fromFile(arquivo), null);
	}
	
	@Test
	public void verificaCozinha() throws Exception {
		final String valorEsperado = "Variada";
		final String valorEncontrado = resource.getCuisine();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaDescricao() throws Exception {
		final String valorEsperado = "O restaurante, que fica nos fundos de um espaço com estúdio de tatuagem e brechó, tem como proposta fazer comida vegana e orgânica. O menu apresenta um prato do dia, que pode incluir arroz com sementes, dahl de lentilhas com especiarias e bobó de berinjela com shimeji. As receitas de kibe de berinjela ou abóbora são boas pedidas.";
		final String valorEncontrado = resource.getDescription();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaEndereco() throws Exception {
		final String valorEsperado = "Rua Fradique Coutinho, 1225";
		final String valorEncontrado = resource.getStreetAddress();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaLatitude() throws Exception {
		final Double valorEsperado = -23.5569221;
		final Double valorEncontrado = resource.getLatitude();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaLongitude() throws Exception {
		final Double valorEsperado = -46.6908631;
		final Double valorEncontrado = resource.getLongitude();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaTelefone() throws Exception {
		final String valorEsperado = "3360-3609";
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
		final String valorEsperado = "Biozone";
		final String valorEncontrado = resource.getTitle();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
}
