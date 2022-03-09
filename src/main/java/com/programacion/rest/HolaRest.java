package com.programacion.rest;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@ApplicationScoped
@Path("/hola")
public class HolaRest {
    @ConfigProperty(name = "mensaje")
    private String mensaje;

    @ConfigProperty(name = "quarkus.http.port")
    private Integer port;

    @GET
    public String saludo(){
       return mensaje +" "+port;
    }
}
