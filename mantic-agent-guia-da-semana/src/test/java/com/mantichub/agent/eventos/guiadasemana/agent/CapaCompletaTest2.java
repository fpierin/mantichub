package com.mantichub.agent.eventos.guiadasemana.agent;

import static com.mantichub.agent.eventos.guiadasemana.infra.TestUtils.fromFile;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import com.mantichub.commons.resource.ResourceInterface;
import com.mantichub.commons.resource.Resources;

public class CapaCompletaTest2 {
	
	private final static String arquivo = "src/test/resources/capa2.html";
	private final static DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
	private GuiaDaSemanaAdapter adapter;
	
	@Before
	public void prepara() throws Exception {
		final String url = "http://www.guiadasemana.com.br/sao-paulo/agenda?palavra=&tipo=&canal=&data=&page=2";
		adapter = new GuiaDaSemanaAdapter(url, fromFile(arquivo));
	}
	
	@Test
	public void verificaQtedDeRecursos() throws Exception {
		final int size = adapter.getResources().size();
		assertThat(size, is(17));
	}
	
	@Test
	public void verificaUrl() throws Exception {
		assertThat(r(0).getUrl(), is("http://www.guiadasemana.com.br/sao-paulo/gastronomia/evento/festa-doce-no-eataly"));
		assertThat(r(1).getUrl(), is("http://www.guiadasemana.com.br/sao-paulo/teatro/evento/bem-sertanejo-o-musical-tom-brasil-2017"));
		assertThat(r(2).getUrl(), is("http://www.guiadasemana.com.br/sao-paulo/gastronomia/evento/festival-um-dia-nas-arabias"));
		assertThat(r(3).getUrl(), is("http://www.guiadasemana.com.br/sao-paulo/musica/evento/pianista-alvaro-siviero-se-apresenta-com-a-orquestra-juvenil-de-heliopolis-museu-de-arte-de-sao-paulo-masp-23-04-2017"));
		assertThat(r(4).getUrl(), is("http://www.guiadasemana.com.br/sao-paulo/teatro/evento/o-leao-e-a-bailarina-teatro-j.-safra-01-04-2017"));
		assertThat(r(5).getUrl(), is("http://www.guiadasemana.com.br/sao-paulo/exposicao/evento/castelo-ra-tim-bum-no-memorial-da-america-latina-2017"));
		assertThat(r(6).getUrl(), is("http://www.guiadasemana.com.br/sao-paulo/teatro/evento/60-decada-de-arromba-doc-musical-theatro-net-sao-paulo-2017"));
		assertThat(r(7).getUrl(), is("http://www.guiadasemana.com.br/sao-paulo/na-cidade/evento/refugiados-um-lar-chamado-sao-paulo-shopping-center-3-17-03-2017"));
		assertThat(r(8).getUrl(), is("http://www.guiadasemana.com.br/sao-paulo/teatro/evento/doc.-eremitas-2017-03-10"));
		assertThat(r(9).getUrl(), is("http://www.guiadasemana.com.br/sao-paulo/teatro/evento/o-homem-de-la-mancha-teatro-alfa-2017"));
		assertThat(r(10).getUrl(), is("http://www.guiadasemana.com.br/sao-paulo/restaurantes/evento/brunch-weekend"));
		assertThat(r(11).getUrl(), is("http://www.guiadasemana.com.br/sao-paulo/exposicao/evento/jeito-de-corpo-espaco-paulista-de-arte-12-04-2017"));
		assertThat(r(12).getUrl(), is("http://www.guiadasemana.com.br/sao-paulo/na-cidade/evento/refugiados-um-lar-chamado-sao-paulo-shopping-center-3-17-03-2017"));
		assertThat(r(13).getUrl(), is("http://www.guiadasemana.com.br/sao-paulo/exposicao/evento/frida-e-eu-unibes-cultural-11-03-2017"));
		assertThat(r(14).getUrl(), is("http://www.guiadasemana.com.br/sao-paulo/exposicao/evento/jeito-de-corpo-espaco-paulista-de-arte-12-04-2017"));
	}
	
