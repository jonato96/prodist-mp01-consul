package com.programacion.rest;

import com.programacion.db.Persona;
import com.programacion.services.ServicePersona;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Path("/personas")
public class PersonaRest {

    @Inject
    private ServicePersona srv;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Persona> findAll(){
        return srv.findAll();
    }
}
