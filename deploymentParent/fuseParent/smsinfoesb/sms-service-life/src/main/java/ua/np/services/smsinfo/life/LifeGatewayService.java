package ua.np.services.smsinfo.life;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.osgi.service.component.annotations.Component;

@Component
@Path("lifes_tates")
@Produces("text/xml")

public interface LifeGatewayService {

    @POST
    public String post(@Multipart(value = "body", type = "text/xml;charset=utf-8") String body);
}