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

public class CapaCompletaTest3 {
	
	private final static String arquivo = "src/test/resources/capa3.html";
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
		assertThat(r(0).getUrl(), is("http://www.guiadasemana.com.br/sao-paulo/exposicao/evento/castelo-ra-tim-bum-no-memorial-da-america-latina-2017"));
		assertThat(r(1).getUrl(), is("http://www.guiadasemana.com.br/sao-paulo/na-cidade/evento/refugiados-um-lar-chamado-sao-paulo-shopping-center-3-17-03-2017"));
		assertThat(r(2).getUrl(), is("http://www.guiadasemana.com.br/sao-paulo/exposicao/evento/frida-e-eu-unibes-cultural-11-03-2017"));
		assertThat(r(3).getUrl(), is("http://www.guiadasemana.com.br/sao-paulo/shows/evento/ivan-lins-em-sao-paulo"));
		assertThat(r(4).getUrl(), is("http://www.guiadasemana.com.br/sao-paulo/cinema/evento/mostra-de-filmes-da-australia-2017-2017-04-12"));
		assertThat(r(5).getUrl(), is("http://www.guiadasemana.com.br/sao-paulo/cinema/evento/mostra-jovem.doc-cinemateca-brasileira-25-04-2017"));
		assertThat(r(6).getUrl(), is("http://www.guiadasemana.com.br/sao-paulo/gastronomia/evento/festival-de-camaroes-e-massas-no-ceagesp"));
		assertThat(r(7).getUrl(), is("http://www.guiadasemana.com.br/sao-paulo/gastronomia/evento/festival-de-sanduiche-de-padoca-na-padaria-brasileira"));
		assertThat(r(8).getUrl(), is("http://www.guiadasemana.com.br/sao-paulo/shows/evento/forte-piano-no-sesc-ipiranga"));
		assertThat(r(9).getUrl(), is("http://www.guiadasemana.com.br/sao-paulo/gastronomia/evento/comida-di-buteco"));
		assertThat(r(10).getUrl(), is("http://www.guiadasemana.com.br/sao-paulo/cinema/evento/ccbb-recebe-retrospectiva-de-michelangelo-antonioni-2017-04-26"));
		assertThat(r(11).getUrl(), is("http://www.guiadasemana.com.br/sao-paulo/exposicao/evento/jeito-de-corpo-espaco-paulista-de-arte-12-04-2017"));
		assertThat(r(12).getUrl(), is("http://www.guiadasemana.com.br/sao-paulo/exposicao/evento/castelo-ra-tim-bum-no-memorial-da-america-latina-2017"));
		assertThat(r(13).getUrl(), is("http://www.guiadasemana.com.br/sao-paulo/na-cidade/evento/refugiados-um-lar-chamado-sao-paulo-shopping-center-3-17-03-2017"));
		assertThat(r(14).getUrl(), is("http://www.guiadasemana.com.br/sao-paulo/exposicao/evento/frida-e-eu-unibes-cultural-11-03-2017"));
		assertThat(r(15).getUrl(), is(nullValue()));
		assertThat(r(16).getUrl(), is(nullValue()));
	}
	
	@Test
	public void verificaTitulo() throws Exception {
		assertThat(r(0).getTitle(), is("Memorial da América Latina terá mostra do Castelo Rá-Tim-Bum"));
		assertThat(r(1).getTitle(), is("Refugiados, um lar chamado São Paulo"));
		assertThat(r(2).getTitle(), is("Frida e eu"));
		assertThat(r(3).getTitle(), is("Ivan Lins em São Paulo"));
		assertThat(r(4).getTitle(), is("Mostra de Filmes da Austrália 2017"));
		assertThat(r(5).getTitle(), is("Mostra Jovem.Doc"));
		assertThat(r(6).getTitle(), is("Festival de Camarões e Massas no Ceagesp"));
		assertThat(r(7).getTitle(), is("Festival de Sanduíche de Padoca na Padaria Brasileira"));
		assertThat(r(8).getTitle(), is("Forte Piano no Sesc Ipiranga"));
		assertThat(r(9).getTitle(), is("Festival Comida di Buteco traz participantes em São Paulo com preços até R$ 25,90"));
		assertThat(r(10).getTitle(), is("Michelangelo Antonioni ganha retrospectiva com mais de 30 filmes em SP"));
		assertThat(r(11).getTitle(), is("Jeito de Corpo"));
		assertThat(r(12).getTitle(), is("Memorial da América Latina terá mostra do Castelo Rá-Tim-Bum"));
		assertThat(r(13).getTitle(), is("Refugiados, um lar chamado São Paulo"));
		assertThat(r(14).getTitle(), is("Frida e eu"));
		assertThat(r(15).getTitle(), is(nullValue()));
		assertThat(r(16).getTitle(), is(nullValue()));
	}
	
	@Test
	public void verificaDescription() throws Exception {
		assertThat(r(0).getDescription(), is("O público terá a chance de entrar num Castelo idêntico ao da série, construído numa área de 700m²"));
		assertThat(r(1).getDescription(), is("Shows, feira étnica e intervenção artística ocupam o Center 3 em prol aos refugiados no Brasil"));
		assertThat(r(2).getDescription(), is("Exposição chega ao Unibes Cultural com foco no público infantil"));
		assertThat(r(3).getDescription(), is("Músico apresenta show acústico em abril no Teatro Porto Seguro"));
		assertThat(r(4).getDescription(), is("Filmes como “Moulin Rouge” e “O Grande Gatsby” estão em cartaz no CCBB até 24 de abril"));
		assertThat(r(5).getDescription(), is("Cinemateca Brasileira exibe documentários inéditos de jovens diretores brasileiros"));
		assertThat(r(6).getDescription(), is("Festival custa R$ 59,90 por pessoa e inclui espaguete com camarão, risoto, pizza e muito mais"));
		assertThat(r(7).getDescription(), is("Evento traz sanduíches brasileiros clássicos de padaria durante o mês de abril"));
		assertThat(r(8).getDescription(), is("Série de shows no Sesc Ipiranga trazem destaques do piano brasileiro"));
		assertThat(r(9).getDescription(), is("São quase 60 bares participantes da nova edição do concurso que elege o melhor boteco de SP"));
		assertThat(r(10).getDescription(), is("Mostra \"Aventura Antonioni\" acontece no CCBB e no CineSesc entre abril e maio"));
		assertThat(r(11).getDescription(), is("Exposição faz observação ao cotidiano e ao corpo negro na Bahia"));
		assertThat(r(12).getDescription(), is("O público terá a chance de entrar num Castelo idêntico ao da série, construído numa área de 700m²"));
		assertThat(r(13).getDescription(), is("Shows, feira étnica e intervenção artística ocupam o Center 3 em prol aos refugiados no Brasil"));
		assertThat(r(14).getDescription(), is("Exposição chega ao Unibes Cultural com foco no público infantil"));
		assertThat(r(15).getDescription(), is(nullValue()));
		assertThat(r(16).getDescription(), is(nullValue()));
	}
	
	@Test
	public void verificaLatitude() throws Exception {
		assertThat(r(0).getLatitude(), is(-23.526407));
		assertThat(r(1).getLatitude(), is(-23.558372));
		assertThat(r(2).getLatitude(), is(-23.550957));
		assertThat(r(3).getLatitude(), is(0.0));
		assertThat(r(4).getLatitude(), is(-23.547497));
		assertThat(r(5).getLatitude(), is(-23.591919));
		assertThat(r(6).getLatitude(), is(-23.533073));
		assertThat(r(7).getLatitude(), is(-23.557257));
		assertThat(r(8).getLatitude(), is(-23.583923));
		assertThat(r(9).getLatitude(), is(nullValue()));
		assertThat(r(10).getLatitude(), is(-23.547497));
		assertThat(r(11).getLatitude(), is(-23.562815));
		assertThat(r(12).getLatitude(), is(-23.526407));
		assertThat(r(13).getLatitude(), is(-23.558372));
		assertThat(r(14).getLatitude(), is(-23.550957));
		assertThat(r(15).getLatitude(), is(nullValue()));
		assertThat(r(16).getLatitude(), is(nullValue()));
	}
	
	@Test
	public void verificaLongitude() throws Exception {
		assertThat(r(0).getLongitude(), is(-46.668453));
		assertThat(r(1).getLongitude(), is(-46.65958));
		assertThat(r(2).getLongitude(), is(-46.67844));
		assertThat(r(3).getLongitude(), is(0.0));
		assertThat(r(4).getLongitude(), is(-46.634674));
		assertThat(r(5).getLongitude(), is(-46.646599));
		assertThat(r(6).getLongitude(), is(-46.734859));
		assertThat(r(7).getLongitude(), is(-46.659321));
		assertThat(r(8).getLongitude(), is(-46.607807));
		assertThat(r(9).getLongitude(), is(nullValue()));
		assertThat(r(10).getLongitude(), is(-46.634674));
		assertThat(r(11).getLongitude(), is(-46.680046));
		assertThat(r(12).getLongitude(), is(-46.668453));
		assertThat(r(13).getLongitude(), is(-46.65958));
		assertThat(r(14).getLongitude(), is(-46.67844));
		assertThat(r(15).getLongitude(), is(nullValue()));
		assertThat(r(16).getLongitude(), is(nullValue()));

	}	
	
	@Test
	public void verificaEndereco() throws Exception {
		assertThat(r(0).getStreetAddress(), is("Avenida Auro Soares de Moura Andrade, 664"));
		assertThat(r(1).getStreetAddress(), is("Avenida Paulista, 2064"));
		assertThat(r(2).getStreetAddress(), is("Rua Oscar Freire, 2.500"));
		assertThat(r(3).getStreetAddress(), is("Alameda Barão de Piracicaba, 740"));
		assertThat(r(4).getStreetAddress(), is("Rua Álvares Penteado, 112"));
		assertThat(r(5).getStreetAddress(), is("Largo Senador Raul Cardoso, 207"));
		assertThat(r(6).getStreetAddress(), is("Avenida Dr. Gastão Vidigal, 1946"));
		assertThat(r(7).getStreetAddress(), is("Rua Augusta, 1592"));
		assertThat(r(8).getStreetAddress(), is("Rua Bom Pastor, 822"));
		assertThat(r(9).getStreetAddress(), is(nullValue()));
		assertThat(r(10).getStreetAddress(), is("Rua Álvares Penteado, 112"));
		assertThat(r(11).getStreetAddress(), is("Rua Francisco Leitão, 198"));
		assertThat(r(12).getStreetAddress(), is("Avenida Auro Soares de Moura Andrade, 664"));
		assertThat(r(13).getStreetAddress(), is("Avenida Paulista, 2064"));
		assertThat(r(14).getStreetAddress(), is("Rua Oscar Freire, 2.500"));
		assertThat(r(15).getStreetAddress(), is(nullValue()));
		assertThat(r(16).getStreetAddress(), is(nullValue()));
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


