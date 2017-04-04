package com.mantichub.agent.eventos.usp.agent;

import static com.mantichub.core.util.HTMLUtils.dateFromRegex;
import static com.mantichub.core.util.HTMLUtils.doubleFromRegex;
import static com.mantichub.core.util.HTMLUtils.nonhtmlValueByPattern;
import static com.mantichub.core.util.HTMLUtils.valueByPattern;
import static com.mantichub.core.util.StringUtils.isNotBlank;

import java.util.Date;

import org.apache.jena.rdf.model.Resource;

import com.mantichub.agent.core.infra.EventResource;
import com.mantichub.core.vocabulary.SCHEMA;

public class EventoUspEventAdapter implements EventResource {

	private final String html;

	public static final String END_DATE_PATTERN = "<td>\\d{2}/\\d{2}/\\d{2} - (\\d{2}/\\d{2}/\\d{2}) \\| \\d{2}:\\d{2} - \\d{2}:\\d{2}<br>";
	public static final String END_TIME_PATTERN = "<td>\\d{2}/\\d{2}/\\d{2} - \\d{2}/\\d{2}/\\d{2} \\| \\d{2}:\\d{2} - (\\d{2}:\\d{2})<br>";
	public static final String LATITUDE_PATTERN = "maps\\?q=([^,]+)";
	public static final String LONGITITUDE_PATTERN = "maps\\?q=[^,]+,([^+]+)";
	public static final String OVERVIEW_PATTERN = "evento-conteudo\">(.+?)(?=<!--)";
	public static final String START_DATE_PATTERN = "<td>(\\d{2}/\\d{2}/\\d{2})( - \\d{2}/\\d{2}/\\d{2})? \\| \\d{2}:\\d{2} - \\d{2}:\\d{2}<br>";
	public static final String START_TIME_PATTERN = "<td>\\d{2}/\\d{2}/\\d{2} - \\d{2}/\\d{2}/\\d{2} \\| (\\d{2}:\\d{2}) - \\d{2}:\\d{2}<br>";
	public static final String STREET_ADDRESS_PATTERN = "var endereco = '(([^,]+?), [\\d|s/n]+)";
	public static final String TITLE_PATTERN = "evento-titulo\\\"[^>]+>([^<]+)";
	public static final String FREE_EVENT_PATTERN = "(<strong>Evento Gratuito</strong>)";

	public EventoUspEventAdapter(final String html) {
		this.html = html;
	}

	@Override
	public String getStreetAddress() {
		return valueByPattern(html, STREET_ADDRESS_PATTERN);
	}

	@Override
	public String getTitle() {
		return valueByPattern(html, TITLE_PATTERN);
	}

	@Override
	public Double getLatitude() {
		return doubleFromRegex(html, LATITUDE_PATTERN);
	}

	@Override
	public Double getLongitude() {
		return doubleFromRegex(html, LONGITITUDE_PATTERN);
	}

	@Override
	public Resource getType() {
		return SCHEMA.ExhibitionEvent;
	}

	@Override
	public Date getEndDate() {
		return dateFromRegex(html, END_DATE_PATTERN, "dd/MM/yy");
	}

	@Override
	public Date getEndTime() {
		return dateFromRegex(html, END_TIME_PATTERN, "HH:mm");
	}

	@Override
	public Date getStartDate() {
		return dateFromRegex(html, START_DATE_PATTERN, "dd/MM/yy");
	}

	@Override
	public Date getStartTime() {
		return dateFromRegex(html, START_TIME_PATTERN, "HH:mm");
	}

	@Override
	public String getOverview() {
		return nonhtmlValueByPattern(html, OVERVIEW_PATTERN);
	}

	@Override
	public Double getPrice() {
		final String freeEvent = valueByPattern(html, FREE_EVENT_PATTERN);
		return isNotBlank(freeEvent) ? 0.00 : null;
	}

}
