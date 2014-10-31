package ua.np.services.smsinfo.smpp;

import java.util.List;
import java.util.Map;

import ua.np.services.smsinfo.Sms;

/**
 * Created by zabuga.m on 28.07.2014.
 */
public class SubmitSmRespProcessor {

    public Sms process(Sms sms, Map<String, ?> headers) {
        List<String> camelSmppIdArray = (List<String>) headers.get("CamelSmppId");

        sms.setIncomingId(camelSmppIdArray.get(0));

        return sms;
    }
}
