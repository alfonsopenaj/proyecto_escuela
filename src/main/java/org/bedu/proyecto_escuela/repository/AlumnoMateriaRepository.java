package org.bedu.proyecto_escuela.repository;

import org.bedu.proyecto_escuela.model.AlumnoMateria;
import org.bedu.proyecto_escuela.model.AlumnoMateriaKey;
import org.bedu.proyecto_escuela.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumnoMateriaRepository extends JpaRepository<AlumnoMateria, AlumnoMateriaKey> {
    @Query("SELECT i.materia FROM AlumnoMateria i WHERE i.alumno.id_alumno = :id_alumno")
    List<Materia> findMateriasByAlumno(long id_alumno);
}
