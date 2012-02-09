package no.nki.springws.server.interceptor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.stream.XMLStreamReader;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.binding.soap.saaj.SAAJInInterceptor;
import org.apache.cxf.common.injection.NoJSR250Annotations;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.service.model.ServiceInfo;
import org.apache.cxf.transport.servlet.ServletDestination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@NoJSR250Annotations
public class XmlInInterceptor extends AbstractSoapInterceptor {

	private Logger logger = LoggerFactory.getLogger(XmlInInterceptor.class);

	public XmlInInterceptor() {
		super(Phase.PRE_PROTOCOL);
		getAfter().add(SAAJInInterceptor.class.getName());
	}

	public void handleMessage(SoapMessage msg) throws Fault {
		Exchange exchange = msg.getExchange();
		Endpoint ep = exchange.get(Endpoint.class);

		ServiceInfo si = ep.getEndpointInfo().getService();
		String serviceName = si.getName().getLocalPart();
		logger.info("Service name : {}", serviceName);

		XMLStreamReader xr = msg.getContent(XMLStreamReader.class);
		if (xr != null) { // If we are not even able to parse the message in the
							// SAAJInInterceptor this can be null
			QName name = xr.getName();
			String operationName = name.getLocalPart();
			logger.info("Operation name : {}", operationName);
		}

		// Set the calling ip
		ServletRequest request = (ServletRequest) msg.get(ServletDestination.HTTP_REQUEST);
		if (request != null) { // and if the ServletDestination isn't even
								// processed, the request could be null
			String remoteHost = request.getRemoteHost();
			logger.info("Calling IP : {}", remoteHost);
		}

		try {
			SOAPMessage msgSOAP = msg.getContent(SOAPMessage.class);
			if (msgSOAP != null) { // we don't even have the message... FIXME We
									// can probably at least read the request
									// with msg.getInputStream()
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				msgSOAP.writeTo(byteArrayOutputStream);
				String encoding = (String) msg.get(Message.ENCODING);
				String xmlRequest = new String(byteArrayOutputStream.toByteArray(), encoding);
				logger.info("Xml Request was : {}", xmlRequest);
			}
		} catch (IOException e) {
			// process io exception
		} catch (SOAPException e) {
			// process soap exception
		}

	}
}