package com.programacion.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;

@ApplicationScoped
public class DataSourceProducer {
    @ConfigProperty(name = "driver")
    private String driver;

    @ConfigProperty(name = "url")
    private String url;

    @ConfigProperty(name = "user")
    private String user;

    @ConfigProperty(name = "pass")
    private String pass;

    @ApplicationScoped
    @Produces
    public DataSource getDataSource(){
        HikariDataSource ds = new HikariDataSource();
        ds.setDriverClassName(driver);
        ds.setJdbcUrl(url);
        ds.setUsername(user);
        ds.setPassword(pass);
        return ds;
    }
}
