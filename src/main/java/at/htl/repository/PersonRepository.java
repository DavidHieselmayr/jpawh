

package at.htl.repository;

import at.htl.model.Person;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

@ApplicationScoped
public class PersonRepository {

    @Inject
    EntityManager em;

    @Transactional
    public void persist(Person p) {
        em.persist(p);
    }

    public Object getAllPersons() {
        try {
            return em.createQuery("select p from Person p").getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
