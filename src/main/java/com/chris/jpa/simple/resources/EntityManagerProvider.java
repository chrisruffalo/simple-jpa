package com.chris.jpa.simple.resources;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Creates entity managers on a per-request basis
 * 
 */
@ApplicationScoped
public class EntityManagerProvider {

    @RequestScoped
    @PersistenceContext
    private EntityManager entityManager;

    @Produces
    public EntityManager getEntityManager() {
        return this.entityManager;
    }
}
