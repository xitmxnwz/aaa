package io.swagger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/wrap")
@Produces({"application/json", "application/xml"})
public class Wrap {

    @GET
    @Path("/www")
    public void www(){
            System.out.println(123);
    }
}
