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
package com.mantichub.agent.core.utils;

import static com.mantichub.core.util.StringUtils.isNotBlank;

import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

import com.mantichub.commons.resource.Resources;
import com.mantichub.core.vocabulary.SCHEMA;

/**
 *
 * @author Felipe Pierin <fpierin@uolinc.com>
 *
 */
public class ResourceUtils {

	public static void addProperty(final Resource resource, final Property property, final Object value) {
		if (value == null) {
			return;
		}
		if (value instanceof Resource) {
			resource.addProperty(property, (Resource) value);
		}
		if ((value instanceof String) && isNotBlank((String) value)) {
			resource.addProperty(property, (String) value);
		}
		if (value instanceof Double) {
			resource.addProperty(property, String.valueOf(value));
		}
	}

	public static Resource resourceFromName(final Resources resource) {
		if (resource != null) {
			switch (resource) {
			case ExhibitionEvent:
				return SCHEMA.ExhibitionEvent;
			case Restaurant:
				return SCHEMA.Restaurant;
			case TheaterEvent:
				return SCHEMA.TheaterEvent;
			default:
				break;
			}
		}
		return null;
	}

}

