package net.qwyck.samples.libertyamqp.mdb;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
public class ReceiverMDB implements MessageListener {

	@Resource
	MessageDrivenContext ejbcontext;

	@Resource
	private void setMessageDrivenContext(EJBContext ejbcontext) {

	}

	@PostConstruct
	public void postConstruct() {

	}

	public void onMessage(Message message) {
		System.out.println("Received: " + message);
	}
}
