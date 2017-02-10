package com.mantichub.agent.eventos.usp.resource;

import static com.mantichub.agent.eventos.usp.config.Configuration.RDF_Format;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jena.rdf.model.Model;
import org.mantic.datastore.TdbRepository;
import org.mantic.datastore.TdbRepositoryImpl;

import com.mantichub.core.agent.Agent;

@Singleton
public class EventosUSPResource extends HttpServlet {

	private static final long serialVersionUID = -4929126819985636942L;
	private final Agent agent;

	@Inject
	public EventosUSPResource(final Agent agent) {
		this.agent = agent;
	}

	@Override
	public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("application/rdf+xml; charset=UTF-8");
			final PrintWriter printout = response.getWriter();
			final Model model = agent.retrieve();
			final TdbRepository tdbRepository = new TdbRepositoryImpl("/opt/apps/mantichub/datastore");
			tdbRepository.create(model);
			final PrintWriter printWriter = response.getWriter();
			model.write(printWriter, RDF_Format.getLang().getName());
			printout.flush();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

}
