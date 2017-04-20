package com.mantichub.agent.guiadafolha.unittest.agent;

import static com.mantichub.agent.guiadafolha.infra.TestUtils.fromFile;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.mantichub.agent.guiafolha.agent.GuiaDaFolhaRestaurantAdapter;
import com.mantichub.commons.resource.Resources;
import com.mantichub.commons.resource.Restaurant;

public class MassariaArtesanalTest {

	private final static String arquivo = "src/test/resources/massaria_artesanal.html";
	private Restaurant resource;

	@Before
	public void prepara() throws Exception {
		resource = new GuiaDaFolhaRestaurantAdapter(null, fromFile(arquivo));
	}
	
	@Test
	public void verificaCozinha() throws Exception {
		final String valorEsperado = "Variada";
		final String valorEncontrado = resource.getCuisine();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaDescricao() throws Exception {
		final String valorEsperado = "A casa é uma espécie de bistrô que serve massas orgânicas e com baixo teor de glúten. Há três opções: penne, espaguete e talharim, que podem ser combinados com os molhos disponíveis no dia, como pesto de azeitona preta, pesto de manjericão, pomodoro e gorgonzola com pera. O prato chega à mesa acompanhado de uma saladinha de folhas coberta com molho lemon pepper. Os bowls e talheres em que a comida é servida são descartáveis e biodegradáveis e, depois de usados, vão para compostagem. Para beber, há cervejas artesanais, alguns rótulos de vinhos, sucos e refrigerantes orgânicos.";
		final String valorEncontrado = resource.getDescription();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaEndereco() throws Exception {
		final String valorEsperado = "Avenida Jandira, 669";
		final String valorEncontrado = resource.getStreetAddress();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaHorarioAbertura() throws Exception {
		final List<String> valorEncontrado = resource.getOpeningHours();
		assertThat(valorEncontrado.get(0), is("We 19:00-23:00"));
		assertThat(valorEncontrado.get(1), is("Th 19:00-23:00"));
		assertThat(valorEncontrado.get(2), is("Fr 19:00-23:00"));
		assertThat(valorEncontrado.get(3), is("Sa 12:00-16:00"));
		assertThat(valorEncontrado.get(4), is("Su 12:00-16:00"));
	}
	
	@Test
	public void verificaLatitude() throws Exception {
		final Double valorEsperado = -23.6095913;
		final Double valorEncontrado = resource.getLatitude();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaLongitude() throws Exception {
		final Double valorEsperado = -46.6591025;
		final Double valorEncontrado = resource.getLongitude();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaTelefone() throws Exception {
		final String valorEsperado = "3791-6826";
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
		final String valorEsperado = "Massaria Artesanal";
		final String valorEncontrado = resource.getTitle();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaVariacaoPreco() throws Exception {
		final String valorEsperado = "$";
		final String valorEncontrado = resource.getPriceRange();
		assertThat(valorEncontrado, is(valorEsperado));
	}

}
