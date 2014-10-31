package ua.np.services.smsinfo.smpp;

import org.apache.camel.*;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.component.smpp.SmppConstants;
import org.apache.camel.component.smpp.SmppMessageType;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;
import ua.np.services.smsinfo.Sms;


public class CamelRouteTest extends CamelBlueprintTestSupport {

    @EndpointInject(uri = "direct:submitSm")
    private Endpoint submitSm;

    @EndpointInject(uri = "mock:deliverSmMock")
    private MockEndpoint deliverSmMock;

    @EndpointInject(uri = "mock:updateSms")
    private MockEndpoint updateSms;

    @Override
    protected String getBlueprintDescriptor() {
        return "/OSGI-INF/blueprint/camelContext.xml," +
                "/OSGI-INF/blueprint/properties.xml";
    }

    //@Test
    public void sendSubmitSMInOnly_Success() throws Exception {
/*
        context.getRouteDefinition("submitSm").adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                weaveById("submitSmToparseResponse").replace().to(updateSms).stop();
            }
        });
*/

        Sms sms = new Sms();
        sms.setText("Hello SMPP World!");
        sms.setPhoneNumber("0663282836");

        deliverSmMock.expectedMessageCount(1);
        updateSms.expectedMessageCount(1);

        Exchange submitSmExchange = submitSm.createExchange(ExchangePattern.InOnly);
        Message submitSmMessage = submitSmExchange.getIn();
        submitSmMessage.setBody(sms);

        template.send(submitSm, submitSmExchange);

        assertMockEndpointsSatisfied();

        submitSmMessage = submitSmExchange.getIn();
        assertNotNull(submitSmMessage.getHeader(SmppConstants.ID));
        assertEquals(1, submitSmMessage.getHeader(SmppConstants.SENT_MESSAGE_COUNT));

        Message deliverySmMessage = deliverSmMock.getExchanges().get(0).getIn();
        assertEquals(SmppMessageType.DeliveryReceipt.toString(), deliverySmMessage.getHeader(SmppConstants.MESSAGE_TYPE));
        assertEquals(sms.getText(), deliverySmMessage.getBody());
        assertNotNull(deliverySmMessage.getHeader(SmppConstants.ID));
        assertEquals(1, deliverySmMessage.getHeader(SmppConstants.SUBMITTED));
        assertEquals(1, deliverySmMessage.getHeader(SmppConstants.DELIVERED));
        assertNotNull(deliverySmMessage.getHeader(SmppConstants.DONE_DATE));
        assertNotNull(deliverySmMessage.getHeader(SmppConstants.SUBMIT_DATE));
        assertNull(deliverySmMessage.getHeader(SmppConstants.ERROR));

    }

 /*   @Test
    public void sendLongSubmitSM() throws Exception {
        Sms sms = new Sms();
        sms.setText("Hello SMPP World! Hello SMPP World! Hello SMPP World! Hello SMPP World! Hello SMPP World! "
                + "Hello SMPP World! Hello SMPP World! Hello SMPP World! Hello SMPP World! Hello SMPP World! "
                + "Hello SMPP World! Hello SMPP World! Hello SMPP World! Hello SMPP World! Hello SMPP World! "
                + "Hello SMPP World! Hello SMPP World! Hello SMPP World! Hello SMPP World! Hello SMPP World! "
                + "Hello SMPP World! Hello SMPP World! Hello SMPP World! Hello SMPP World! Hello SMPP World! "); // 270 chars
        sms.setPhoneNumber("380674557547");

        updateSms.expectedMessageCount(3);

        Exchange exchange = submitSm.createExchange(ExchangePattern.InOnly);
        exchange.getIn().setBody(sms);

        template.send(submitSm, exchange);

        assertMockEndpointsSatisfied();
        assertEquals(SmppMessageType.DeliveryReceipt.toString(), updateSms.getExchanges().get(0).getIn().getHeader(SmppConstants.MESSAGE_TYPE));
        assertEquals(SmppMessageType.DeliveryReceipt.toString(), updateSms.getExchanges().get(1).getIn().getHeader(SmppConstants.MESSAGE_TYPE));

        assertNotNull(exchange.getIn().getHeader(SmppConstants.ID));
        assertEquals(3, exchange.getIn().getHeader(SmppConstants.SENT_MESSAGE_COUNT));
    }*/

}
