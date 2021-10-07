package at.htl.model.boundary;

import at.htl.model.Person;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/api/person")
public class PersonRessource {

    @Path("/init")
    @GET
    public void init() {
        Person p1 = new Person("Max", "Muster");
        Person p2 = new Person("Jonas", "Gruber");
    }

}
