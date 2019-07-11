package io;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/superwrap")
@Produces({"application/json", "application/xml"})
public class SuperWrap {
    @GET
    @Path("/ooo")
    public void ooo(){

    }
}
