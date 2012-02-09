package no.nki.springws.server.interceptor;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.common.injection.NoJSR250Annotations;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.io.CacheAndWriteOutputStream;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.io.CachedOutputStreamCallback;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@NoJSR250Annotations
public class XmlOutInterceptor extends AbstractSoapInterceptor {

	private Logger logger = LoggerFactory.getLogger(XmlOutInterceptor.class);

	public XmlOutInterceptor() {
		super(Phase.PRE_STREAM);
	}

	public void handleMessage(SoapMessage message) throws Fault {

		final OutputStream os = message.getContent(OutputStream.class);
		if (os == null) {
			return;
		}
		final CacheAndWriteOutputStream newOut = new CacheAndWriteOutputStream(os);
		message.setContent(OutputStream.class, newOut);

		newOut.registerCallback(new LoggingCallback(message, os));
	}

	private class LoggingCallback implements CachedOutputStreamCallback {

		private final Message message;
		private final OutputStream origStream;

		public LoggingCallback(final Message msg, final OutputStream os) {
			this.message = msg;
			this.origStream = os;
		}

		public void onFlush(CachedOutputStream cos) {

		}

		public void onClose(CachedOutputStream cos) {
			String encoding = (String) message.get(Message.ENCODING);
			String ct = (String) message.get(Message.CONTENT_TYPE);
			StringBuilder builder = new StringBuilder();
			try {
				writePayload(builder, cos, encoding, ct);
			} catch (IOException ex) {
				throw new RuntimeException("Cannot generate audit log for soap response", ex);
			}

			String msg = builder.toString();
			logger.info("OUT MESSAGE {}", msg);
			message.setContent(OutputStream.class, origStream);

		}

		protected void writePayload(StringBuilder builder, CachedOutputStream cos, String encoding, String contentType) throws IOException {
			if (StringUtils.isEmpty(encoding)) {
				cos.writeCacheTo(builder);
			} else {
				cos.writeCacheTo(builder, encoding);
			}
		}
	}

}