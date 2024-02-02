package org.bedu.proyecto_escuela.repository;

import org.bedu.proyecto_escuela.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    //List<Alumno> findAll();
}
