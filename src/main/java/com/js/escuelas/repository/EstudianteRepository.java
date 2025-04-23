package com.js.escuelas.repository;

import com.js.escuelas.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante,Long> {

    List<Estudiante> findByNombre(String nombre);

    List<Estudiante> findByCarrera(String carrera);
}
