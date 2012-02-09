package no.nki.springws.server.interceptor;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.binding.soap.interceptor.Soap12FaultInInterceptor;
import org.apache.cxf.common.injection.NoJSR250Annotations;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.nki.springws.server.service.exception.BusinessException;
import no.nki.springws.server.service.exception.TechnicalException;

@NoJSR250Annotations
public class ExceptionInterceptor extends AbstractSoapInterceptor {

	private Logger logger = LoggerFactory.getLogger(ExceptionInterceptor.class);

	public ExceptionInterceptor() {
		super(Phase.MARSHAL);
		logger.error("=================>>> Calling the constructor of ExceptionInterceptor.");
	}

	public void handleMessage(SoapMessage message) throws Fault {
		Fault fault = (Fault) message.getContent(Exception.class);
		Throwable ex = fault.getCause();
		logger.error("=================>>> A soap fault has been caught.");
		if (ex instanceof BusinessException) {
			BusinessException e = (BusinessException) ex;
			logger.debug("=================>>> Generating a soap fault with a business exception.");
			generateSoapFault(fault, e);
		} else {
			logger.debug("=================>>> Generating a soap fault with an exception that is not a business one.");
			generateSoapFault(fault, null);
		}
	}

	private void generateSoapFault(Fault fault, Exception e) {
		int dummy_error_code = 1234;
		fault.setFaultCode(createQName(dummy_error_code));
		fault.setMessage("A business exception occured and was processed happily by an interceptor.");
	}

	private static QName createQName(int errorCode) {
		return new QName("http://cxf.nki.no", String.valueOf(errorCode));
	}
	
}