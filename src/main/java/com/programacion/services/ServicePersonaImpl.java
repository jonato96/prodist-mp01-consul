package com.programacion.services;

import com.programacion.db.Persona;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ServicePersonaImpl implements ServicePersona{

    @Inject
    private DataSource dataSource;

    @Override
    public void create(Persona obj) {

    }

    @Override
    public Persona findById(Integer id) {
        return null;
    }

    @Override
    public List<Persona> findAll() {
        List<Persona> listado = new ArrayList<>();
        Persona obj = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement("select * from persona order by id");
            rs = ps.executeQuery();
            while(rs.next()){

                obj = new Persona();
                obj.setId(rs.getInt("id"));
                obj.setNombre(rs.getString("nombre"));
                obj.setDireccion(rs.getString("direccion"));
                listado.add(obj);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                con.close();
                ps.close();
                rs.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return listado;
    }

    @Override
    public void update(Persona obj, Integer id) {

    }

    @Override
    public void delete(Integer id) {

    }
}