	@Test
	public void verificaTitulo() throws Exception {
		assertThat(r(0).getTitle(), is("Eataly São Paulo realiza Festa Doce"));
		assertThat(r(1).getTitle(), is("Bem Sertanejo, O Musical"));
		assertThat(r(2).getTitle(), is("Festival Um Dia nas Arábias no Tatuapé"));
		assertThat(r(3).getTitle(), is("Pianista Alvaro Siviero se apresenta com a Orquestra Juvenil de Heliópolis"));
		assertThat(r(4).getTitle(), is("O Leão e a Bailarina"));
		assertThat(r(5).getTitle(), is("Memorial da América Latina terá mostra do Castelo Rá-Tim-Bum"));
		assertThat(r(6).getTitle(), is("60! Década de Arromba – Doc. Musical"));
		assertThat(r(7).getTitle(), is("Refugiados, um lar chamado São Paulo"));
		assertThat(r(8).getTitle(), is("DOC. eremitas"));
		assertThat(r(9).getTitle(), is("O Homem De La Mancha"));
		assertThat(r(10).getTitle(), is("3º Brunch Weekend em São Paulo"));
		assertThat(r(11).getTitle(), is("Jeito de Corpo"));
		assertThat(r(12).getTitle(), is("Refugiados, um lar chamado São Paulo"));
		assertThat(r(13).getTitle(), is("Frida e eu"));
		assertThat(r(14).getTitle(), is("Jeito de Corpo"));
	}
	
	@Test
	public void verificaDescription() throws Exception {
		assertThat(r(0).getDescription(), is("Evento vai trazer docerias famosas com opções de guloseimas a partir de R$ 5,00"));
		assertThat(r(1).getDescription(), is("Superprodução marca a estreia do cantor Michel Teló como ator"));
		assertThat(r(2).getDescription(), is("Evento no Tatuapé busca apoiar os refugiados sírios no Brasil, trazendo gastronomia e artesanato"));
		assertThat(r(3).getDescription(), is("Músico une-se aos jovens músicos em concerto no MASP"));
		assertThat(r(4).getDescription(), is("Musical “O Leão e a Bailarina” chega pela primeira vez a São Paulo"));
		assertThat(r(5).getDescription(), is("O público terá a chance de entrar num Castelo idêntico ao da série, construído numa área de 700m²"));
		assertThat(r(6).getDescription(), is("O espetáculo mescla humor, números de circo e ilusionismo"));
		assertThat(r(7).getDescription(), is("Shows, feira étnica e intervenção artística ocupam o Center 3 em prol aos refugiados no Brasil"));
		assertThat(r(8).getDescription(), is("Entre os dias 10 de março e 30 de abril, a Funarte recebe a peça \"DOC. eremitas\", sempre às sextas e sábados,..."));
		assertThat(r(9).getDescription(), is("O espetáculo fica em cartaz até 2 de abril"));
		assertThat(r(10).getDescription(), is("Festival em abril traz para São Paulo casas com cardápio especial para o brunch"));
		assertThat(r(11).getDescription(), is("Exposição faz observação ao cotidiano e ao corpo negro na Bahia"));
		assertThat(r(12).getDescription(), is("Shows, feira étnica e intervenção artística ocupam o Center 3 em prol aos refugiados no Brasil"));
		assertThat(r(13).getDescription(), is("Exposição chega ao Unibes Cultural com foco no público infantil"));
		assertThat(r(14).getDescription(), is("Exposição faz observação ao cotidiano e ao corpo negro na Bahia"));
	}
	
	@Test
	public void verificaLatitude() throws Exception {
		assertThat(r(0).getLatitude(), is(-23.591188));
		assertThat(r(1).getLatitude(), is(-23.638514));
		assertThat(r(2).getLatitude(), is(-23.555984));
		assertThat(r(3).getLatitude(), is(-23.561209));
		assertThat(r(4).getLatitude(), is(-23.516928));
		assertThat(r(5).getLatitude(), is(-23.526407));
		assertThat(r(6).getLatitude(), is(-23.6));
		assertThat(r(7).getLatitude(), is(-23.558372));
		assertThat(r(8).getLatitude(), is(-23.535309));
		assertThat(r(9).getLatitude(), is(-23.650797));
		assertThat(r(10).getLatitude(), is(nullValue()));
		assertThat(r(11).getLatitude(), is(-23.562815));
		assertThat(r(12).getLatitude(), is(-23.558372));
		assertThat(r(13).getLatitude(), is(-23.550957));
		assertThat(r(14).getLatitude(), is(-23.562815));
	}
	
	@Test
	public void verificaLongitude() throws Exception {
		assertThat(r(0).getLongitude(), is(-46.683434));
		assertThat(r(1).getLongitude(), is(-46.720051));
		assertThat(r(2).getLongitude(), is(-46.560047));
		assertThat(r(3).getLongitude(), is(-46.65641));
		assertThat(r(4).getLongitude(), is(-46.67667));
		assertThat(r(5).getLongitude(), is(-46.668453));
		assertThat(r(6).getLongitude(), is(-46.6833));
		assertThat(r(7).getLongitude(), is(-46.65958));
		assertThat(r(8).getLongitude(), is(-46.65065));
		assertThat(r(9).getLongitude(), is(-46.721104));
		assertThat(r(10).getLatitude(), is(nullValue()));
		assertThat(r(11).getLongitude(), is(-46.680046));
		assertThat(r(12).getLongitude(), is(-46.65958));
		assertThat(r(13).getLongitude(), is(-46.67844));
		assertThat(r(14).getLongitude(), is(-46.680046));
	}	
	
