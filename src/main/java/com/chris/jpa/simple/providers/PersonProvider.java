package com.chris.jpa.simple.providers;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.chris.jpa.simple.model.Person;

/**
 * A slightly more-modern version of a DAO (data access object)
 * 
 *
 */
@RequestScoped // one per request!
public class PersonProvider {

    /**
     * Produced by the EntityManagerProvider.  The important thing
     * is that one provider is produced per "request".  I put "request"
     * in quotes becuase it's pretty nebulous but most of the time
     * a request is 1:1 with HTTP requests (SOAP, REST, etc).
     * 
     * Sometimes, though, it can be trigered through the container itself, like
     * when an ansync EJB is called.
     * 
     */
    @Inject
    private EntityManager manager;
    
    /**
     * Fine-grained control of transactions.
     * 
     */
    @Inject
    private UserTransaction utx;
    
    public List<Person> all() {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();        
        
        CriteriaQuery<Person> pQuery = builder.createQuery(Person.class);
        
        pQuery.from(Person.class);
        
        TypedQuery<Person> tQuery = this.manager.createQuery(pQuery);
        
        try {
            return tQuery.getResultList();
        } catch (NoResultException nre) {
            return Collections.emptyList();
        }
    }
    
    public Person get(String id) {
        Person found = this.manager.find(Person.class, id);
        
        return found;
    }
    
    public Person add() {
        Person p = new Person();
        
        p.setFirst("Name-" + UUID.randomUUID().toString());
        p.setLast("Smith");
        
        try {
            this.utx.begin();
            this.manager.persist(p);
            this.utx.commit();
        } catch (NotSupportedException | SystemException e) {
            // could not commit
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (RollbackException e) {
            e.printStackTrace();
        } catch (HeuristicMixedException e) {
            e.printStackTrace();
        } catch (HeuristicRollbackException e) {
            e.printStackTrace();
        }
        
        return p;
    }
    
}
