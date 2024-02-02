package org.bedu.proyecto_escuela.repository;

import org.bedu.proyecto_escuela.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long> {
    //List<Materia> findAll();
}