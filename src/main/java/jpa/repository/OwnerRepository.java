package jpa.repository;

import jpa.entity.Owner;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Vitalii Ivenko on 15.10.2015.
 */
@Repository
public class OwnerRepository {

    //    JPA version
    @PersistenceContext
    private EntityManager entityManager;

    public List<Owner> findAll() {
        return entityManager.createQuery("from Owner").getResultList();
    }

    public Owner findOwnerByName(String firstName) {
        TypedQuery<Owner> query = entityManager.createQuery("from Owner o where o.firstName = :firstName",
                Owner.class);
        query.setParameter("firstName", firstName);
        Owner owner = query.getSingleResult();
        return owner;
    }


    public long count() {
        return (Long) entityManager.createQuery("select count (o.firstName) from Owner o").getSingleResult();
    }

    public void saveAll(List<Owner> owners) {
        for(Owner owner: owners) {
            entityManager.persist(owner);
        }
    }
    public void save(Owner owner) {
        entityManager.persist(owner);
    }

    public Owner findById(int id) {
        return entityManager.find(Owner.class, id);
    }

    public void delete(Owner entity) {
        entityManager.remove(entity);
    }

    public void deleteAll() {
        entityManager.createQuery("delete from Owner").executeUpdate();
    }
}
