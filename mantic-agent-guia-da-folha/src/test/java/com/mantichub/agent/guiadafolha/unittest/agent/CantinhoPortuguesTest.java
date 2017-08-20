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

public class CantinhoPortuguesTest {
	
	private final static String arquivo = "src/test/resources/cantinho_portugues.html";
	private FoodEstablishment resource;
	
	@Before
	public void prepara() throws Exception {
		resource = new GuiaDaFolhaRestaurantAdapter(null, fromFile(arquivo));
	}

	@Test
	public void verificaCozinha() throws Exception {
		final String valorEsperado = "Portuguesa";
		final String valorEncontrado = resource.getServesCuisine();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaDescricao() throws Exception {
		final String valorEsperado = "É um restaurante típico português: repare no sotaque carregado dos proprietários e nas sardinhas que chiam na grelha. Tem ambiente simples e não se concentra no bacalhau: além dos bolinhos e de uma salada, tem apenas uma receita em posta (à lagareiro) e mais três com o peixe desfiado. Fora do bacalhau, há um pouco de tudo o que se encontraria numa boa tasca: arrozes de polvo ou pato, dobradinha com feijão branco e folhado de alheira.";
		final String valorEncontrado = resource.getDescription();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaEndereco() throws Exception {
		final String valorEsperado = "Avenida dos Imarés, 656";
		final String valorEncontrado = resource.getStreetAddress();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaHorarioAbertura() throws Exception {
		final List<String> valorEncontrado = resource.getOpeningHours();
		assertThat(valorEncontrado.get(0), is("We 09:00-23:00"));
		assertThat(valorEncontrado.get(1), is("Th 09:00-23:00"));
		assertThat(valorEncontrado.get(2), is("Fr 09:00-23:00"));
		assertThat(valorEncontrado.get(3), is("Sa 09:00-00:00"));
		assertThat(valorEncontrado.get(4), is("Su 09:00-18:00"));
		assertThat(valorEncontrado.get(5), is("Tu 09:00-23:00"));
	}

	@Test
	public void verificaLatitude() throws Exception {
		final Double valorEsperado = -23.6143958;
		final Double valorEncontrado = resource.getLatitude();
		assertThat(valorEncontrado, is(valorEsperado));
	}

	@Test
	public void verificaLongitude() throws Exception {
		final Double valorEsperado = -46.6638437;
		final Double valorEncontrado = resource.getLongitude();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaTelefone() throws Exception {
		final String valorEsperado = "5041-9787";
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
		final String valorEsperado = "Cantinho Português";
		final String valorEncontrado = resource.getTitle();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
	@Test
	public void verificaVariacaoPreco() throws Exception {
		final String valorEsperado = "$$$$";
		final String valorEncontrado = resource.getPriceRange();
		assertThat(valorEncontrado, is(valorEsperado));
	}
	
}
