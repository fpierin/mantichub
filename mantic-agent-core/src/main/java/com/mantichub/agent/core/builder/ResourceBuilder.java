/*
 * Copyright (c) 1996-2017 UOL Inc, Todos os direitos reservados
 *
 * Este arquivo e uma propriedade confidencial do Universo Online Inc. Nenhuma
 * parte do mesmo pode ser copiada, reproduzida, impressa ou transmitida por
 * qualquer meio sem autorizacao expressa e por escrito de um representante
 * legal do Universo Online Inc.
 *
 * All rights reserved
 *
 * This file is a confidential property of Universo Online Inc. No part of this
 * file may be reproduced or copied in any form or by any means without written
 * permission from an authorized person from Universo Online Inc.
 */
package com.mantichub.agent.core.builder;


import static com.mantichub.core.util.StringUtils.normalize;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.shared.Lock;

import com.mantichub.agent.core.utils.ResourceUtils;

/**
 *
 * @author Felipe Pierin <fpierin@uolinc.com>
 *
 */
public class ResourceBuilder {

	private final Model model;
	private final String projectNS;
	private Resource type;
	private Resource resource;
	
	protected ResourceBuilder(final Model model, final String projectNS) {
		this.model = model;
		this.projectNS = projectNS;
	}

	public ResourceBuilder projectNS(final String projectNS) {
		return this;
	}

	public ResourceBuilder resource(final String resourceName) {
		try {
			model.enterCriticalSection(Lock.WRITE);
			final String resourceUri = projectNS + normalize(resourceName);
			setResource(model.createResource(resourceUri));
		} finally {
			model.leaveCriticalSection();

		}
		return this;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(final Resource resource) {
		this.resource = resource;
	}

	protected void addProperty(final Property property, final Object value) {
		ResourceUtils.addProperty(getResource(), property, value);
	}
	
	public Resource getType() {
		return type;
	}
	
	public void setType(final Resource type) {
		this.type = type;
	}

}
