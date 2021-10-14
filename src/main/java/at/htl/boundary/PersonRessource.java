package at.htl.boundary;

import at.htl.model.Person;
import at.htl.repository.PersonRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.annotation.PostConstruct;
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

    @PostConstruct
    public void init2() {
        Person p1 = new Person("Max", "Muster");
        Person p2 = new Person("Jonas", "Gruber");
        Person p3 = new Person("Felix", "Gruber");
        Person p4 = new Person("Felix", "Baumgartner");

        dbrepo.persist(p1);
        dbrepo.persist(p2);
        dbrepo.persist(p3);
        dbrepo.persist(p4);
    }

    @Path("/init")
    @PostConstruct
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response init() {
        Person p1 = new Person("Max", "Muster");
        Person p2 = new Person("Jonas", "Gruber");
        Person p3 = new Person("Felix", "Gruber");
        Person p4 = new Person("Felix", "Baumgartner");

        try {
            dbrepo.persist(p1);
            dbrepo.persist(p2);
            dbrepo.persist(p3);
            dbrepo.persist(p4);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersons() {
        try {
            return Response.ok(dbrepo.listAll()).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
