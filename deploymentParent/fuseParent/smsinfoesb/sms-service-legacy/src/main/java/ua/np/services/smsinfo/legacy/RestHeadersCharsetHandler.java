package ua.np.services.smsinfo.legacy;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.message.Message;
import org.apache.cxf.message.MessageImpl;
import org.apache.cxf.message.XMLMessage;

/**
 * Workaround for handle defect with UTF-8 Rest and Gsmu.
 * wraps the rest CamelCxfMessage http-header to soap-style format.
 *
 * @author soloviov.d
 */
public class RestHeadersCharsetHandler implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
    	if (exchange.getProperty("operationName").equals("sendMessages")){
    		XMLMessage cxfMessageRest = exchange.getUnitOfWork().getOriginalInMessage().getHeader("camelcxfmessage", XMLMessage.class);
    		// overlaps rest CamelCxfMessage http-header to soap-style format
    		Message m = cxfMessageRest.getMessage();
    		m.setContextualProperty(Message.ENCODING, "UTF-8");
    		SoapMessage cxfMessageSoap = new SoapMessage(new MessageImpl(m));
    		exchange.getOut().setHeaders(exchange.getIn().getHeaders());
    		exchange.getOut().setHeader("CamelCxfMessage", cxfMessageSoap);
    		exchange.getOut().setBody(exchange.getIn().getBody());
    	} 
    }
}