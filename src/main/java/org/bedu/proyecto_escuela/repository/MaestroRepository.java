package org.bedu.proyecto_escuela.repository;

import org.bedu.proyecto_escuela.model.Maestro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaestroRepository extends JpaRepository<Maestro, Long> {
    //List<Maestro> findAll();
}
