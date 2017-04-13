package com.mantichub.agent.usp.unittest.agent;

import static com.mantichub.core.util.HTMLUtils.setByPattern;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Set;

import org.junit.Test;

import com.mantichub.agent.guiafolha.agent.GuiaDaFolhaAgent;
import com.mantichub.agent.usp.infra.TestUtils;

public class Galeria4Test {
	
	private final static String arquivo = "src/test/resources/galeria4.html";
	
	@Test
	public void verificaQtdeDeRecursos() throws Exception {
		String fromFile = TestUtils.fromFile(arquivo);
		final Set<String> urls = setByPattern(fromFile, GuiaDaFolhaAgent.OBJETO_URL);
		assertThat(urls.size(), is(14));
	}

}