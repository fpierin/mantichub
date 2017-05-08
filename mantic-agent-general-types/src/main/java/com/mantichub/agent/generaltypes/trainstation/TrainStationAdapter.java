package com.mantichub.agent.generaltypes.trainstation;

import static org.apache.commons.lang3.text.WordUtils.capitalizeFully;

import java.io.Serializable;
import java.util.List;

import com.mantichub.commons.resource.Resources;
import com.mantichub.commons.resource.CivicStructure;

public class TrainStationAdapter implements CivicStructure, Serializable {

	private static final long serialVersionUID = -5268956549050996874L;
	
	private final String name;
	private final String latitude;
	private final String longitude;
	private final String endereco;
	private final String numero;
	private String cep;

	public TrainStationAdapter(final String name, final String latitude, final String longitude, final String endereco, final String numero, final String cep) {
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.endereco = endereco;
		this.numero = numero;
		this.setCep(cep);
	}

	@Override
	public String getDescription() {
		return "Estação " + getTitle();
	}

	@Override
	public String getImage() {
		return null;
	}

	@Override
	public Double getLatitude() {
		return new Double(latitude);
	}

	@Override
	public Double getLongitude() {
		return new Double(longitude);
	}

	@Override
	public List<String> getOpeningHours() {
		return null;
	}

	@Override
	public String getTitle() {
		return capitalizeFully(name);
	}

	@Override
	public Resources getType() {
		return Resources.TrainStation;
	}

	@Override
	public String getUrl() {
		return null;
	}

	@Override
	public String getStreetAddress() {
		return capitalizeFully(endereco + ", " + numero);
	}

	public String getCep() {
		return cep;
	}

	public void setCep(final String cep) {
		this.cep = cep;
	}

}
