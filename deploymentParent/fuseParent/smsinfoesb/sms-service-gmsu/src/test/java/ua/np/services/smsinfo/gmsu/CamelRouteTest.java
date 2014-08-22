package ua.np.services.smsinfo.gmsu;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;



/**
 * Created by hliuza.iu on 14.07.2014.
 */
public class CamelRouteTest extends CamelBlueprintTestSupport{

    @Override
    protected String getBlueprintDescriptor() {
        return "/OSGI-INF/blueprint/camelContext.xml," +
                "/OSGI-INF/blueprint/properties.xml";
    }

       // @Test
    public void testToGmsuRoute() throws Exception {

        context.getRouteDefinition("direct-gmsu").adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                replaceFromWith("seda:from");
                weaveById("toGmsu").replace().to("mock:toGmsu").stop();
            }
        });

        MockEndpoint toGmsu = context().getEndpoint( "mock:toGmsu", MockEndpoint.class );
        template.setDefaultEndpoint(context().getEndpoint("seda:from"));
        context.start();
        template.sendBody(getMessageFromDirectVm());
        toGmsu.expectedMessageCount(1);
        toGmsu.expectedBodiesReceived(getExpectedMessageToGmsu());
        assertMockEndpointsSatisfied();
    }


      //  @Test
    public void testGmsuToServiceRoute() throws Exception {
        context.getRouteDefinition("gmsu-to-jpa").adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                replaceFromWith("seda:from");
                weaveById("jpa").replace().to("mock:jpa").stop();
            }
        });
        MockEndpoint gmsuToService = context().getEndpoint( "mock:jpa", MockEndpoint.class );
        template.setDefaultEndpoint(context().getEndpoint("seda:from"));
        context.start();
        template.sendBody(getExpectedGmsuResponse());
        gmsuToService.expectedMessageCount(1);
        gmsuToService.expectedBodiesReceived(getConvertedGmsuResponse());
        assertMockEndpointsSatisfied();
    }


       //  @Test
    public void testStatusResponse() throws Exception {

        context.getRouteDefinition("statusResponse").adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                replaceFromWith("seda:from");
                weaveById("statusToJpa").replace().to("mock:statusToJpa").stop();
            }
        });

        MockEndpoint responseToJpa = context().getEndpoint( "mock:statusToJpa", MockEndpoint.class );
        template.setDefaultEndpoint(context().getEndpoint("seda:from"));
        context.start();
        template.sendBody(getReponseWithStatus());
        responseToJpa.expectedMessageCount(1);
        responseToJpa.expectedBodiesReceived(getConvertedResponseWithStatus());
        assertMockEndpointsSatisfied();
    }

    private String getMessageFromDirectVm(){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"+
                "<smsGroup>"+
                "<id>1</id>"+
                "<state>2</state>"+
                "<startDateTime>13.09.2007 12:10:15</startDateTime>"+
                "<endDateTime>13.09.2014 12:10:15</endDateTime>"+
                "<systemName>fff</systemName>"+
                "<smsList>"+
                "<id>1</id>"+
                "<groupId>2</groupId>"+
                "<state>7</state>"+
                "<incomingId>9</incomingId>"+
                "<text>eeeee</text>"+
                "<phoneNumber>+380976426544</phoneNumber>"+
                "</smsList>"+
                "</smsGroup>";
    }

    private String getExpectedMessageToGmsu(){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root xmlns=\"http://goldentele.com/cpa\">"+
                "<login>newmail</login>"+
                "<paswd>sdgf232fsaqa2</paswd>"+
                "<service>bulk-request</service>"+
                "<expiry>13.09.2014 12:10:15</expiry>"+
                "<tid>1</tid>"+
                "<messages>"+
                "<message>"+
                "<IDint>1</IDint>"+
                "<sin>380976426544</sin>"+
                "<body content-type=\"text/plain\">eeeee</body>"+
                "</message>"+
                "</messages>"+
                "</root>";
    }

    private String getExpectedGmsuResponse(){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
                "<report xmlns=\"http://goldetele.com/cpa\">"+
                "<status mid=\"84140005\" clid=\"0000-00001321\">Accepted</status>"+
                "<status mid=\"84140006\" clid=\"0000-00001322\">Accepted</status>"+
                "</report>";
    }

    private String getConvertedGmsuResponse(){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
                "<message>"+
                "<id>84140005</id>"+
                "<state>Accepted</state>"+
                "</message>"+
                "<message>"+
                "<id>84140006</id>"+
                "<state>Accepted</state>"+
                "</message>";
    }


    private String getReponseWithStatus(){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
                "<message xmlns=\"http://goldetele.com/cpa\" mid=\"1234\">"+
                "<service>delivery-report</service>"+
                "<status date=\"dd.mm.yyyy hh24:mi:ss\" error=\"error text if available\">Delivered</status>"+
                "</message>";
    }

    private String getConvertedResponseWithStatus(){
        return  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
                "<smsList>"+
                "<id>1234</id>"+
                "<state>Delivered</state>"+
                "</smsList>";
    }
}
