package com.js.escuelas.service.Impl;

import com.js.escuelas.model.Estudiante;
import com.js.escuelas.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService implements com.js.escuelas.service.EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;


    @Override
    public List<Estudiante> getAll() {
        return estudianteRepository.findAll();
    }

    

    @Override
    public Estudiante save(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    @Override
    public void delete(Long id) {
         estudianteRepository.deleteById(id);
    }

    @Override
    public Estudiante findEstudiante(Estudiante estudiante) {
       Estudiante estudiante1 = estudianteRepository.findById(estudiante.getId()).orElse(null);
       return estudiante1;
    }



    @Override
    public List<Estudiante> findByNombre(String nombre) {
        return estudianteRepository.findByNombre(nombre);
    }



    @Override
    public List<Estudiante> findByCarrera(String carrera) {
        return estudianteRepository.findByCarrera(carrera);
    }
}
