package com.programacion.configuration;

import com.orbitz.consul.Consul;
import com.orbitz.consul.model.agent.ImmutableRegCheck;
import com.orbitz.consul.model.agent.ImmutableRegistration;
import com.orbitz.consul.model.agent.Registration;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.net.InetAddress;
import java.util.UUID;

@ApplicationScoped
public class AppEvents {

    private String id;

    @ConfigProperty(name = "quarkus.application.name", defaultValue = "prodist-mp01-consul")
    private String name;

    @ConfigProperty(name = "quarkus.http.port", defaultValue = "8080")
    private Integer port;

    @PostConstruct
    public void inicializar(){
        id = name + "-" + UUID.randomUUID().toString();
    }

    public void init(@Observes StartupEvent ev) throws Exception {
        System.out.println("INICIANDO");
        //Cuando la app inicie tenemos que registrarla
        Consul consulClient = Consul.builder()
                .build();
        String urlChequeo = String.format("http://127.0.0.1:%d/q/health/live", port);

        ImmutableRegCheck check = ImmutableRegCheck.builder()
                .http(urlChequeo)
                .interval(String.format("%ss", 10))
                .deregisterCriticalServiceAfter("5s")
                .build();

        Registration service = ImmutableRegistration.builder()
                .id(id)                 //identificador de instancia
                .name(name)             //nombre de la aplicacion
                .address(InetAddress.getLocalHost().getHostAddress()) //obtenemos ip de la maquina
                .port(port)           //puerto
                .putMeta("puerto", port.toString())
                .putMeta("version", "version 1.0")
                .check(
                        check
                )
                .build();

        consulClient.agentClient().register(service);

    }

    public void destroy(@Observes ShutdownEvent ev){
        System.out.println("TERMINANDO");
        //Al finalizar eliminamos el registro
        Consul consulClient = Consul.builder()
                .build();
        consulClient.agentClient().deregister(id);
    }
}
