package ua.np.services.smsinfo.legacy;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.springframework.stereotype.Component;

@Component
@Path("/")
@Consumes("text/xml")
@Produces("text/xml")
public class LegacyRestService {

    @POST
    @Path("/{systemName}") 
    public String post(@QueryParam("systemName") String systemName, @Multipart(value = "body", type = "text/xml") String body) {
    	return null;
    }
}