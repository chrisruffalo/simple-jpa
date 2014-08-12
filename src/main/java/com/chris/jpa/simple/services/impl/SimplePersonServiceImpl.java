package com.chris.jpa.simple.services.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import com.chris.jpa.simple.model.Person;
import com.chris.jpa.simple.providers.PersonProvider;
import com.chris.jpa.simple.services.SimplePersonService;

/**
 * <p>
 * Implements the person service.  Notice: application scoped so that
 * only one is created per application.  Don't really need to use EJB.
 * </p><p>
 * If we wanted to forego fine-grained transaction control we could
 * make this a stateless ejb.
 * </p>
 * 
 */
@ApplicationScoped
public class SimplePersonServiceImpl implements SimplePersonService {

    @Inject
    private PersonProvider provider;
    
    @Override
    public Response all() {
        Response all = Response.ok().entity(this.provider.all()).build();
        return all;
    }

    @Override
    public Response person(String id) {
        Person p = this.provider.get(id);
        if(p == null) {
            return Response.noContent().build();
        }
        
        Response response = Response.ok().entity(p).build();
        
        return response;
    }

    @Override
    public Response add() {
        Person p = this.provider.add();
        Response response = Response.ok().entity(p).build();
        
        return response;
    }

}
