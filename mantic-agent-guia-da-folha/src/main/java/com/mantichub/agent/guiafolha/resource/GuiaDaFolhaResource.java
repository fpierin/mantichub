package com.mantichub.agent.guiafolha.resource;

import static com.mantichub.agent.core.constant.AgentConfiguration.RDF_FORMAT;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jena.rdf.model.Model;

import com.mantichub.agent.core.infra.Agent;

@Singleton
public class GuiaDaFolhaResource extends HttpServlet {
	
	private static final long serialVersionUID = -4929126819985636942L;
	private final Agent agent;
	
	@Inject
	public GuiaDaFolhaResource(final Agent agent) {
		this.agent = agent;
	}
	
	@Override
	public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("application/rdf+xml; charset=UTF-8");
			final PrintWriter printout = response.getWriter();
			final Model model = agent.retrieve();
			final PrintWriter printWriter = response.getWriter();
			model.write(printWriter, RDF_FORMAT.getLang().getName());
			printout.flush();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
}
