package ua.np.services.smsinfo.life;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import ua.np.services.smsinfo.SmsGroup;

public class CompareGroupResponceProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
    	Long l;
        SmsGroup lifeGroup = exchange.getProperty( "lifeGroup", SmsGroup.class );
        SmsGroup responceGroup = exchange.getIn().getBody(SmsGroup.class);
        if (lifeGroup != null && responceGroup != null){
        	if (lifeGroup.getSms().size() == responceGroup.getSms().size()){
        		for (int i = 0; i < lifeGroup.getSms().size(); i++){
        			lifeGroup.getSms().get(i).setState(responceGroup.getSms().get(i).getState());
        			lifeGroup.getSms().get(i).setSenderId(responceGroup.getSms().get(i).getSenderId());
        		}
        	} else {
        		throw new IllegalStateException("CompareGroupResponceProcessor: size mismatch", new Throwable("Size of received and responded Sms's mismatch"));
        	}
        } else {
        	throw new IllegalStateException("CompareGroupResponceProcessor: null pointer", new Throwable((lifeGroup == null ? "Presaved " : " Responded") + "SmsGroup might not be null"));
        }
        exchange.getOut().setBody(lifeGroup);
    }
}