	@Test
	public void verificaEndereco() throws Exception {
		assertThat(r(0).getStreetAddress(), is("Avenida Presidente Juscelino Kubitschek, 1.489"));
		assertThat(r(1).getStreetAddress(), is("Rua Bragança Paulista, 1281"));
		assertThat(r(2).getStreetAddress(), is("Rua Canuto de Abreu, s/n"));
		assertThat(r(3).getStreetAddress(), is("Avenida Paulista, 1578"));
		assertThat(r(4).getStreetAddress(), is("Rua Josef Kryss, 318"));
		assertThat(r(5).getStreetAddress(), is("Avenida Auro Soares de Moura Andrade, 664"));
		assertThat(r(6).getStreetAddress(), is("Rua Olimpíadas, 360 - 5º andar (Shopping Vila Olímpia)"));
		assertThat(r(7).getStreetAddress(), is("Avenida Paulista, 2064"));
		assertThat(r(8).getStreetAddress(), is("Alameda Nothmann , 1058"));
		assertThat(r(9).getStreetAddress(), is("Rua Bento Branco de Andrade Filho, 722"));
		assertThat(r(10).getStreetAddress(), is(nullValue()));
		assertThat(r(11).getStreetAddress(), is("Rua Francisco Leitão, 198"));
		assertThat(r(12).getStreetAddress(), is("Avenida Paulista, 2064"));
		assertThat(r(13).getStreetAddress(), is("Rua Oscar Freire, 2.500"));
		assertThat(r(14).getStreetAddress(), is("Rua Francisco Leitão, 198"));
	}
	
	@Test
	public void verificaTipo() throws Exception {
		assertThat(r(0).getType(), is(Resources.FoodEvent));
		assertThat(r(1).getType(), is(Resources.TheaterEvent));
		assertThat(r(2).getType(), is(Resources.FoodEvent));
		assertThat(r(3).getType(), is(Resources.MusicEvent));
		assertThat(r(4).getType(), is(Resources.TheaterEvent));
		assertThat(r(5).getType(), is(Resources.ExhibitionEvent));
		assertThat(r(6).getType(), is(Resources.TheaterEvent));
		assertThat(r(7).getType(), is(Resources.SocialEvent));
		assertThat(r(8).getType(), is(Resources.TheaterEvent));
		assertThat(r(9).getType(), is(Resources.TheaterEvent));
		assertThat(r(10).getType(), is(Resources.Festival));
		assertThat(r(11).getType(), is(Resources.ExhibitionEvent));
		assertThat(r(12).getType(), is(Resources.SocialEvent));
		assertThat(r(13).getType(), is(Resources.ExhibitionEvent));
		assertThat(r(14).getType(), is(Resources.ExhibitionEvent));
	}
	
	@Test
	public void verificaDataInicio() throws Exception {
		assertThat(r(0).getStartDate(), is(df.parse("Fri Apr 21 00:00:00 BRT 2017")));
		assertThat(r(1).getStartDate(), is(df.parse("Fri Apr 21 00:00:00 BRT 2017")));
		assertThat(r(2).getStartDate(), is(df.parse("Sat Apr 22 00:00:00 BRT 2017")));
		assertThat(r(3).getStartDate(), is(df.parse("Sun Apr 23 00:00:00 BRT 2017")));
		assertThat(r(4).getStartDate(), is(df.parse("Sun Apr 23 00:00:00 BRT 2017")));
		assertThat(r(5).getStartDate(), is(df.parse("Sun Apr 23 00:00:00 BRT 2017")));
		assertThat(r(6).getStartDate(), is(df.parse("Sun Apr 23 00:00:00 BRT 2017")));
		assertThat(r(7).getStartDate(), is(df.parse("Sun Apr 23 00:00:00 BRT 2017")));
		assertThat(r(8).getStartDate(), is(df.parse("Sun Apr 23 00:00:00 BRT 2017")));
		assertThat(r(9).getStartDate(), is(df.parse("Sun Apr 23 00:00:00 BRT 2017")));
		assertThat(r(10).getStartDate(), is(df.parse("Sun Apr 23 00:00:00 BRT 2017")));
		assertThat(r(11).getStartDate(), is(df.parse("Mon Apr 24 00:00:00 BRT 2017")));
		assertThat(r(12).getStartDate(), is(df.parse("Mon Apr 24 00:00:00 BRT 2017")));
		assertThat(r(13).getStartDate(), is(df.parse("Mon Apr 24 00:00:00 BRT 2017")));
		assertThat(r(14).getStartDate(), is(df.parse("Tue Apr 25 00:00:00 BRT 2017")));
	}
	
