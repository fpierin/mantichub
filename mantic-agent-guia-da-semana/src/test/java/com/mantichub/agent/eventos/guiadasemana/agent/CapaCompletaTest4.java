package com.mantichub.agent.eventos.guiadasemana.agent;

import static com.mantichub.agent.eventos.guiadasemana.infra.TestUtils.fromFile;
import static java.text.MessageFormat.format;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.mantichub.commons.resource.ResourceInterface;
import com.mantichub.commons.resource.Resources;

public class CapaCompletaTest4 {
	
	private final static String arquivo = "src/test/resources/capa4.html";
	private GuiaDaSemanaAdapter adapter;
	
	@Before
	public void prepara() throws Exception {
		final String url = "http://www.guiadasemana.com.br/sao-paulo/agenda?palavra=&tipo=&canal=&data=&page=2";
		adapter = new GuiaDaSemanaAdapter(url, fromFile(arquivo));
	}
	
	@Test
	public void verificaTipo() throws Exception {
		assertThat(r(0).getType(), is(Resources.ExhibitionEvent));
		assertThat(r(1).getType(), is(Resources.SocialEvent));
		assertThat(r(2).getType(), is(Resources.ExhibitionEvent));
		assertThat(r(3).getType(), is(Resources.MusicEvent));
		assertThat(r(4).getType(), is(Resources.ScreeningEvent));
		assertThat(r(5).getType(), is(Resources.ScreeningEvent));
		assertThat(r(6).getType(), is(Resources.FoodEvent));
		assertThat(r(7).getType(), is(Resources.FoodEvent));
		assertThat(r(8).getType(), is(Resources.MusicEvent));
		assertThat(r(9).getType(), is(Resources.FoodEvent));
		assertThat(r(10).getType(), is(Resources.ScreeningEvent));
		assertThat(r(11).getType(), is(Resources.ExhibitionEvent));
		assertThat(r(12).getType(), is(Resources.ExhibitionEvent));
		assertThat(r(13).getType(), is(Resources.SocialEvent));
		assertThat(r(14).getType(), is(Resources.ExhibitionEvent));
		assertThat(r(15).getType(), is(nullValue()));
		assertThat(r(16).getType(), is(nullValue()));
	}
	
	private ResourceInterface r(final int i) {
		final String html = adapter.getResources().get(i);
		final ResourceInterface ri = new GuiaDaSemanaResourceAdapter(html);
		return ri;
	}
	
	public static void main(final String[] args) throws Exception {
		final String url = "http://www.guiadasemana.com.br/sao-paulo/agenda?palavra=&tipo=&canal=&data=&page=2";
		final GuiaDaSemanaAdapter adapter = new GuiaDaSemanaAdapter(url, fromFile(arquivo));
		final List<String> resources = adapter.getResources();
		for (int i = 0; i < resources.size(); i++) {
			final String html = resources.get(i);
			final ResourceInterface ri = new GuiaDaSemanaResourceAdapter(html);
			final String value = pegaValor(ri.getType());
			System.out.println(format("assertThat(r({0}).getType(), is({1}));", i, value));
		}
	}

	private static String pegaValor(final Object ri) {
//		return ri != null ? "\"" + String.valueOf(ri) + "\"" : "nullValue()";
		return ri != null ? "Resources." + String.valueOf(ri) : "nullValue()";
	}
	
	
}


