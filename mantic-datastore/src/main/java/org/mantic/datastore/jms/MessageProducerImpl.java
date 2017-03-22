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
package org.mantic.datastore.jms;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 *
 * @author Felipe Pierin <fpierin@uolinc.com>
 *
 */
@Named("messageProducer")
public class MessageProducerImpl implements MessageProducer {
	
	private final JmsTemplate jmsTemplate;
	
	@Inject
	public MessageProducerImpl(final JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	@Override
	public void send(final Serializable object) {
		if (object == null) {
			return;
		}
		
		jmsTemplate.send(new MessageCreator() {
			@Override
			public Message createMessage(final Session session) throws JMSException {
				final ObjectMessage msg = session.createObjectMessage(object);
				return msg;
			}
		});
	}
	
}