	@Test
	public void verificaDataFim() throws Exception {
		assertThat(r(0).getEndDate(), is(df.parse("Sun Apr 23 23:59:00 BRT 2017")));
		assertThat(r(1).getEndDate(), is(df.parse("Sun Apr 23 23:59:00 BRT 2017")));
		assertThat(r(2).getEndDate(), is(df.parse("Sun Apr 23 23:59:00 BRT 2017")));
		assertThat(r(3).getEndDate(), is(nullValue()));
		assertThat(r(4).getEndDate(), is(nullValue()));
		assertThat(r(5).getEndDate(), is(nullValue()));
		assertThat(r(6).getEndDate(), is(nullValue()));
		assertThat(r(7).getEndDate(), is(nullValue()));
		assertThat(r(8).getEndDate(), is(nullValue()));
		assertThat(r(9).getEndDate(), is(nullValue()));
		assertThat(r(10).getEndDate(), is(nullValue()));
		assertThat(r(11).getEndDate(), is(nullValue()));
		assertThat(r(12).getEndDate(), is(nullValue()));
		assertThat(r(13).getEndDate(), is(nullValue()));
		assertThat(r(14).getEndDate(), is(nullValue()));
	}

	@Test
	public void verificaHoraInicio() throws Exception {
		assertThat(r(0).getStartTime(), is(df.parse("Fri Apr 21 00:00:00 BRT 2017")));
		assertThat(r(1).getStartTime(), is(df.parse("Fri Apr 21 00:00:00 BRT 2017")));
		assertThat(r(2).getStartTime(), is(df.parse("Sat Apr 22 00:00:00 BRT 2017")));
		assertThat(r(3).getStartTime(), is(df.parse("Sun Apr 23 00:00:00 BRT 2017")));
		assertThat(r(4).getStartTime(), is(df.parse("Sun Apr 23 00:00:00 BRT 2017")));
		assertThat(r(5).getStartTime(), is(df.parse("Sun Apr 23 00:00:00 BRT 2017")));
		assertThat(r(6).getStartTime(), is(df.parse("Sun Apr 23 00:00:00 BRT 2017")));
		assertThat(r(7).getStartTime(), is(df.parse("Sun Apr 23 00:00:00 BRT 2017")));
		assertThat(r(8).getStartTime(), is(df.parse("Sun Apr 23 00:00:00 BRT 2017")));
		assertThat(r(9).getStartTime(), is(df.parse("Sun Apr 23 00:00:00 BRT 2017")));
		assertThat(r(10).getStartTime(), is(df.parse("Sun Apr 23 00:00:00 BRT 2017")));
		assertThat(r(11).getStartTime(), is(df.parse("Mon Apr 24 00:00:00 BRT 2017")));
		assertThat(r(12).getStartTime(), is(df.parse("Mon Apr 24 00:00:00 BRT 2017")));
		assertThat(r(13).getStartTime(), is(df.parse("Mon Apr 24 00:00:00 BRT 2017")));
		assertThat(r(14).getStartTime(), is(df.parse("Tue Apr 25 00:00:00 BRT 2017")));
	}
	
	@Test
	public void verificaHoraFim() throws Exception {
		assertThat(r(0).getEndTime(), is(df.parse("Sun Apr 23 23:59:00 BRT 2017")));
		assertThat(r(1).getEndTime(), is(df.parse("Sun Apr 23 23:59:00 BRT 2017")));
		assertThat(r(2).getEndTime(), is(df.parse("Sun Apr 23 23:59:00 BRT 2017")));
		assertThat(r(3).getEndDate(), is(nullValue()));
		assertThat(r(4).getEndDate(), is(nullValue()));
		assertThat(r(5).getEndDate(), is(nullValue()));
		assertThat(r(6).getEndDate(), is(nullValue()));
		assertThat(r(7).getEndDate(), is(nullValue()));
		assertThat(r(8).getEndDate(), is(nullValue()));
		assertThat(r(9).getEndDate(), is(nullValue()));
		assertThat(r(10).getEndDate(), is(nullValue()));
		assertThat(r(11).getEndDate(), is(nullValue()));
		assertThat(r(12).getEndDate(), is(nullValue()));
		assertThat(r(13).getEndDate(), is(nullValue()));
		assertThat(r(14).getEndDate(), is(nullValue()));
	}
	
	private ResourceInterface r(final int i) {
		final String html = adapter.getResources().get(i);
		final ResourceInterface ri = new GuiaDaSemanaResourceAdapter(html);
		return ri;
	}
	
	
}

