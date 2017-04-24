package com.mantichub.agent.eventos.guiadasemana.agent;

import static com.mantichub.core.util.HTMLUtils.valueListByPattern;

import java.util.List;

public class GuiaDaSemanaAdapter  {

	private String url;
	private String html;
	private List<String> resources;

	public GuiaDaSemanaAdapter(final String url, final String html) {
		this.url = url;
		this.html = html;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(final String html) {
		this.html = html;
	}

	public List<String> getResources() {
		if (resources == null) {
			resources = valueListByPattern(html, "(<article (?:(?!</article>).)*</article>)");
		}
		return resources;
	}

	public void setResources(final List<String> resources) {
		this.resources = resources;
	}
	

}
