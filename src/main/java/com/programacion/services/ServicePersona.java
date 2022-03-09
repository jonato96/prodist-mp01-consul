package com.programacion.services;

import com.programacion.db.Persona;

import java.util.List;

public interface ServicePersona {

    void create(Persona obj);
    Persona findById(Integer id);
    List<Persona> findAll();
    void update(Persona obj, Integer id);
    void delete(Integer id);

}
