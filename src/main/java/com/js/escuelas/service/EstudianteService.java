package com.js.escuelas.service;

import com.js.escuelas.model.Estudiante;

import java.util.List;

public interface EstudianteService {

    List<Estudiante> getAll();

    List<Estudiante> findByNombre(String nombre);

    List<Estudiante> findByCarrera(String carrera);

    Estudiante findEstudiante(Estudiante estudiante);

    Estudiante save(Estudiante estudiante);

    void delete(Long id);
}
