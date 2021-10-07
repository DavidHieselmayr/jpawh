package at.htl.boundary;

import at.htl.model.Person;
import at.htl.repository.PersonRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/person")
public class PersonRessource {

    @Inject
    PersonRepository dbrepo;

    @Path("/init")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response init() {
        Person p1 = new Person("Max", "Muster");
        Person p2 = new Person("Jonas", "Gruber");

        try{
            dbrepo.persist(p1);
            dbrepo.persist(p2);
            return Response.status(Response.Status.CREATED).build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }



    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersons() {
        try {
            return Response.ok(dbrepo.getAllPersons()).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
