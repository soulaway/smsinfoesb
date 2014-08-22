package ua.np.services.smsinfo.life;

import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;


/**
 * Copyright (C) 2014 Nova Poshta. All rights reserved.
 * http://novaposhta.ua/
 * <p/>
 * for internal use only!
 * <p/>
 * User: pampushko.o
 * email: pampushko.o@novaposhta.ua
 * Date: 14.07.2014
 */




public class CamelRouteTest extends CamelBlueprintTestSupport
{

    @Override
    protected String getBlueprintDescriptor()
    {
        return "/OSGI-INF/blueprint/camelContext.xml," +
                "/OSGI-INF/blueprint/properties.xml";
    }


    //@Test
    public void testToLifeRoute() throws Exception
    {

        context.getRouteDefinition("direct-life").adviceWith(context, new AdviceWithRouteBuilder()
        {
            @Override
            public void configure() throws Exception
            {
                replaceFromWith("seda:from");
                weaveById("toLife").replace().to("mock:toLife").stop();
            }
        });

        MockEndpoint toLife = context().getEndpoint("mock:toLife", MockEndpoint.class);
        template.setDefaultEndpoint(context().getEndpoint("seda:from"));
        context.start();
        template.sendBody(getMessageFromDirectVm());
        toLife.expectedMessageCount(1);
        toLife.expectedBodiesReceived(getExpectedMessageToLife());
        assertMockEndpointsSatisfied();
    }

    //@Test
    public void testLifeToServiceRoute() throws Exception
    {
        context.getRouteDefinition("life-to-service").adviceWith(context, new AdviceWithRouteBuilder()
        {
            @Override
            public void configure() throws Exception
            {
                replaceFromWith("seda:from");
                weaveById("jpa").replace().to("mock:jpa").stop();
            }
        });
        MockEndpoint lifeToService = context().getEndpoint( "mock:jpa", MockEndpoint.class );
        template.setDefaultEndpoint(context().getEndpoint("seda:from"));

        context.start();
        template.sendBody(getExpectedLifeResponse());
        lifeToService.expectedMessageCount(1);
        lifeToService.expectedBodiesReceived(getConvertedLifeResponse());
        assertMockEndpointsSatisfied();
    }


    //@Test
    public void testStatusResponse() throws Exception
    {

        context.getRouteDefinition("statusResponse").adviceWith(context, new AdviceWithRouteBuilder()
        {
            @Override
            public void configure() throws Exception {
                replaceFromWith("seda:from");
                weaveById("statusToJpa").replace().to("mock:statusToJpa").stop();
            }
        });

        MockEndpoint responseToJpa = context().getEndpoint("mock:statusToJpa", MockEndpoint.class);
        template.setDefaultEndpoint(context().getEndpoint("seda:from"));
        context.start();
        template.sendBody(getReponseWithStatus());
        responseToJpa.expectedMessageCount(1);
        responseToJpa.expectedBodiesReceived(getConvertedResponseWithStatus());
        assertMockEndpointsSatisfied();
    }


    private String getMessageFromDirectVm()
    {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\r\n" +
                "<smsGroup>\r\n" +
                "    <id>13404</id>\r\n" +
                "    <state>2</state>\r\n" +
                "    <startDateTime>13.09.2007 12:10:15</startDateTime>\r\n" +
                "    <endDateTime>13.09.2014 12:10:16</endDateTime>\r\n" +
                "    <smsList>\r\n" +
                "        <id>130</id>\r\n" +
                "        <groupId>134</groupId>\r\n" +
                "        <state>2</state>\r\n" +
                "        <incomingId>6</incomingId>\r\n" +
                "        <text>bbbbbb243</text>\r\n" +
                "        <phoneNumber>0504053086</phoneNumber>\r\n" +
                "    </smsList>\r\n" +
                "    <smsList>\r\n" +
                "        <id>284</id>\r\n" +
                "        <groupId>184</groupId>\r\n" +
                "        <state>2</state>\r\n" +
                "        <incomingId>6</incomingId>\r\n" +
                "        <text>bbbbbb2</text>\r\n" +
                "        <phoneNumber>0504053086</phoneNumber>\r\n" +
                "    </smsList>\r\n" +
                "</smsGroup>\r\n";
    }

    private String getExpectedMessageToLife()
    {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><message>\r\n" +
                "<service id=\"individual\" validity=\"+5 hour\" source=\"NovaPoshta\" uniq_key=\"13404\"/>\r\n" +
                "<to>0504053086</to>\r\n" +
                "<body content-type=\"text/plain\">bbbbbb243</body>\r\n" +
                "<to>0504053086</to>\r\n" +
                "<body content-type=\"text/plain\">bbbbbb2</body>\r\n" +
                "</message>\r\n";
    }

    private String getExpectedLifeResponse()
    {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><status groupid=\"13404\" date=\"Wed, 16 Jul 2014 16:15:37 +0300\"><detail><id>158265799</id><state>Accepted</state></detail><detail><id>158265800</id><state>Accepted</state></detail></status>";
    }

    private String getConvertedLifeResponse()
    {

        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><smsList>\r\n" +
                "<id>158265799</id>\r\n" +
                "<state>Accepted</state>\r\n" +
                "<id>158265800</id>\r\n" +
                "<state>Accepted</state>\r\n" +
                "</smsList>\r\n";

    }


    private String getReponseWithStatus(){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n\n" +
                "<status>\r\n" +
                "    <detail><id>41990095</id><state>Delivered</state></detail>\r\n" +
                "    <detail><id>41885332</id><state>Delivered</state></detail>\r\n" +
                "</status>";
    }

    private String getConvertedResponseWithStatus(){
        return  "<?xml version=\"1.0\" encoding=\"UTF-8\"?><smsList><id>41990095</id><status>Delivered</status></smsList><smsList><id>41885332</id><status>Delivered</status></smsList>";
    }

}