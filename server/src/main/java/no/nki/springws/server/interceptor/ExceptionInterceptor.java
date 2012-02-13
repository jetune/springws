package no.nki.springws.server.interceptor;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.common.injection.NoJSR250Annotations;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.nki.springws.common.service.exception.BusinessException;

@NoJSR250Annotations
public class ExceptionInterceptor extends AbstractSoapInterceptor {

	private Logger logger = LoggerFactory.getLogger(ExceptionInterceptor.class);

	public ExceptionInterceptor() {
		super(Phase.MARSHAL);
	}

	public void handleMessage(SoapMessage message) throws Fault {
		Fault fault = (Fault) message.getContent(Exception.class);
		Throwable ex = fault.getCause();
		if (ex instanceof BusinessException) {
			BusinessException e = (BusinessException) ex;
			generateSoapFault(fault, e);
		} else {
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